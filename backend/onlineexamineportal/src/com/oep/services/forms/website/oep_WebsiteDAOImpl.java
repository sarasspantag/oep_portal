package com.oep.services.forms.website;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import javax.xml.bind.DatatypeConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.course.oep_CourseDAOImpl;
import com.oep.services.forms.course.oep_CourseRequest;
import com.oep.services.forms.course.oep_ICourseDAO;


public class oep_WebsiteDAOImpl implements oep_IWebsiteDAO{
	
	private static Logger log = Logger.getLogger(oep_WebsiteDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private oep_Response response;
	
	@Autowired
	private oep_ResponseInfo responseInfo;
	
	
	@Override
	public oep_ResponseInfo saveNewsandeventdetails(String uploadData, HttpServletRequest httpRequest) {
						
		try{
			
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			HttpSession session = httpRequest.getSession();
				
			 StringBuffer newHtml = new StringBuffer();
			
				String html=request.getContent();
				 Pattern p = Pattern.compile("<img[^>]*src=[\\\"']([^\\\"^']*)");
				  Matcher m = p.matcher(html);
				  Pattern ps = Pattern.compile("src=[\\\"']([^\\\"^']*)");
				  Matcher ms = ps.matcher(html);
				  while (m.find() && ms.find()) {
					  Random r = new Random( System.currentTimeMillis() );
					  int imageid = 10000 + r.nextInt(20000);
					  String src = m.group();
			            int startIndex = src.indexOf("src=") + 5;
			            log.info("startIndex "+startIndex);
			            String srcTag = src.substring(startIndex, src.length());
				           log.info(srcTag);
				           String base64String =srcTag;
				  		 String[] strings = base64String.split(",");
				  		 log.info("strings "+strings);
				  	        String extension;
				  	      switch (strings[0]) {//check image's extension
			              case "data:image/jpeg;base64":
			                  extension = "jpeg";
			                  break;
			              case "data:image/png;base64":
			                  extension = "png";
			                  break;
			              case "data:image/jpg;base64":
			                  extension = "jpg";
			                  break;
			              default://should write cases for more images types
			                  extension =  null;
			                  break;
			          }
			  	    	log.info("extension"+ extension);
			  	    	if(extension != null){
			  	    	  byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			  	    	 String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
			  	    	String   modulename = oep_UTIL.getmodulename("newsform", 1);
			  	    	newpath1 = newpath1.concat(File.separator).concat(modulename);
			  	    	 File theDir = new File(newpath1);
						 if (!theDir.exists()) {
					        
					            boolean result = false;

					            try {

					                theDir.mkdirs();
					                result = true;
					            } catch (SecurityException se) {
					                // handle it
					              
					            }
					            if (result) {
					              
					            }
					        } else if (theDir.exists()) {

					           
					        }
						 newpath1 = newpath1.concat(File.separator).concat(imageid+"."+extension);
						 String imagePath1 = oep_UTIL.filepath(newpath1);
				  	        log.info("path"+ imagePath1);
				  	      File newfile = new File(newpath1);
				  	    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newfile))) {
				  	        
			  	            outputStream.write(data);
			  	        } catch (IOException e) {
			  	            e.printStackTrace();
			  	        }
				  	  String uri = httpRequest.getRequestURI();
			        	String url = httpRequest.getRequestURL().toString();
			        	String ctxPath = httpRequest.getContextPath();
			        	url = url.replaceFirst(uri, "");
			        	url = url + ctxPath+"/";
			            String id= ms.group(1);
			            String newId = url+imagePath1;
			            newId=newId.replace("\\","\\\\");
			          String rep = "src='" + newId + "'";
			            ms.appendReplacement(newHtml, rep);
			  	    	}
				  } ms.appendTail(newHtml);
				  log.info("replace "+newHtml.toString());
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_HEADLINE",request.getHeadline());
			inParamMap.put("P_BLOG_ID",request.getBlogid());
			inParamMap.put("P_CONTENT",newHtml.toString());
			inParamMap.put("P_IMAGE",request.getHeadline());
			inParamMap.put("P_DESCRIPTION",request.getHeadline());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",request.getStatus());

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_NEWS_AND_EVENTS_MODULE", inParamMap);			
			
			String resultType = (String)resultMap.get("out_result_type");
			String blogdescpath = request.getFilename1();
			String imagePath = "";  
			int courseid = 0;  
			String desriptionPath ="";
			
			if(resultMap.get("out_result_type") != null && resultMap.get("out_result_type").equals("S") && request.getFilename() != null)
			{
				
				
				
				if(request.getFilename() !=null){
				String oldimagefilepath = request.getFilename();					
				int blogid = Integer.parseInt((String) resultMap.get("out_genrate_id"));

				if(blogid > 0)
				{									
					String newpath = oep_UTIL.getserverfilepath(httpRequest);
					String modulename = oep_UTIL.getmodulename("news", 3);
					newpath = newpath.concat(File.separator).concat(modulename);							
					
					File uploadPath = new File(newpath);
					if(!uploadPath.exists())
					{
						uploadPath.mkdirs();
					}
					newpath = newpath.concat(File.separator).concat(blogid+".jpg");
					
					if(oldimagefilepath!=null){
					
						  File oldfile = new File(oldimagefilepath);
						  if(oldfile.exists()){
	        	  		File file = new File(newpath);
						if(file.exists())
						{
							java.nio.file.Path fileToDeletePath = Paths.get(newpath);
							Files.delete(fileToDeletePath);
						}
						  }	
					
					new File(oldimagefilepath).renameTo(new File(newpath));
					}
					
					 imagePath = oep_UTIL.filepath(newpath);
					
				}
				}
				
	if(resultMap.get("out_result_type") != null && resultMap.get("out_result_type").equals("S") && request.getFilename1() != null)
	{			
			 courseid = Integer.parseInt((String) resultMap.get("out_genrate_id"));
			 if(courseid > 0)
			 {
				String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
				String modulename1 = oep_UTIL.getmodulename("news", 7);	
				newpath1 = newpath1.concat(File.separator).concat(modulename1);
								
				File uploadPath1 = new File(newpath1);
				if(!uploadPath1.exists())
				{
					uploadPath1.mkdirs();
				}
				newpath1 = newpath1.concat(File.separator).concat(courseid+".docx");
				
				if(blogdescpath!=null){
				
					  File oldfile = new File(blogdescpath);
					  if(oldfile.exists()){
        	  		File file = new File(newpath1);
					if(file.exists())
					{
						java.nio.file.Path fileToDeletePath = Paths.get(newpath1);
						Files.delete(fileToDeletePath);						
					}
					  }
				new File(blogdescpath).renameTo(new File(newpath1));
				
				}					
				 desriptionPath = oep_UTIL.filepath(newpath1);			
			}
			
	}
	
	SimpleJdbcCall jdbcCALLAaftersave = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_NEWS_AND_EVENTS_MODULE");
	Map<String, Object> inParamMap2 = new HashMap<String, Object>();
		
	inParamMap2.put("P_HEADLINE",request.getHeadline());
	inParamMap2.put("P_BLOG_ID",resultMap.get("out_genrate_id"));
	inParamMap2.put("P_CONTENT",newHtml.toString());
	inParamMap2.put("P_IMAGE",imagePath);
	inParamMap2.put("P_DESCRIPTION",desriptionPath);
	inParamMap2.put("P_CREATED_BY",request.getUserid());
	inParamMap2.put("P_OPRN",request.getOprn());
	inParamMap2.put("P_STATUS",request.getStatus());
	inParamMap2.put("P_OPRN","UPD");
	
	SqlParameterSource in2 = new MapSqlParameterSource(inParamMap2);
	Map<String, Object> resultMap2 = jdbcCALLAaftersave.execute(in2);
			response.setResponseObj(resultMap);

			}		
		    }catch(Exception e)
		      {
			    e.printStackTrace();
			
		      }
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	

	@Override
	public oep_ResponseInfo savealumniblogdetails(String uploadData, HttpServletRequest httpRequest) {
						
		try{
			  StringBuffer newHtml = new StringBuffer();
		
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			HttpSession session = httpRequest.getSession();
				log.info("div "+request.getFormdata());	
				String html=request.getFormdata();
				 Pattern p = Pattern.compile("<img[^>]*src=[\\\"']([^\\\"^']*)");
				  Matcher m = p.matcher(html);
				  Pattern ps = Pattern.compile("src=[\\\"']([^\\\"^']*)");
			        Matcher ms = ps.matcher(html);
				  while (m.find() && ms.find()) {
						Random r = new Random( System.currentTimeMillis() );
						int imageid = 10000 + r.nextInt(20000);
			            String src = m.group();
			            int startIndex = src.indexOf("src=") + 5;
			            log.info("startIndex "+startIndex);
			            String srcTag = src.substring(startIndex, src.length());
			           log.info(srcTag);
			           String base64String =srcTag;
			  		 String[] strings = base64String.split(",");
			  		 log.info("strings "+strings);
			  	        String extension;
			  	
			  	        switch (strings[0]) {//check image's extension
			              case "data:image/jpeg;base64":
			                  extension = "jpeg";
			                  break;
			              case "data:image/png;base64":
			                  extension = "png";
			                  break;
			              case "data:image/jpg;base64":
			                  extension = "jpg";
			                  break;
			              default://should write cases for more images types
			                  extension =  null;
			                  break;
			          }
			  	    	log.info("extension"+ extension);
			  	    	if(extension != null){
			  	    		
			  	    	
			  	        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			  	     
			  	  	  String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
			  	      String   modulename = oep_UTIL.getmodulename("alumni", 1);
					
						newpath1 = newpath1.concat(File.separator).concat(modulename);
					
						 File theDir = new File(newpath1);
						 if (!theDir.exists()) {
					        
					            boolean result = false;

					            try {

					                theDir.mkdirs();
					                result = true;
					            } catch (SecurityException se) {
					                // handle it
					              
					            }
					            if (result) {
					              
					            }
					        } else if (theDir.exists()) {

					           
					        }
						newpath1 = newpath1.concat(File.separator).concat(imageid+"."+extension);
			  	     
						String imagePath1 = oep_UTIL.filepath(newpath1);
			  	        log.info("path"+ imagePath1);
			  	      File newfile = new File(newpath1);
			  	      
			  	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newfile))) {
			  	        
			  	            outputStream.write(data);
			  	        } catch (IOException e) {
			  	            e.printStackTrace();
			  	        }
			  	      String uri = httpRequest.getRequestURI();
			        	String url = httpRequest.getRequestURL().toString();
			        	String ctxPath = httpRequest.getContextPath();
			        	url = url.replaceFirst(uri, "");
			        	url = url + ctxPath+"/";
			            String id= ms.group(1);
			            String newId = url+imagePath1;
			            newId=newId.replace("\\","\\\\");
			          String rep = "src='" + newId + "'";
			            ms.appendReplacement(newHtml, rep);
			  	  
				  }
			        }  ms.appendTail(newHtml);
				  log.info("replace "+newHtml.toString());
				    
		  LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_HEADLINE",request.getHeadline());
			inParamMap.put("P_BLOG_ID",request.getBlogid());
			inParamMap.put("P_CONTENT",newHtml.toString());
			inParamMap.put("P_IMAGE",request.getHeadline());
			inParamMap.put("P_DESCRIPTION",request.getHeadline());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",request.getStatus());
			log.info("inparam "+inParamMap);
			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_ALUMNI_BLOG_MODULE", inParamMap);
			
			String resultType = (String)resultMap.get("out_result_type");
			String blogdescpath = request.getFilename1();
			String imagePath = "";  
			int courseid = 0;  
			String desriptionPath ="";
			
			if(resultMap.get("out_result_type") != null && resultMap.get("out_result_type").equals("S") && request.getFilename() != null)
			{
				if(request.getFilename() !=null){
				String oldimagefilepath = request.getFilename();		
										
				int blogid = Integer.parseInt((String) resultMap.get("out_genrate_id"));

				if(blogid > 0)
				{
									
					String newpath = oep_UTIL.getserverfilepath(httpRequest);
					String modulename = oep_UTIL.getmodulename("product", 3);
					newpath = newpath.concat(File.separator).concat(modulename);		
					
					File uploadPath = new File(newpath);
					if(!uploadPath.exists())
					{
						uploadPath.mkdirs();
					}
					newpath = newpath.concat(File.separator).concat(blogid+".jpg");
					
					if(oldimagefilepath!=null){
					
						  File oldfile = new File(oldimagefilepath);
						  if(oldfile.exists()){
	        	  		File file = new File(newpath);
						if(file.exists())
						{
							java.nio.file.Path fileToDeletePath = Paths.get(newpath);
							Files.delete(fileToDeletePath);
						}
						  }
						  new File(oldimagefilepath).renameTo(new File(newpath));
					}
					 imagePath = oep_UTIL.filepath(newpath);
					
				}
				}
				
	if(resultMap.get("out_result_type") != null && resultMap.get("out_result_type").equals("S") && request.getFilename1() != null)
	{			
			 courseid = Integer.parseInt((String) resultMap.get("out_genrate_id"));
			if(courseid > 0)
			{
				String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
				String modulename1 = oep_UTIL.getmodulename("product", 7);	
				newpath1 = newpath1.concat(File.separator).concat(modulename1);
				
				File uploadPath1 = new File(newpath1);
				if(!uploadPath1.exists())
				{
					uploadPath1.mkdirs();
				}
				newpath1 = newpath1.concat(File.separator).concat(courseid+".docx");
				
				if(blogdescpath!=null){
				
					  File oldfile = new File(blogdescpath);
					  if(oldfile.exists()){
        	  		File file = new File(newpath1);
					if(file.exists())
					{
						java.nio.file.Path fileToDeletePath = Paths.get(newpath1);
						Files.delete(fileToDeletePath);
						
					}
					  }
				new File(blogdescpath).renameTo(new File(newpath1));
				
				}					
				 desriptionPath = oep_UTIL.filepath(newpath1);
			}
	}
	
	SimpleJdbcCall jdbcCALLAaftersave = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_ALUMNI_BLOG_MODULE");
	Map<String, Object> inParamMap2 = new HashMap<String, Object>();
		
	inParamMap2.put("P_HEADLINE",request.getHeadline());
	inParamMap2.put("P_BLOG_ID",resultMap.get("out_genrate_id"));
	inParamMap2.put("P_CONTENT",newHtml.toString());
	inParamMap2.put("P_IMAGE",imagePath);
	inParamMap2.put("P_DESCRIPTION",desriptionPath);
	inParamMap2.put("P_CREATED_BY",request.getUserid());
	inParamMap2.put("P_OPRN",request.getOprn());
	inParamMap2.put("P_STATUS",request.getStatus());
	inParamMap2.put("P_OPRN","UPD");
	
	SqlParameterSource in2 = new MapSqlParameterSource(inParamMap2);
	Map<String, Object> resultMap2 = jdbcCALLAaftersave.execute(in2);
			response.setResponseObj(resultMap);

			}	
		    }catch(Exception e)
		      {
			    e.printStackTrace();
			
		      }
		log.info("response "+response);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo signup(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_USERID",request.getUserid());
			/*inParamMap.put("P_FIRST_NAME",request.getFirstname());
			inParamMap.put("P_LAST_NAME",request.getLastname());*/
			inParamMap.put("P_USERNAME",request.getUsername());
			inParamMap.put("P_EMAIL",request.getEmail());
			inParamMap.put("P_MOBILE",request.getMobile());
			inParamMap.put("P_PASSWORD",oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", request.getPassword()));
			inParamMap.put("P_CATEGORY",request.getCategory());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SIGN_UP", inParamMap);
			log.info(resultMap);
		
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo savecontactus(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_USERID",request.getUserid());			
			inParamMap.put("P_USERNAME",request.getUsername());
			inParamMap.put("P_EMAIL",request.getEmail());
			inParamMap.put("P_MOBILE",request.getMobile());
			inParamMap.put("P_CONTENT",request.getContent());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_CONTACT_US", inParamMap);
			log.info(resultMap);
		
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo savetodolist(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_LIST_ID",request.getListid());	
			inParamMap.put("P_USER_ID",request.getUserid());	
			inParamMap.put("P_CONTENT",request.getContent());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_TO_DO_LIST", inParamMap);
			log.info(resultMap);
		
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getNewsandeventdetails(String id) {
		
		String query = "SELECT `id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(`created_at`,'%m/%d/%Y')created_at,"
				+ "`created_by`,`status` FROM `news_blog`	WHERE `id` = "+id+" ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("headline", rs.getString("headline"));
				map.put("blogid", rs.getString("id"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(questiondetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getNewsandeventList() {
		
		String query = "SELECT `id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,"
				+ "`created_by`,`status` FROM `news_blog`  ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> blogList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("headline", rs.getString("headline"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				map.put("blogid", rs.getString("id"));
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(blogList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo getNewsandeventListwithStatus() {
		
		String query = "SELECT `id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(`created_at`,' %e %b %Y')created_at,"
				+ "`created_by`,`status` FROM `news_blog` WHERE `status` = 1  ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> blogList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("headline", rs.getString("headline"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				map.put("blogid", rs.getString("id"));
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(blogList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo getalumniblogdetails(String id) {
		
		String query = "SELECT `id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,"
				+ "`created_by`,`status` FROM `alumni_blog`	WHERE `id` = "+id+" ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("headline", rs.getString("headline"));
				map.put("blogid", rs.getString("id"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(questiondetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getalumniblogList() {
		
		String query = "SELECT `id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,"
				+ "`created_by`,`status` FROM `alumni_blog` ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> blogList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("headline", rs.getString("headline"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				map.put("blogid", rs.getString("id"));
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(blogList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}

	@Override
	public oep_ResponseInfo getRecentBlogPost() {
		
	/*	String query = " SELECT `id`,`headline`,`content`,`image`,`description`,"
				+ " DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,`created_by`,`status`"
				+ " FROM `alumni_blog` ORDER BY `created_at` DESC LIMIT 4 ";*/
		
		String query = " SELECT a.`id`,`headline`,`content`,`image`,`description`,DATE_FORMAT(a.`created_at`,'%e/%b/%Y')created_at,"
				+ "a.`created_by`,a.`status`,(SELECT COUNT(`comments`) FROM `alumni_blog_comments` WHERE `blog_id` =  a.`id`)selcount"
				+ "	FROM `alumni_blog` a JOIN  `alumni_blog_comments` b ON a.`id`=b.`blog_id` GROUP BY  a.`id` DESC LIMIT 4 ";
		
		
		
		@SuppressWarnings("unchecked")
		List<Object> RecentList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("headline", rs.getString("headline"));
				map.put("commentcount", rs.getString("selcount"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				map.put("blogid", rs.getString("id"));
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				count++;
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(RecentList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo getRecentNewsPost() {
		
		String query = " SELECT a.`id`,`headline`,a.`content`,a.`image`,a.`description`, MONTHNAME(a.`created_at`)disp,"
				+ " DATE_FORMAT(a.`created_at`,'%d/%m/%Y')created_at,a.`created_by`,a.`status`,MONTH (a.`created_at`)disp1,YEAR(a.`created_at`)disp2,"
				+ " (SELECT COUNT(`comments`) FROM `news_events_blog_comments` WHERE `id` =  a.`id`)selcount  "
				+ " FROM `news_blog`a WHERE `status` = 1 GROUP BY MONTH(a.`created_at`) DESC  LIMIT 4 ";
		
		/*String query = " SELECT `id`,`headline`,`content`,`image`,`description`, MONTHNAME(`created_at`)disp,"
				+ " DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,`created_by`,`status`,MONTH (`created_at`)disp1,YEAR(`created_at`)disp2  "
				+ " FROM `news_blog` WHERE `status` = 1 GROUP BY MONTH(`created_at`), YEAR(`created_at`) DESC  LIMIT 4 ";
		*/
		
		log.info(query);
		@SuppressWarnings("unchecked")
		List<Object> RecentList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("monthname", rs.getString("disp"));
				map.put("monthvalue", rs.getString("disp1"));
				map.put("commentcount", rs.getString("selcount"));
				map.put("yearvalue", rs.getString("disp2"));
				map.put("headline", rs.getString("headline"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				map.put("blogid", rs.getString("id"));
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				count++;
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(RecentList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo getMonthlyNewsdetails(String id,String year) {
		
		String query = " SELECT `id`,`headline`,`content`,`image`,`description`, MONTHNAME(`created_at`)disp,"
				+ " DATE_FORMAT(`created_at`,'%d/%m/%Y')created_at,`created_by`,`status`,MONTH (`created_at`)disp1,YEAR(`created_at`)disp2  "
				+ " FROM `news_blog` WHERE MONTH(`created_at`) = "+id+" AND YEAR(created_at) = "+year+"   LIMIT 4 ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("monthname", rs.getString("disp"));
				map.put("monthvalue", rs.getString("disp1"));
				map.put("yearvalue", rs.getString("disp2"));
				map.put("headline", rs.getString("headline"));
				map.put("blogid", rs.getString("id"));
				map.put("content", rs.getString("content"));
				map.put("image", rs.getString("image"));
				map.put("description", rs.getString("description"));
				map.put("date", rs.getString("created_at"));
				if(rs.getString("status")!= null && rs.getString("status").equals("1")){
					map.put("status", "Active");
				}else{
					map.put("status", "Inactive");
				}
				map.put("createdby", rs.getString("created_by"));
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(questiondetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	
	@Override
	public oep_ResponseInfo getimagecategorydetails() {
		
		String query = "SELECT * FROM `gallery_image_category` WHERE `category_id` != 1";
		
		@SuppressWarnings("unchecked")
		List<Object> categoryList = jdbcTemplate.query(query, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("categoryid", rs.getString("category_id"));
				map.put("categoryname", rs.getString("category_name"));
				
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(categoryList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo getgalleryimage(String categoryid) {
		// TODO Auto-generated method stub
		String imagequery="SELECT `category_image_path`,`category_image_desc`,`category_image_id`  FROM `gallery_image_category_details`"
				+ " WHERE `category_id`= "+categoryid+"  ";
		@SuppressWarnings("unchecked")
		List<Object> imageList = jdbcTemplate.query(imagequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imagepath", rs.getString("category_image_path"));
				map.put("imagedesc", rs.getString("category_image_desc"));
				map.put("imageid", rs.getString("category_image_id"));
				
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(imageList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getAllgalleryimage() {
		// TODO Auto-generated method stub
		String imagequery="SELECT `category_image_path`,`category_image_desc`,`category_image_id` FROM `gallery_image_category_details`" ;
		@SuppressWarnings("unchecked")
		List<Object> imageList = jdbcTemplate.query(imagequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imagepath", rs.getString("category_image_path"));
				map.put("imagedesc", rs.getString("category_image_desc"));
				map.put("imageid", rs.getString("category_image_id"));
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(imageList);
		responseInfo.setInventoryResponse(response)  ;
		return responseInfo;
	}
	
	
	
	@Override
	public oep_ResponseInfo getHomegalleryimage(String categoryid) {
		// TODO Auto-generated method stub
		String imagequery="SELECT `category_image_path`,`category_image_desc` FROM `gallery_image_category_details` LIMIT 5" ;
		@SuppressWarnings("unchecked")
		List<Object> imageList = jdbcTemplate.query(imagequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imagepath", rs.getString("category_image_path"));
				map.put("imagedesc", rs.getString("category_image_desc"));
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(imageList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	@Override
	public oep_ResponseInfo savecategoryimage(String uploadData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		try {
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			String imagedesc ="";
			
			 
			 if(request.getOprn().equals("DEL")){
				 LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
					inParamMap.put("P_CATEGORY_ID",request.getCategoryid());
					inParamMap.put("P_IMAGE_PATH","img");
					inParamMap.put("P_OPRN",request.getOprn());
					inParamMap.put("P_IMAGE_DESC",imagedesc);
					log.info("inParamMap2         "+inParamMap);
					Map<String, Object> resultMap2 = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_GALLERY_IMAGE", inParamMap);
				 
			 }else{				 
				 JSONArray jsonArray = new JSONArray(request.getGalleryarray()); 
					log.info("jsonArray"+jsonArray);
					 int i = 0;
					 JSONObject explrObject = new JSONObject();
			  while(i < jsonArray.length()){
				  int digits = 0;
					Random s = new Random( System.currentTimeMillis() );
					  digits = 10000 + s.nextInt(20000);
					int imageid = digits;
			    explrObject = jsonArray.getJSONObject(i);
			
				log.info("explrObject"+explrObject.getString("filename"));
				String oldimagefilepath = explrObject.getString("filename");
				if (!jsonArray.getJSONObject(i).has("imagedesc")) {
					 imagedesc = "";
					}
				else{
					imagedesc = explrObject.getString("imagedesc");	
				}
				String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
				String modulename1 = oep_UTIL.getmodulename("gallery", 1);	
				newpath1 = newpath1.concat(File.separator).concat(modulename1);
				log.info(newpath1);
				log.info(modulename1);
				log.info(newpath1);
								
				File uploadPath1 = new File(newpath1);
				if(!uploadPath1.exists())
				{
					uploadPath1.mkdirs();
				}
				newpath1 = newpath1.concat(File.separator).concat(imageid+".jpg");
				if(oldimagefilepath!=null){
					
					  File oldfile = new File(oldimagefilepath);
					  if(oldfile.exists()){
      	  		File file = new File(newpath1);
					if(file.exists())
					{
						java.nio.file.Path fileToDeletePath = Paths.get(newpath1);
						Files.delete(fileToDeletePath);
						
					}
					  }
					  log.info(newpath1);
				new File(oldimagefilepath).renameTo(new File(newpath1));
				
				}	
				String galleryPath = oep_UTIL.filepath(newpath1);
				log.info(galleryPath);
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_CATEGORY_ID",request.getCategoryid());
				inParamMap.put("P_IMAGE_PATH",galleryPath);
				inParamMap.put("P_OPRN",request.getOprn());
				inParamMap.put("P_IMAGE_DESC",imagedesc);
				log.info("inParamMap2         "+inParamMap);
				Map<String, Object> resultMap2 = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_GALLERY_IMAGE", inParamMap);
				  i++;
			}
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setResponseType("S");
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo saveblogcomments(String uploadData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		
		try {
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_BLOG_ID",request.getBlogid());
			inParamMap.put("P_COMMENTS",request.getComments());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",1);
			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_BLOG_COMMENTS", inParamMap);	
			response.setResponseObj(resultMap);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo savenewseventscomments(String uploadData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		
		try {
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_BLOG_ID",request.getBlogid());
			inParamMap.put("P_COMMENTS",request.getComments());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",1);
			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_BLOG_COMMENTS", inParamMap);	
			response.setResponseObj(resultMap);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo getblogcomments(String blogid) {
		// TODO Auto-generated method stub
		
		String userquery = "SELECT `username`,b.`id` commentid,`blog_id`,comments,CASE WHEN `role` ='2' THEN (SELECT `faculty_profile` FROM `faculty_master` WHERE `userid`=a.`userid`)"
				+ " WHEN `role` ='4' THEN (SELECT `imagepath` FROM `participants` WHERE `user_id`=a.`userid`) ELSE 'resources/images/user.png' END AS imagepath"
				+ " FROM `users` a JOIN `alumni_blog_comments` b ON a.`userid`=b.`created_by`  JOIN `alumni_blog` c ON c.`id`=b.`blog_id` WHERE c.`id`="+blogid;
		
		@SuppressWarnings("unchecked")
		List<Object>  commentsList = jdbcTemplate.query(userquery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("username", rs.getString("username"));
				map.put("imagepath", rs.getString("imagepath"));
				map.put("commentid", rs.getString("commentid"));
				map.put("blogid", rs.getString("blog_id"));
				map.put("comments", rs.getString("comments"));
				return map;
			}
		});
		String likequery="SELECT COALESCE(SUM(`likes_count`),0)likecopunt,a.`comment_id` FROM `alumni_blog_likes` a JOIN `alumni_blog_comments` b "
				+ " ON a.`comment_id`=b.`id` JOIN `alumni_blog` c ON c.`id`=b.`blog_id` WHERE c.`id`='"+blogid+" ' GROUP BY  a.`comment_id` ";
		log.info(likequery);
		@SuppressWarnings("unchecked")
		List<Object>  likecountList = jdbcTemplate.query(likequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("like", rs.getString("likecopunt"));
				map.put("commentid", rs.getString("comment_id"));
				
				return map;
			}
		});
		
		String replyquery="SELECT `reply_content`,a.`comment_id`,`username` FROM `alumni_blog_reply` a JOIN `alumni_blog_comments` b "
				+ " ON a.`comment_id`=b.`id` JOIN `alumni_blog` c ON c.`id`=b.`blog_id` JOIN `users` d ON d.`userid` = a.`created_by`"
				+ " WHERE c.`id`='"+blogid+" ' ORDER BY  a.`id` ";
		log.info(replyquery);
		@SuppressWarnings("unchecked")
		List<Object>  replyList = jdbcTemplate.query(replyquery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", rs.getString("username"));
				map.put("replycontent", rs.getString("reply_content"));
				map.put("commentid", rs.getString("comment_id"));
				
				return map;
			}
		});
		
		
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		
	
		
		detailsmap.put("commentsList", commentsList);
		detailsmap.put("likecountList", likecountList);
		detailsmap.put("replyList", replyList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
		
	}


	@Override
	public oep_ResponseInfo savebloglike(String uploadData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		
		try {
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_COMMENT_ID",request.getCommentid());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",1);
			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_BLOG_LIKES", inParamMap);	
			response.setResponseObj(resultMap);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo saveblogreply(String uploadData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		try {
			oep_WebsiteRequest request = (oep_WebsiteRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_WebsiteRequest.class);
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_REPLY_CONTENT",request.getReply());
			inParamMap.put("P_COMMENT_ID",request.getCommentid());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",1);
			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_BLOG_REPLY", inParamMap);	
			response.setResponseObj(resultMap);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo getnewsandeventsalldetails(String newsblogid) {
		// TODO Auto-generated method stub
		String userquery = "SELECT `username`,b.`id` commentid,comments,CASE WHEN `role` ='2' THEN (SELECT `faculty_profile` FROM `faculty_master` WHERE `userid`=a.`userid`)"
				+ " WHEN `role` ='4' THEN (SELECT `imagepath` FROM `participants` WHERE `user_id`=a.`userid`) ELSE 'resources/images/user.png' END AS imagepath"
				+ " FROM `users` a JOIN `news_events_blog_comments` b ON a.`userid`=b.`created_by`  JOIN `news_blog` c ON c.`id`=b.`news_blog_id` WHERE c.`id`="+newsblogid;
		
		@SuppressWarnings("unchecked")
		List<Object>  commentsList = jdbcTemplate.query(userquery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("username", rs.getString("username"));
				map.put("imagepath", rs.getString("imagepath"));
				map.put("commentid", rs.getString("commentid"));
				map.put("comments", rs.getString("comments"));
				return map;
			}
		});
		String likequery="SELECT COALESCE(SUM(`likes_count`),0)likecopunt,a.`comment_id` FROM `news_events_blog_likes` a JOIN `news_events_blog_comments` b "
				+ " ON a.`comment_id`=b.`id` JOIN `news_blog` c ON c.`id`=b.`news_blog_id` WHERE c.`id`='"+newsblogid+" ' GROUP BY  a.`comment_id` ";
		log.info(likequery);
		@SuppressWarnings("unchecked")
		List<Object>  likecountList = jdbcTemplate.query(likequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("like", rs.getString("likecopunt"));
				map.put("commentid", rs.getString("comment_id"));
				
				return map;
			}
		});
		
		String replyquery="SELECT `reply_content`,a.`comment_id` FROM `news_events_blog_reply` a JOIN `news_events_blog_comments` b "
				+ " ON a.`comment_id`=b.`id` JOIN `news_blog` c ON c.`id`=b.`news_blog_id` WHERE c.`id`='"+newsblogid+" ' ORDER BY  a.`id` ";
		log.info(replyquery);
		@SuppressWarnings("unchecked")
		List<Object>  replyList = jdbcTemplate.query(replyquery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("replycontent", rs.getString("reply_content"));
				map.put("commentid", rs.getString("comment_id"));
				
				return map;
			}
		});
		
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		detailsmap.put("commentsList", commentsList);
		detailsmap.put("likecountList", likecountList);
		detailsmap.put("replyList", replyList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getTodoList(String userid) {
		
		String query = "SELECT `id`,`content` FROM `todo_list` WHERE `created_by` = "+userid+"  ORDER BY `created_at` DESC LIMIT 5 ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> List = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("content", rs.getString("content"));				
				map.put("id", rs.getString("id"));				
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(List);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getTodoListdetails(String id) {
		// TODO Auto-generated method stub
		String imagequery="SELECT `id`,`content` FROM `todo_list` "
				+ " WHERE `id`= "+id+" LIMIT 5";
		@SuppressWarnings("unchecked")
		List<Object> List = jdbcTemplate.query(imagequery, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("content", rs.getString("content"));				
				map.put("id", rs.getString("id"));				
				
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(List);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getRecentReviewList() {
		
		String query = " (SELECT CONCAT('Course ',`course_name`,' created By ',`username`)description, "
				+ " CASE WHEN DATEDIFF(CURDATE(),`created_date`) > 0 THEN CONCAT(DATEDIFF(CURDATE(),`created_date`),' days ago') ELSE 'created today'  END AS listdata"
				+ "  FROM `course_master`a  JOIN `users`b ON a.`created_by` = b.`userid` JOIN `roles`c ON c.`id`= b.`role` GROUP BY `course_id`)  UNION ALL "
		+ " (SELECT CONCAT('Course ',`course_name`,' Scheduled from ',DATE_FORMAT(`start_date`,'%d/%m/%Y'),' to ', DATE_FORMAT(`end_date`,'%d/%m/%Y'))description,"
				+ "  CASE WHEN DATEDIFF(CURDATE(),`created_date`) > 0 THEN CONCAT(DATEDIFF(CURDATE(),`created_date`),' days ago') ELSE 'created today'   END AS listdata "
				+ " FROM `course_scheduling`b JOIN `course_master` a ON a.`course_id`=b.`program_name`  GROUP BY `cs_id` LIMIT 5)  UNION ALL"
		+ " (SELECT CONCAT('Test ',`test_name`,' created for the course ',`course_name`,' By ',`username`)description, "
				+ " CASE WHEN DATEDIFF(CURDATE(),b.`created_date`) > 0 THEN CONCAT(DATEDIFF(CURDATE(),b.`created_date`),' days ago') ELSE 'created today'  END AS listdata"
				+ "  FROM `question_master`b  JOIN  `course_master` a ON a.`course_id`= b.`sub_id`JOIN `users`c ON b.`created_by` = c.`userid` GROUP BY `sub_id`) UNION ALL"
		+ "  (SELECT CONCAT('Test ',`test_name`,' created for the course ',`course_name`,' is scheduled on ', DATE_FORMAT(`testdate`,'%d/%m/%Y'),' By ',`username`)description ,"
				+ "  CASE WHEN DATEDIFF(CURDATE(),c.`created_at`) > 0 THEN CONCAT(DATEDIFF(CURDATE(),c.`created_at`),' days ago') ELSE 'created today'  END AS listdata "
				+ "  FROM `test_schedule`c   JOIN  `question_master`b ON c.`ques_master_id`= b.`id`  JOIN  `course_master` a ON a.`course_id`=b.`sub_id`"
				+ "  JOIN `users`d ON c.`created_by` = d.`userid`   GROUP BY c.`id`) ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> List = jdbcTemplate.query(query, new RowMapper() {
			int count = 0;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
			
				map.put("listdata", rs.getString("listdata"));
				map.put("description", rs.getString("description"));
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(List);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}

}
