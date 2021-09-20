package com.oep.services.forms.course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.participant.oep_ParticipantRequest;


public class oep_CourseDAOImpl implements oep_ICourseDAO{

	private static Logger log = Logger.getLogger(oep_CourseDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private oep_Response response;
	
	@Autowired
	private oep_ResponseInfo responseInfo;
	
	
	@Override
	public oep_ResponseInfo savecoursemastermgmt(String uploadData, HttpServletRequest httpRequest) {
						
		try{
			
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_COURSE_NAME",request.getCoursename());
			inParamMap.put("P_COURSE_DESC",request.getCoursedesc());
			inParamMap.put("P_DURATION",request.getDuration());
			inParamMap.put("P_APPLICABLE_IC",request.getAppic());
			inParamMap.put("P_COURSE_DETAILS",request.getCoursedetails());
			inParamMap.put("P_COURSE_ID",request.getCourseid());
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_MODIFIED_BY",request.getUserid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",request.getStatus());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_COURSE_MODULE", inParamMap);
			log.info(resultMap);
			
			
			String resultType = (String)resultMap.get("out_result_type");
			String proddescpath = request.getFilename();
			log.info(proddescpath);
			int courseid = 0;
			if(resultMap.get("out_genrate_id") != null){
				courseid = Integer.parseInt((String) resultMap.get("out_genrate_id"));
			}
			
			log.info(courseid);
			if(courseid > 0)
			{
				String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
				String modulename1 = oep_UTIL.getmodulename("product", 1);	
				newpath1 = newpath1.concat(File.separator).concat(modulename1);
				
				log.info(newpath1);
				log.info(modulename1);
				log.info(newpath1);
								
				File uploadPath1 = new File(newpath1);
				if(!uploadPath1.exists())
				{
					uploadPath1.mkdirs();
				}
				newpath1 = newpath1.concat(File.separator).concat(courseid+".docx");
				log.info(newpath1);
				
				if(proddescpath!=null){
				
					  File oldfile = new File(proddescpath);
					  if(oldfile.exists()){
        	  		File file = new File(newpath1);
					if(file.exists())
					{
						java.nio.file.Path fileToDeletePath = Paths.get(newpath1);
						Files.delete(fileToDeletePath);
						
					}
					  }
					  log.info(newpath1);
				new File(proddescpath).renameTo(new File(newpath1));
				
				}					
				String desriptionPath = oep_UTIL.filepath(newpath1);
				log.info(desriptionPath);
			
			SimpleJdbcCall jdbcCALLAaftersave = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_COURSE_MODULE");
			Map<String, Object> inParamMap2 = new HashMap<String, Object>();
				
			inParamMap2.put("P_COURSE_NAME",request.getCoursename());
			inParamMap2.put("P_COURSE_DESC",request.getCoursedesc());
			inParamMap2.put("P_DURATION",request.getDuration());
			inParamMap2.put("P_APPLICABLE_IC",request.getAppic());
			inParamMap2.put("P_COURSE_DETAILS",desriptionPath);	
			inParamMap2.put("P_COURSE_ID",courseid);
			inParamMap2.put("P_CREATED_BY",request.getUserid());
			inParamMap2.put("P_MODIFIED_BY",request.getUserid());
			inParamMap2.put("P_OPRN","UPD");
			inParamMap2.put("P_STATUS",request.getStatus());
			
			log.info("inParamMap2         "+inParamMap2);
			
			SqlParameterSource in2 = new MapSqlParameterSource(inParamMap2);
			Map<String, Object> resultMap2 = jdbcCALLAaftersave.execute(in2);
			}
			response.setResponseObj(resultMap);

			
		    }catch(Exception e)
		      {
			    e.printStackTrace();
			
		      }
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	
	
	
	@Override
	public oep_ResponseInfo registerparticipantcourse(String registerData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(registerData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
			String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%*";
			StringBuilder builder = new StringBuilder();
			int count = 8;
			while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			
			String newPW = builder.toString();
			 String  encryptnewPW = oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", newPW);
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_ROLE_ID",request.getRoleid());
			inParamMap.put("P_USER_ID",request.getUserid());
			inParamMap.put("P_COURSE_ID",request.getCourseid());
			inParamMap.put("P_PASSWORD",encryptnewPW);
			inParamMap.put("P_PARTICIPANT_NAME",request.getParticipantname());
			inParamMap.put("P_PS_NUMBER",request.getPsnumber());
			inParamMap.put("P_EMAIL",request.getEmail());			
			inParamMap.put("P_APPLICABLE_IC",request.getApplicableic());
			inParamMap.put("P_JOB_CODE",request.getJobcode());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_COURSE_MANUAL_REGISTRATION", inParamMap);
			log.info(resultMap);
			String resulttype = (String) resultMap.get("out_result_type");
			
			if(resulttype.equals("S") && request.getOprn().equals("INS")){
				String mailid=request.getEmail();
				String msg="You have been sucessfully Registered  in L&T.Your Password is "+newPW;
				String subject = "Registration";
				oep_UTIL.sendMail(jdbcTemplate, mailid, msg, subject,"0");
				
			}
			log.info(resultMap.get("out_result_sendmail"));
			if(resultMap.get("out_result_sendmail").equals("yes")){
				String mailid=request.getEmail();
				String msg="You have been sucessfully Registered  in L&T.Your Password is "+newPW;
				String subject = "Registration";
				oep_UTIL.sendMail(jdbcTemplate, mailid, msg, subject,"0");
				
			}
		
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo savecourseschedule(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			String applncontect_URL = httpRequest.getScheme() + "://" +   
		            httpRequest.getServerName();
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_PROGRAM_NAME",request.getProgramname());
			inParamMap.put("P_FACULTY_NAME",request.getFacultyname());
			inParamMap.put("P_TOTAL_PARTICIPANTS",request.getTotalparticipants());
			
			if(request.getStartdate() != null && !request.getStartdate().equals("")){
				inParamMap.put("P_START_DATE",oep_UTIL.convertToNewSQLDate(request.getStartdate()));
			}else{
				inParamMap.put("P_START_DATE",oep_UTIL.convertToNewSQLDate("12/12/2019"));
			}
			
			if(request.getStartdate() != null && !request.getStartdate().equals("")){
				inParamMap.put("P_END_DATE",oep_UTIL.convertToNewSQLDate(request.getEnddate()));
			}else{
				inParamMap.put("P_END_DATE",oep_UTIL.convertToNewSQLDate("22/12/2019"));
			}
			
			inParamMap.put("P_START_HOUR",request.getStarthour());
			inParamMap.put("P_END_HOUR",request.getEndhour());
			inParamMap.put("P_START_MINUTE",request.getStartminute());
			inParamMap.put("P_END_MINUTE",request.getEndminute());
			inParamMap.put("P_START_FORMAT",request.getStartformat());
			inParamMap.put("P_END_FORMAT",request.getEndformat());
			
			/*inParamMap.put("P_START_DATE","2019-08-29");
			inParamMap.put("P_END_DATE","2019-08-31");
			*/
			inParamMap.put("P_APPLICABLE_IC",request.getAppic());
			inParamMap.put("P_CS_ID",request.getCsid());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",request.getStatus());
			inParamMap.put("P_HOST",applncontect_URL);
			log.info("inParamMap  "+inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_COURSE_SCHEDULING_MODULE", inParamMap);
			log.info(resultMap);
		
			if(request.getOprn().equals("INS")){
				
				oep_UTIL.sendMailfromSql((String) resultMap.get("out_genrate_mail_id"));
			}
			
			String content = (String) resultMap.get("out_genrate_mail_content");
			log.info(resultMap.get("out_genrate_mail_content"));
			
		    sendmailtoICadmin(content, httpRequest);
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Transactional
	public void sendmailtoICadmin(String content, HttpServletRequest httpRequest) throws Exception 
	{
		try
		{
			   
			   String query = "SELECT `email` FROM `users` WHERE `role` = 3 ";
					log.info(query);
					
					@SuppressWarnings("unchecked")
					List<Object> mailList = jdbcTemplate.query(query, new RowMapper() {
						@Override
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("email", rs.getString("email"));
							return map;
						}
					});
			   
		
					
			   Iterator itr = mailList.iterator();
					
			   log.info(mailList);
			   while(itr.hasNext()){
				   Map<String, Object> map = (Map<String, Object>) itr.next();		  
					
				   String mailcontent = content;
				   String subject = "course notification";
				   String mailid = (String) map.get("email");
				   
					LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
					inParamMap.put("P_CONTENT",mailcontent);
					inParamMap.put("P_SUBJECT",subject);
					inParamMap.put("P_MAIL_ID",mailid);
					inParamMap.put("P_OPRN","INS");
				
					log.info(inParamMap);

					Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SEND_ADMIN_COURSE_CERTIFICATE", inParamMap);
					
					log.info(resultMap);
					response.setResponseObj(resultMap);
					
					String imh_id = (String) resultMap.get("out_genrate_id");
					log.info("imh_id value is "+  imh_id);	
					
					oep_UTIL.sendMailfromSql(imh_id);			  
					 
			   }
			   
			   
		}
		catch( Exception e )
		{
			
			throw e;
		}
	}
	
	/*@Override
	public oep_ResponseInfo saveexammaster(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_EXAM_ID",request.getExamid());
			inParamMap.put("P_SUBJECT_ID",request.getSubjectid());
			inParamMap.put("P_DURATION_FROM",request.getDurationfrom());
			inParamMap.put("P_DURATION_TO",request.getDurationto());
			inParamMap.put("P_QUESTION_ID",request.getQuestionid());
			inParamMap.put("P_ATTEND",request.getAttend());
			inParamMap.put("P_EXAM_DATE",request.getExamdate());
			inParamMap.put("P_MARK",request.getMark());
			inParamMap.put("P_BATCH",request.getBatch());
			inParamMap.put("P_TEST_NAME",request.getTestname());
			inParamMap.put("P_PARTICIPANT_ID",request.getPartid());
			inParamMap.put("P_TEST_ID",request.getTestid());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_EXAM_MASTER", inParamMap);
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
	public oep_ResponseInfo savecourseyear(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_YEAR_NAME",request.getYearname());
			inParamMap.put("P_COURSE_NAME",request.getCoursename());
			inParamMap.put("P_COURSE_ID",request.getCourseid());
			inParamMap.put("P_YEAR_ID",request.getYearid());
			inParamMap.put("P_OPRN",request.getOprn());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_COURSE_YEAR", inParamMap);
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
	public oep_ResponseInfo savesubject(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_SUBJECT_NAME",request.getSubjectname());
			inParamMap.put("P_SUBJECT_ID",request.getSubjectid());
			inParamMap.put("P_DURATION",request.getDuration());
			inParamMap.put("P_COURSE_ID",request.getCourseid());			
			inParamMap.put("P_CREATED_BY",request.getUserid());
			inParamMap.put("P_DESCRIPTION",request.getDescription());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_STATUS",request.getStatus());
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SUBJECT_MODULE", inParamMap);
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
     public void downloadSampleFormat(String fileName,HttpServletRequest httpRequest, HttpServletResponse response) 
     
     {
	        int nofocolumn = Integer.parseInt(columnsheet);
			int extensionIndex = fileName.lastIndexOf(".");
			String uploadedFilename = fileName.substring(0,extensionIndex)+"_"+new Date(extensionIndex).toString()+fileName.substring(extensionIndex,fileName.length());
				try {
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=SampleExcel.xlsx");
	        
		
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet=(XSSFSheet) wb.createSheet("productlist");
		
				Row rowSample = sheet.createRow(0);
				int columnCount = 0;
				
				String columnNames = ("Question_id,Question *,option_1 *,option_2*,option_3*,option_4 *,answer");
				log.info(columnNames);
				StringTokenizer columnToken = new StringTokenizer(columnNames,",");
				while(columnToken.hasMoreTokens())
				{
					 
					String columnName = columnToken.nextToken();
					
					Cell cell = rowSample.createCell(columnCount);
					cell.setCellValue(columnName);
					XSSFCellStyle cellStyle = wb.createCellStyle();
					cell.setCellStyle((org.apache.poi.ss.usermodel.CellStyle) cellStyle);
					columnCount++;
					
				}
				
			
				
				for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				     sheet.autoSizeColumn(columnIndex);
				}
				
				wb.write(response.getOutputStream());
				
	        } catch (Exception e) {
	           
	        	e.printStackTrace();
	        }
  }
	 */
	 
	 
	 @Override
		public oep_ResponseInfo savetestschedule(String uploadData, HttpServletRequest httpRequest) {
					
			try{
				
				oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
											
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_TEST_STARTED",request.getStarttest());
				inParamMap.put("P_QUESTION_ID",request.getTestid());
				inParamMap.put("P_BATCH",request.getBatch());
				inParamMap.put("P_TS_ID", request.getTsid());		
				inParamMap.put("P_TEST_DATE",oep_UTIL.convertToNewSQLDate(request.getTestdate()));				
				inParamMap.put("P_START_HOUR",request.getStarthour());
				inParamMap.put("P_START_FORMAT",request.getStartformat());
				inParamMap.put("P_START_MINUTE",request.getStartminute());
				inParamMap.put("P_DURATION_HOUR",request.getDurationhour());
				inParamMap.put("P_DURATION_MINUTE",request.getDurationminute());				
				inParamMap.put("P_OPRN",request.getOprn());
				inParamMap.put("P_CREATED_BY",request.getUserid());
				inParamMap.put("P_ADMINISTRATED_BY",request.getUserid());
				inParamMap.put("P_STATUS",request.getStatus());
				log.info(inParamMap);

				Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_TEST_SCHEDULE", inParamMap);
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
		public oep_ResponseInfo savetestadministrator(String uploadData, HttpServletRequest httpRequest) {
					
			try{
				
				oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
				HttpSession session = httpRequest.getSession();
				
				List<oep_CourseRequest> itemList = oep_UTIL.convertJSONArraytoList(request.getParticipantid(),
						"com.oep.services.forms.course.oep_CourseRequest");
				log.info(itemList);	
				
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_TEST_STARTED",request.getStarttest());
				inParamMap.put("P_QUESTION_ID",request.getTestid());
				inParamMap.put("P_TS_ID", request.getTsid());
				inParamMap.put("P_BATCH",request.getBatch());
				inParamMap.put("P_TEST_DATE",oep_UTIL.convertToNewSQLDate(request.getTestdate()));		
			/*	inParamMap.put("P_START_TIME",request.getStarttime());
				inParamMap.put("P_END_TIME",request.getEndtime());*/
				inParamMap.put("P_OPRN",request.getOprn());
				inParamMap.put("P_CREATED_BY",request.getUserid());
				inParamMap.put("P_ADMINISTRATED_BY",request.getUserid());
				inParamMap.put("P_STATUS",request.getStatus());
				
				inParamMap.put("P_START_HOUR",request.getStarthour());
				inParamMap.put("P_START_FORMAT",request.getStartformat());
				inParamMap.put("P_START_MINUTE",request.getStartminute());
				inParamMap.put("P_DURATION_HOUR",request.getDurationhour());
				inParamMap.put("P_DURATION_MINUTE",request.getDurationminute());

				log.info(inParamMap);

				Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SAVE_TEST_ADMINISTRATOR", inParamMap);
				log.info(resultMap);	
				
				String partid = "";	
				Iterator<oep_CourseRequest> itr = itemList.iterator();
				while(itr.hasNext())
				{
					oep_CourseRequest obj = itr.next();
					obj.setTestid(request.getTestid());	
					obj.setTsid(request.getTsid());	
					log.info(obj.getPartid()+"  "+obj.getUserid()+"  "+obj.getTsid()+"   "+obj.getTestid()+"  ");
					
				}
				String applncontect_URL ="tsid="+request.getTsid();

				applncontect_URL = "http://139.59.62.244/#/login?"+applncontect_URL;
				savetestparticipants(itemList, partid,applncontect_URL);
				response.setResponseObj(resultMap);
					
				
			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}


		@Transactional
		public void savetestparticipants( final List<oep_CourseRequest> list,final String partid,final String url) 
		{
			try
			{
				   log.info("HELLO");
				   jdbcTemplate.batchUpdate("{call P_OEP_SCHEDULED_TEST_PARTICIPANTS( ?,?,?,?,?)}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return list.size();
					}
					String id = partid;
					public void setValues(PreparedStatement ps, int i) throws SQLException 
						{
								oep_CourseRequest item = list.get(i);
							
							ps.setString(1, item.getTsid());
							ps.setString(2,item.getPartid());	
							ps.setString(3,item.getUserid());	
							ps.setString(4,url);
							ps.setString(5,"INS");
							log.info(item.getTsid()); 
							log.info(item.getPartid());
							log.info(item.getUserid());
						}
					});
				   
				   Iterator<oep_CourseRequest> itr = list.iterator();
				   while(itr.hasNext()){
					   oep_CourseRequest oepReq = itr.next();
					   
					   String query = "SELECT `email` FROM `users` WHERE `userid` = "+oepReq.getUserid();
						log.info(query);
						
						@SuppressWarnings("unchecked")
						List<Object> mailList = jdbcTemplate.query(query, new RowMapper() {
							@Override
							public Object mapRow(ResultSet rs, int arg1) throws SQLException {
								
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("email", rs.getString("email"));
								return map;
							}
						});
						
						if(mailList != null && mailList.size() > 0){
							Map<String, String> map = (Map<String, String>) mailList.get(0);
							String msg = "You have received a Test Request . Please click on the link  to start the test."+url;
							oep_UTIL.sendMail(jdbcTemplate, map.get("email"), msg, "Test Request", "0");
						}
						
						
				   }
				   
				   
			}
			catch( Exception e )
			{
				
				throw e;
			}
		}
		
		
		
		@Override
		public oep_ResponseInfo updatequestiondetails(String uploadData,HttpServletRequest httpRequest) {
				
			try{
			
				oep_updateQuestionRequest request = (oep_updateQuestionRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_updateQuestionRequest.class);
				HttpSession session = httpRequest.getSession();
				log.info(request.getQdetails());
				
				List<oep_updateQuestionRequest> itemList = oep_UTIL.convertJSONArraytoList(request.getQdetails(),
						"com.oep.services.forms.course.oep_updateQuestionRequest");
				
				
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_SUBJECT_ID",request.getSubjectid());
				inParamMap.put("P_BATCH",request.getBatch());
				inParamMap.put("P_TESTNAME",request.getTestname());
				inParamMap.put("P_TEST_ID",request.getTestid());
				inParamMap.put("P_CREATED_BY",request.getUserid());
				inParamMap.put("P_STATUS",request.getStatus());
				inParamMap.put("P_OPRN",request.getOprn());
				log.info(inParamMap);

				Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_QUESTION_MODULE", inParamMap);
				log.info(resultMap);
				log.info(itemList);
				
				String question_id = "";
				if(resultMap.get("out_result_type").equals("S")){
					question_id = (String) resultMap.get("out_genrate_id");
				}
				//importonlineproductitems(excelDatas);
				Iterator<oep_updateQuestionRequest> itr = itemList.iterator();
				while(itr.hasNext())
				{
					log.info("sdfg");
					oep_updateQuestionRequest obj = itr.next();					
						
					obj.setCsid(question_id);						
				
					log.info(obj.getQuestion()+"");
					log.info(obj.getOption_3()+"");
					log.info(obj.getQuestion_id()+"");
					log.info(question_id);
					
				}
				updatequesdetails(itemList);
				
               //response.setResponseObj(resultMap);
               response.setResponseType("S");
       		   response.setResponseObj(resultMap);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		
		@Transactional
		public void updatequesdetails( final List<oep_updateQuestionRequest> list) 
		{
			try
			{
				   log.info("HELLO");
				   
				   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_UPDATE_QUESTION_DETAILS( ?,?,?,?,?,?,?,?,?,?,?,?)}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return list.size();
					}
					
					public void setValues(PreparedStatement ps, int i) throws SQLException 
				{
						oep_updateQuestionRequest item = list.get(i);
						log.info(list);
					ps.setString(1,item.getNo());
					ps.setString(2,item.getQuestion());
					ps.setString(3,item.getType());
					ps.setString(4,item.getOption_1());
					ps.setString(5,item.getOption_2());
					ps.setString(6,item.getOption_3());
					ps.setString(7,item.getOption_4());
					ps.setString(8,item.getAnswer());
					//ps.setString(9,item.getImage());
					ps.setString(9,item.getMark());
					ps.setString(10,item.getQuestion_id());
					ps.setString(11,"UPD");
					ps.setString(12,item.getCsid());
					
					
					
					log.info(item.getNo());
					log.info(item.getQuestion());
					log.info(item.getType());
					log.info(item.getOption_1());
					log.info(item.getOption_2());
					log.info(item.getOption_3());
					log.info(item.getOption_4());
					log.info(item.getAnswer());
					//log.info(item.getImage());
					log.info(item.getMark());
					log.info(item.getQuestion_id());
					log.info(item.getCsid());
					
					
				}
			});
			}
			catch( Exception e )
			{
				
				throw e;
			}
		}
	 
	@Override
		public oep_ResponseInfo savequestion(String uploadData,HttpServletRequest httpRequest) {
				
			try{
			
				oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_CourseRequest.class);
				HttpSession session = httpRequest.getSession();
				
				
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_SUBJECT_ID",request.getSubjectid());
				inParamMap.put("P_BATCH",request.getBatch());
				inParamMap.put("P_TESTNAME",request.getTestname());
				inParamMap.put("P_TEST_ID",request.getTestid());
				inParamMap.put("P_CREATED_BY",request.getUserid());
				inParamMap.put("P_STATUS",request.getStatus());
				inParamMap.put("P_OPRN",request.getOprn());
				log.info(inParamMap);

				Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_QUESTION_MODULE", inParamMap);
				log.info(resultMap);
				
				String question_id = "";
				if(resultMap.get("out_result_type").equals("S")){
					question_id = (String) resultMap.get("out_genrate_id");
				}
				
				String imagePath="";
				String filepath = request.getFilename(); 
				log.info(filepath);
				
					int productid = 0;
					Random r = new Random( System.currentTimeMillis() );
					productid = 10000 + r.nextInt(20000);
				   log.info(productid);
				
				    String newpath = oep_UTIL.getserverfilepath(httpRequest);
				   log.info(newpath);
				    
					String modulename = oep_UTIL.getmodulename("productimport", 1);
					log.info(modulename);
					
					newpath = newpath.concat(File.separator).concat(modulename);
					File uploadPath = new File(newpath);
					if(!uploadPath.exists())
					{
						uploadPath.mkdirs();
					}
					String productidStr =  String.valueOf(productid);
					log.info("productidStr"+productidStr);
					newpath = newpath.concat(File.separator).concat(productidStr+".xlsx"); 
					log.info(newpath);
					
					new File(filepath).renameTo(new File(newpath));
				log.info(newpath);
					imagePath = oep_UTIL.filepath(newpath);
				log.info(imagePath);
					
					
					int digits = 0;
					Random s = new Random( System.currentTimeMillis() );
					  digits = 10000 + s.nextInt(20000);
					int fileSeq = digits;
					//String fileSeq ="143";
					log.info("fdsgbdsfgdsf"+fileSeq);
					String filename = productidStr+".xlsx";
					log.info(filename);
					StringTokenizer token = new StringTokenizer(filename,".");
					session.setAttribute("productimportid", fileSeq);
					
					
					int rowCount = 1;
					FileInputStream fileInputStream = new FileInputStream(newpath);
					XSSFWorkbook workbookValidation = new XSSFWorkbook(fileInputStream);
					log.info(workbookValidation.getSheet("productlist"));
					
					if(workbookValidation.getSheet("productlist") != null && !workbookValidation.getSheet("productlist").equals(""))
					{
						XSSFSheet worksheetValidation = workbookValidation.getSheet("productlist");
						XSSFRow row1Validation = worksheetValidation.getRow(0);
						Iterator rowsValidation = worksheetValidation.rowIterator();
						XSSFRow rowValidation = (XSSFRow) rowsValidation.next();
						
						
						
						if(rowValidation.getCell(0, Row.RETURN_BLANK_AS_NULL) != null && rowValidation.getCell(1, Row.RETURN_BLANK_AS_NULL) != null  &&
								rowValidation.getCell(2, Row.RETURN_BLANK_AS_NULL) != null  && rowValidation.getCell(3, Row.RETURN_BLANK_AS_NULL) != null &&
								rowValidation.getCell(4, Row.RETURN_BLANK_AS_NULL) != null  && rowValidation.getCell(5, Row.RETURN_BLANK_AS_NULL) != null &&
								rowValidation.getCell(6, Row.RETURN_BLANK_AS_NULL) != null  && rowValidation.getCell(7, Row.RETURN_BLANK_AS_NULL) != null &&
								rowValidation.getCell(8, Row.RETURN_BLANK_AS_NULL) != null && rowValidation.getCell(9, Row.RETURN_BLANK_AS_NULL) != null ){
							
							String headerCell1 = (rowValidation.getCell(0, Row.RETURN_BLANK_AS_NULL)).getStringCellValue();
							if((rowValidation.getCell(0, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("S.No.") && (rowValidation.getCell(1, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("Question")  &&
									(rowValidation.getCell(2, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("Question type")   && (rowValidation.getCell(3, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("option_1") &&
									(rowValidation.getCell(4, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("option_2")  && (rowValidation.getCell(5, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("option_3") &&
									(rowValidation.getCell(6, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("option_4")  && (rowValidation.getCell(7, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("answer") &&
									(rowValidation.getCell(8, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("Question image if Available") && (rowValidation.getCell(9, Row.RETURN_BLANK_AS_NULL)).getStringCellValue().equals("Score for correct answer") ){	
						
						
					List<Object> excelDatas = new ArrayList<Object>();
					log.info(request.getOprn());
					if(request.getOprn().equals("INS")){
						//XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);	
						log.info(workbookValidation.getSheet("productlist"));
						XSSFSheet worksheet = workbookValidation.getSheet("productlist");					
						log.info(worksheet);
						XSSFRow row1 = worksheet.getRow(0);				
						XSSFCell cellA1 = row1.getCell((short) 0);
						String a1Val = cellA1.getStringCellValue();
						int totalCellCount = 0;
						
					Iterator rows = worksheet.rowIterator();
					log.info("row "+rows);
					while (rows.hasNext()) {
					
						XSSFRow row = (XSSFRow) rows.next();
						
						
						//log.info("row count"+rowCount);
						if(row.getRowNum() ==0)
						     continue;
						if(rowCount > 0)
						{
							Iterator cells = row.cellIterator();
							int cellCount = 0;
							Map<String, Object> map = new HashMap<String, Object>();
							
							int lastColumn = 10;
						    {
						    	for (int cn = 0; cn < 10; cn++) {
						          Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
						        
						          int myno = cn+1;
						          if (c == null) {
						        	  map.put("cell"+myno, "-");
						        	//  log.info(c);
						          } else {
						        	  switch(c.getCellType())
					                    {
					                    case 0:
					                    	
					                    	if (HSSFDateUtil.isCellDateFormatted(c)) {
								                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								                  try {
								                    String date = dateFormat.format(c.getDateCellValue());
								                    map.put("cell"+myno, date);
								                    break;
								                  } catch (Exception e) {
								                    e.printStackTrace();
								                    break;
								                  }
								                  
								                }else{
								                	int value = new Double(c.getNumericCellValue()).intValue();
								                	
							                    	map.put("cell"+myno,  String.valueOf(value));
							                    	break;
							                    	}
					                    case 1:
					                    
					                    	map.put("cell"+myno, c.getStringCellValue());
					                    }
						          }
						       }
						    	 
						       map.put("cell"+(lastColumn + 1), fileSeq);
				               map.put("cell"+(lastColumn + 2), request.getUserid());
				               map.put("cell"+(lastColumn + 3), question_id);
				               excelDatas.add(map);  
				              // log.info(session.getAttribute("uid"));
				              // log.info(map);
				              // log.info(question_id);
						}
							
		                    rowCount++;
					}
					}
				
					importonlineproductitems(excelDatas);
				    log.info(excelDatas);
			}else{
				XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);		
				log.info(workbook);
				XSSFSheet worksheet = workbook.getSheet("data");					
				log.info(worksheet);
				XSSFRow row1 = worksheet.getRow(0);				
				XSSFCell cellA1 = row1.getCell((short) 0);
				String a1Val = cellA1.getStringCellValue();
				int totalCellCount = 0;
			
				Iterator rows = worksheet.rowIterator();
				log.info("row "+rows);
				while (rows.hasNext()) {
				
					XSSFRow row = (XSSFRow) rows.next();
					log.info("row count"+rowCount);
					log.info(row.getRowNum());
					if(row.getRowNum() ==0)
					     continue;
					if(rowCount > 0)
					{
						Iterator cells = row.cellIterator();
						int cellCount = 0;
						Map<String, Object> map = new HashMap<String, Object>();
						
						int lastColumn = 11;
					    {
					    	for (int cn = 0; cn < 11; cn++) {
					          Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
					        
					          int myno = cn+1;
					          if (c == null) {
					        	  map.put("cell"+myno, "0");
					        	//  log.info(c);
					          } else {
					        	  switch(c.getCellType())
				                    {
				                    case 0:
				                    	
				                    	if (HSSFDateUtil.isCellDateFormatted(c)) {
							                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							                  try {
							                    String date = dateFormat.format(c.getDateCellValue());
							                    map.put("cell"+myno, date);
							                    break;
							                  } catch (Exception e) {
							                    e.printStackTrace();
							                    break;
							                  }
							                  
							                }else{
							                	int value = new Double(c.getNumericCellValue()).intValue();
							                	
						                    	map.put("cell"+myno,  String.valueOf(value));
						                    	break;
						                    	}
				                    case 1:
				                    
				                    	map.put("cell"+myno, c.getStringCellValue());
				                    }
					          }
					       }
					    	 
					       map.put("cell"+(lastColumn + 1), fileSeq);
			               map.put("cell"+(lastColumn + 2), request.getUserid());
			               map.put("cell"+(lastColumn + 3), question_id);
			               excelDatas.add(map);  
			              
					}
						
	                    rowCount++;
				}
				}
			
				updatequestiondetailsitems(excelDatas);
			    log.info(excelDatas);
		
			}
			String selectQuery = "SELECT CASE WHEN `status` = 'Success' THEN "
					+ "CONCAT('<label style=\"color:green;font-size:19px !important;\"><i class=\"mdi-action-check-circle\"></i> ',"
					+ "`status`,'</label>') ELSE CONCAT('<label style=\"color:red;font-size:19px !important;\"><i class=\"mdi-alert-error\"></i>  ',"
					+ "`status`,'</label>')END AS uploadstatus,`record_seq_no`,`question_id`,"
					+ "`error_msg` errormsg,`seq_no`,`status` uploadstatusdesc,`serialno` serialno FROM `oep_question_import_status` WHERE `seq_no` = "+fileSeq;		
			
			        log.info("selectQuery  "+selectQuery);
					
					@SuppressWarnings("unchecked")
					List<Object> produtimportstatuslist = jdbcTemplate.query(selectQuery, new RowMapper() {
						
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("uploadstatus", rs.getString("uploadstatus"));
							map.put("recindex", rs.getString("seq_no"));
							map.put("out_result_msg", rs.getString("errormsg"));
							map.put("uploadstatusdesc", rs.getString("uploadstatusdesc"));
							map.put("recordseqno", rs.getString("record_seq_no"));
							map.put("questionid", rs.getString("question_id"));
							map.put("serialno", rs.getString("serialno"));
							return map;
						}
					});
					log.info(produtimportstatuslist);
					Iterator produtimportstatuslistITR = produtimportstatuslist.iterator();
					while(produtimportstatuslistITR.hasNext())
					{
						
					   Map<String,Object>importlistmap = (Map<String, Object>) produtimportstatuslistITR.next();
						
						String questionid =(String) importlistmap.get("questionid");
					    int indexId = Integer.parseInt((String) importlistmap.get("recordseqno"));
						//indexId = indexId-1;
						
						String status =  (String) importlistmap.get("uploadstatusdesc");						
						
							if(status.equals("Success")){
							
							String imagePath1 = "";		
							
							Map<String, Object> map = (Map<String, Object>) excelDatas.get(indexId);
							
						    String img21name = map.get("cell9").toString();		
						    if(!img21name.equals("-")){
							String oldimagefilepath = oep_UTIL.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator).concat(request.getZipfilename()).concat(File.separator)+img21name;
							log.info("oldimagefilepath IS  "+oldimagefilepath);
							String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
							log.info("newpath1 IS  "+newpath1);
							modulename = oep_UTIL.getmodulename("product", 1);
							log.info("modulename IS  "+modulename);
							newpath1 = newpath1.concat(File.separator).concat(modulename);
							log.info("newpath1 IS  "+newpath1);
							uploadPath = new File(newpath1);
							if(!uploadPath.exists())
							{
								uploadPath.mkdirs();
							}
							newpath1 = newpath1.concat(File.separator).concat(questionid+".jpg");
							new File(oldimagefilepath).renameTo(new File(newpath1));
							//log.info("newpath1 IS  "+newpath1);
							imagePath1 = oep_UTIL.filepath(newpath1);
							//log.info("newpath1 IS  "+imagePath1);
							
							
							
							SimpleJdbcCall jdbcCALLAaftersave = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_SAVE_QUESTION_IMAGES");
							Map<String, Object> inParamMap1 = new HashMap<String, Object>();
							inParamMap1.put("P_IMAGEPATH",imagePath1);
							inParamMap1.put("P_QUESTION_ID",questionid);
							inParamMap1.put("P_OPRN", "UPD");
							
							log.info("inParamMap1         "+inParamMap1);
							SqlParameterSource in2 = new MapSqlParameterSource(inParamMap1);
							Map<String, Object> resultMap2 = jdbcCALLAaftersave.execute(in2);
							log.info("resultMap2         "+resultMap2);
							}
							}								
					} 
					String selectQuery1 = "SELECT CASE WHEN `status` = 'Success' THEN "
							+ "CONCAT('<label style=\"color:green;font-size:19px !important;\"><i class=\"mdi-action-check-circle\"></i> ',"
							+ "`status`,'</label>') ELSE CONCAT('<label style=\"color:red;font-size:19px !important;\"><i class=\"mdi-alert-error\"></i>  ',"
							+ "`status`,'</label>')END AS uploadstatus,`record_seq_no`,`question_id`,"
							+ "`error_msg` errormsg,`seq_no`,`status` uploadstatusdesc,`serialno` serialno  FROM `oep_question_import_status` WHERE `seq_no` = "+fileSeq;		
					
					        log.info("selectQuery  "+selectQuery1);
							
							@SuppressWarnings("unchecked")
							List<Object> produtimportstatuslist1 = jdbcTemplate.query(selectQuery1, new RowMapper() {
								
								public Object mapRow(ResultSet rs, int arg1) throws SQLException {
								
									HashMap<String, String> map = new HashMap<String, String>();
									map.put("uploadstatus", rs.getString("uploadstatus"));
									map.put("recindex", rs.getString("seq_no"));
									map.put("out_result_msg", rs.getString("errormsg"));
									map.put("uploadstatusdesc", rs.getString("uploadstatusdesc"));
									map.put("recordseqno", rs.getString("record_seq_no"));
									map.put("questionid", rs.getString("question_id"));
									map.put("serialno", rs.getString("serialno"));
									return map;
								}
							});
				
	
					response.setResponseType("S");
					response.setResponseObj(produtimportstatuslist1);
					
			}else{
				response.setResponseType("F");
				response.setResponseObj("Question bank excel is not valid. Try with 'Download Sample Format' Excel and upload");
			}
					}	else{
						response.setResponseType("F");
						response.setResponseObj("Question bank excel is not valid.Try with  'Download Sample Format' Excel and upload");
					}
					}else{
						response.setResponseType("F");
						response.setResponseObj("Question bank excel is not valid. Try with  'Download Sample Format' Excel and upload");
					}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				response.setResponseType("F");
				response.setResponseObj("File not found");
			} catch (IOException e1) {
				e1.printStackTrace();
				response.setResponseType("F");
				response.setResponseObj("File not found");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				response.setResponseType("F");
				response.setResponseObj("File not found");
			} catch(Exception e){
				response.setResponseType("F");
				response.setResponseObj("Question bank excel is not valid. Try with 'Download Sample Format' Excel and upload");
			}
	
			responseInfo.setInventoryResponse(response);
			return responseInfo;
	}
		
		@Transactional
		public void importonlineproductitems( final List<Object> list) 
		{
			try
			{
				StringBuilder parametersSB = new StringBuilder();
				
				int totalCellCount = 13;
				
				for(int cell = 1;cell <= totalCellCount;cell++)
				{
					if(cell != totalCellCount+1)
					{
						parametersSB.append("?,");
					}
					else
					{
						parametersSB.append("?");
					}
				}
						parametersSB.append("?");
				
				
				
					jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_IMPORT_QUESTION( "+parametersSB+" )}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return list.size();
					}
		
				public void setValues(PreparedStatement ps, int index) throws SQLException 
				{
					
					
					Map<String, Object> map = (Map<String, Object>) list.get(index);
					          for(int cell = 1;cell <= 13;cell++)
					        {
						 if(map.get("cell"+cell) == null)
						 {
							ps.setString(cell, "string");
						}
						 else
						 {
							ps.setString(cell, map.get("cell"+cell).toString());
							
			             }
					        }
							 ps.setInt(14, index);
							
				 }
					
					
				});
			}
			
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		
		@Transactional
		public void updatequestiondetailsitems( final List<Object> list) 
		{
			try
			{
				StringBuilder parametersSB = new StringBuilder();
				
				int totalCellCount = 14;
				
				for(int cell = 1;cell <= totalCellCount;cell++)
				{
					if(cell != totalCellCount+1)
					{
						parametersSB.append("?,");
					}
					else
					{
						parametersSB.append("?");
					}
				}
						parametersSB.append("?");
				
				
				
					jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_UPDATE_QUESTION( "+parametersSB+" )}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return list.size();
					}
		
				public void setValues(PreparedStatement ps, int index) throws SQLException 
				{
					
					
					Map<String, Object> map = (Map<String, Object>) list.get(index);
					          for(int cell = 1;cell <= 14;cell++)
					        {
						 if(map.get("cell"+cell) == null)
						 {
							ps.setString(cell, "string");
						}
						 else
						 {
							ps.setString(cell, map.get("cell"+cell).toString());
							
			             }
					        }
							 ps.setInt(15, index);
							
				 }
					
					
				});
			}
			
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		
		
		@Override
		public oep_ResponseInfo getCoursedetails(String courseid) {
			
			String query = "SELECT `course_name`,`status`,`course_id`,`course_desc`,`duration`,`applicable_ic`,`course_details`,`created_date`"
					+ " FROM `course_master` WHERE `course_id` = "+courseid+" ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(query, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					int count = 1;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("status", rs.getString("status"));
					map.put("course_id", rs.getString("course_id"));
					map.put("course_name", rs.getString("course_name"));
					map.put("course_desc", rs.getString("course_desc"));
					map.put("details", rs.getString("course_details"));
					map.put("created_date", rs.getString("created_date"));
					map.put("duration", rs.getString("duration"));
					map.put("applicable_ic", rs.getString("applicable_ic"));
					map.put("index",count);
					count++;
					return map;
					
				}
			});
		
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}

		
		@Override
		public oep_ResponseInfo getFacultyTestscheduleList(String userid) {
			
			String query = "SELECT a.`id`,a.`ques_master_id`,`is_test_started`,a.`batch`,DATE_FORMAT(testdate,'%e/%c/%Y')testdate, "
					+ "`start_time`,`end_time`,a.`status`,b.`test_name`,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN "
					+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am'  "
					+ "  THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1),' ',1)END AS starthours, "
					+ " CASE WHEN  SUBSTRING_INDEX(`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) + 12  "
					+ "  WHEN SUBSTRING_INDEX(`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1)"
					+ " END AS endhours,      SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
					+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', -1), ' ', 1)endminutetime FROM `test_schedule` a  "
					+ " JOIN `question_master` b ON a.`ques_master_id` = b.`id`  JOIN `faculty_master`c ON c.`main_subject`=b.`sub_id`"
					+ " WHERE `userid`=  "+userid+" ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> List = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", rs.getString("id"));
					map.put("testid", rs.getString("ques_master_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					map.put("testname", rs.getString("test_name"));
					map.put("starthours", rs.getString("starthours"));
					map.put("endhours", rs.getString("endhours"));
					map.put("startminutetime", rs.getString("startminutetime"));
					map.put("endminutetime", rs.getString("endminutetime"));
					map.put("isstatus", rs.getString("status"));
					map.put("isteststarted", rs.getString("is_test_started"));
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					if(rs.getString("is_test_started")!= null && rs.getString("is_test_started").equals("1")){
						map.put("teststarted", "Test Started");
					}else{
						map.put("teststarted", "Not started");
					}
					
					map.put("index",count);
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
		public oep_ResponseInfo getQuestiondetails(String questionid) {
			
			String query = "SELECT `question`,`option_1`,`option_2`,`option_3`,`option_4`,`answer`,`test_id`,"
					+ " a.`batch`,DATE_FORMAT(testdate,'%c/%e/%Y')testdate,`image`,`question_type` "
					+ " FROM `question_master` a JOIN `question_details` b ON b.`ques_master_id` = a.`id`"
					+ " LEFT JOIN `test_schedule` c ON c.`ques_master_id` = a.`id` "
					+ " WHERE a.`id` = "+questionid+"  ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
				
					map.put("question", rs.getString("question"));
					map.put("option1", rs.getString("option_1"));
					map.put("option2", rs.getString("option_2"));
					map.put("option3", rs.getString("option_3"));
					map.put("option4", rs.getString("option_4"));
					map.put("answer", rs.getString("answer"));
					map.put("testid", rs.getString("test_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("image", rs.getString("image"));
					map.put("questiontype", rs.getString("question_type"));
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
		public oep_ResponseInfo getQuestionbankdetails(String questionid) {
			
		/*	String query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`sub_name` FROM `question_master` a "
					+ " JOIN `subject_master` b ON a.`sub_id` = b.`sub_id` WHERE a.`id` = "+questionid+"  ";*/
			
			String query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`course_name` FROM `question_master` a "
					+ " JOIN `course_master` b ON a.`sub_id` = b.`course_id` WHERE a.`id` = "+questionid+"  ";
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> questionmasterList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
				
					map.put("id", rs.getString("id"));
					map.put("batch", rs.getString("batch"));
					map.put("testid", rs.getString("test_id"));
					map.put("testname", rs.getString("test_name"));
					/*map.put("subjectid", rs.getString("sub_id"));
					map.put("subjectname", rs.getString("sub_name"));*/
					
					map.put("courseid", rs.getString("sub_id"));
					map.put("coursename", rs.getString("course_name"));

										
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
				}
			});
			String questiondetailsquery="SELECT * FROM `question_details` WHERE `ques_master_id`="+questionid;
			
			@SuppressWarnings("unchecked")
			List<Object> questiondetailsList = jdbcTemplate.query(questiondetailsquery, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("No",count);
					map.put("Question", rs.getString("question"));
					map.put("Type", rs.getString("question_type"));
					map.put("Option_1", rs.getString("option_1"));
					map.put("Option_2", rs.getString("option_2"));
					map.put("Option_3", rs.getString("option_3"));
					map.put("Option_4", rs.getString("option_4"));
					map.put("Answer", rs.getString("answer"));
					map.put("Image", rs.getString("image"));
					map.put("Mark", rs.getString("mark"));
					map.put("Question_id", rs.getString("question_id"));
					
					count++;
					return map;
				}
			});
			response.setResponseType("S");
			Map<String, Object> detailsmap = new HashMap<String, Object>();
			detailsmap.put("questionmasterList", questionmasterList);
			detailsmap.put("questiondetailsList", questiondetailsList);
			response.setResponseObj(detailsmap);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		@Override
		public oep_ResponseInfo getCourseList() {
			
			String query = "SELECT `course_id`,`course_name`,`course_desc`,`status`,`duration` FROM `course_master` WHERE `status` = 1   ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> courseList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("coursedesc", rs.getString("course_desc"));
					map.put("courseduration", rs.getString("duration"));
					map.put("action","<mat-icon>view</mat-icon> <mat-icon>edit</mat-icon> <mat-icon>delete</mat-icon>");
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(courseList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		@Override
		public oep_ResponseInfo getallCourseList() {
			
			String query = "SELECT `course_id`,`course_name`,`course_desc`,`status`,`duration` FROM `course_master`    ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> courseList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("coursedesc", rs.getString("course_desc"));
					map.put("courseduration", rs.getString("duration"));
					map.put("action","<mat-icon>view</mat-icon> <mat-icon>edit</mat-icon> <mat-icon>delete</mat-icon>");
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(courseList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		/*@Override
		public oep_ResponseInfo getSubjectList() {
			
			String query = "SELECT `sub_id`,`sub_name`,a.`course_id`,a.`duration`,`course_name`,a.`status` FROM `subject_master`a "
					+ " JOIN `course_master` b ON b.`course_id` = a.`course_id`   ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> subjectList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("subjectid", rs.getString("sub_id"));
					map.put("subjectname", rs.getString("sub_name"));
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("index",count);
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("action","<mat-icon>view</mat-icon> <mat-icon>edit</mat-icon> <mat-icon>delete</mat-icon>");
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(subjectList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		
		
		@Override
		public oep_ResponseInfo getcheckedSubjectList() {
			
			String query = "SELECT `sub_id`,`sub_name`,a.`course_id`,a.`duration`,`course_name`,a.`status` FROM `subject_master`a "
					+ " JOIN `course_master` b ON b.`course_id` = a.`course_id`  WHERE a.`status`  = 1 ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> subjectList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("subjectid", rs.getString("sub_id"));
					map.put("subjectname", rs.getString("sub_name"));
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("index",count);
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("action","<mat-icon>view</mat-icon> <mat-icon>edit</mat-icon> <mat-icon>delete</mat-icon>");
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(subjectList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}

*/
		
		@Override
		public oep_ResponseInfo getScheduledetails(String scheduleid) {
			
			/*String query = " SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(start_date,'%m/%d/%Y')start_date,`course_name`, `username`,"
					+ "DATE_FORMAT(end_date,'%m/%d/%Y')end_date, `total_participants_allowed`,a.`applicable_ic`,"
					+ "`faculty_name`,a.`status`,DATE_FORMAT(`start_date`, '%a %b %e %Y')date1,"
					+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')date2,(COALESCE(`total_participants_allowed`,0) - COALESCE(COUNT(d.`course_id`),0))seatavailable FROM `course_scheduling`a"
					+ " JOIN `course_master` b ON a.`program_name` = b.`course_id`"
					+ " JOIN `faculty_master`c ON c.`faculty_id` = a.`faculty_name` LEFT JOIN `participants_registration_course_details` d ON b.`course_id`=d.`course_id` WHERE  `cs_id` = "+scheduleid+" ";
			log.info(query); saras*/
			
			String query = " SELECT `cs_id`,`program_name`, HOUR(`start_time`)starthour,MINUTE(`end_time`)endminute,"
					+ " SUBSTRING_INDEX(a.`start_time`,' ',-1)startformatt,SUBSTRING_INDEX(a.`end_time`,' ',-1)endformatt,"
					+  " GROUP_CONCAT(f.`username`,' ','\n ')partname, HOUR(`end_time`)endhour,MINUTE(`start_time`)startminute,"
					+ " DATE_FORMAT(start_date,'%m/%d/%Y')start_date,DATE_FORMAT(start_date,'%d-%m-%Y')date_start,`course_name`, c.`username`,"
					+ " DATE_FORMAT(end_date,'%m/%d/%Y')end_date, DATE_FORMAT(end_date,'%d-%m-%Y')date_end, "
					+ " `total_participants_allowed`,a.`applicable_ic`,`faculty_name`,a.`status`,"
					+ " DATE_FORMAT(`start_date`, '%a %b %e %Y')date1, DATE_FORMAT(`end_date`, '%a %b %e %Y')date2,(COALESCE(`total_participants_allowed`,0) - COALESCE(COUNT(e.`course_id`),0))seatavailable  "
					+ " FROM `course_scheduling`a JOIN `course_master` b ON a.`program_name` = b.`course_id` "
					+ " JOIN `faculty_master`c ON c.`faculty_id` = a.`faculty_name`  "
					+ " JOIN `participants_registration_course_details`e ON e.`course_id`= a.`cs_id`"
					+ " JOIN `participants`f ON f.`participant_id`=e.`participant_id` WHERE  `cs_id` = "+scheduleid+" ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
				int count = 0;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("scheduleid", rs.getString("cs_id"));
					map.put("programname", rs.getString("program_name"));					
					map.put("startformat", rs.getString("startformatt"));
					map.put("endformat", rs.getString("endformatt"));					
					map.put("partname", rs.getString("partname"));
					map.put("coursedesc", rs.getString("course_name"));
					map.put("start_date", rs.getString("start_date"));
					map.put("date1", rs.getString("date1"));
					map.put("date2", rs.getString("date2"));
					map.put("end_date", rs.getString("end_date"));
					map.put("totalparticipants", rs.getString("total_participants_allowed"));
					map.put("appic", rs.getString("applicable_ic"));
					map.put("status", rs.getString("status"));
					map.put("facultyid", rs.getString("faculty_name"));
					map.put("facultyname", rs.getString("username"));
					map.put("username", rs.getString("username"));
					map.put("starthour", rs.getString("starthour"));
					map.put("endhour", rs.getString("endhour"));
					map.put("startminute", rs.getString("startminute"));
					map.put("endminute", rs.getString("endminute"));
					map.put("availableseats", rs.getString("seatavailable"));
					map.put("datestart", rs.getString("date_start"));
					map.put("dateend", rs.getString("date_end"));
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
		public oep_ResponseInfo getTestscheduledetails(String id) {
			
				
			/*String query = "  SELECT a.`ques_master_id`,`is_test_started`,a.`id`,a.`batch`,DATE_FORMAT(`testdate`,'%e/%c/%Y')testdate,a.`start_time`,"
					+ " a.`end_time`,a.`status`,b.`test_name`,`test_id`,GROUP_CONCAT(`username`)username,`is_participant_start`  FROM `test_schedule` a "
					+ " JOIN `question_master` b ON a.`ques_master_id` = b.`id` JOIN `course_scheduling`c ON c.`cs_id`= a.`batch`"
					+ " JOIN `participants_registration_course_details`e ON e.`course_id`= c.`cs_id`"
					+ " JOIN `participants`f ON f.`participant_id`=e.`participant_id`"
					+ " LEFT JOIN `test_participants`g ON g.`part_id`= f.`participant_id` AND g.`ts_id`=a.`id`  WHERE a.`id`= "+id+" ";*/
			
			
			String query = " SELECT a.`ques_master_id`,`is_test_started`,a.`id`,`sub_id`,a.`batch`,DATE_FORMAT(`testdate`,'%m/%d/%Y')newtestdate,a.`start_time`, a.`end_time`,a.`status`,b.`test_name`,`test_id`,"
					+ "HOUR(a.`start_time`)starthour,MINUTE(a.`start_time`)startminute, HOUR(a.`end_time`)endhour,MINUTE(a.`end_time`)endminute,"
					+ " DATE_FORMAT(`testdate`,'%d/%m/%Y')testdate, SUBSTRING_INDEX(a.`start_time`,' ',-1)startformatt,SUBSTRING_INDEX(a.`end_time`,' ',-1)endformatt,"
					+ "GROUP_CONCAT(f.`username`,' ',CASE WHEN `is_participant_start` = 2 THEN ' completed \n' WHEN `is_participant_start` = 1 THEN 'Started \n' ELSE 'Not started \n'END)username,"
					+ "`is_participant_start`,h.`username`facname   FROM `test_schedule` a    JOIN `question_master` b ON a.`ques_master_id` = b.`id` "
					+ "JOIN `course_scheduling`c ON c.`cs_id`= a.`batch` JOIN `participants_registration_course_details`e ON e.`course_id`= c.`cs_id` "
					+ " JOIN `participants`f ON f.`participant_id`=e.`participant_id` "
					+ "LEFT JOIN `test_participants`g ON g.`part_id`= f.`participant_id` AND g.`ts_id`=a.`id`"
					+ " JOIN `faculty_master`h ON h.`faculty_id` = c.`faculty_name` WHERE a.`id`= "+id+" ";
			
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> List = jdbcTemplate.query(query, new RowMapper() {
				int count = 0;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("testid", rs.getString("test_id"));
					map.put("startformat", rs.getString("startformatt"));
					map.put("endformat", rs.getString("endformatt"));	
					map.put("facname", rs.getString("facname"));
					map.put("quesid", rs.getString("ques_master_id"));
					map.put("id", rs.getString("id"));
					map.put("tsid", rs.getString("id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("newtestdate", rs.getString("newtestdate"));
					map.put("subid", rs.getString("sub_id"));
					map.put("starthour", rs.getString("starthour"));
					map.put("endhour", rs.getString("endhour"));
					map.put("startminute", rs.getString("startminute"));
					map.put("endminute", rs.getString("endminute"));
					map.put("starttime", rs.getString("start_time"));
					map.put("testname", rs.getString("test_name"));
					map.put("username", rs.getString("username"));
					map.put("starttest", rs.getString("is_test_started"));
					map.put("participantstart", rs.getString("is_participant_start"));
					map.put("endtime", rs.getString("end_time"));
/*					
					String submenuQuery = "SELECT `part_id`,`imh_staus`,`username`,CONCAT(`username`,' ',CASE WHEN `imh_staus` = 0 "
							+ " THEN 'Certificate Not sent  ' WHEN `imh_staus` = 1 THEN 'Certificate sent' "
							+ "  ELSE 'Invalid  'END)status FROM `email_history`a "
							+ " JOIN `participants`b ON a.`part_id`=b.`participant_id`  WHERE `ts_id` = "+ rs.getString("id");	*/
					
					String submenuQuery = " SELECT a.`ques_master_id`,`username`,`imh_staus`,`is_test_started`,a.`id`,a.`batch`,DATE_FORMAT(`testdate`,'%e/%c/%Y')testdate,a.`start_time`, a.`end_time`,a.`status`,b.`test_name`,`test_id`,"
							+ "CONCAT(`username`,' ',CASE WHEN `is_participant_start` = 2 THEN ' completed \n' WHEN `is_participant_start` = 1 THEN 'Started \n' ELSE 'Not started \n'END)teststatus,"
							+ "`is_participant_start`  FROM `test_schedule` a    JOIN `question_master` b ON a.`ques_master_id` = b.`id` "
							+ "JOIN `course_scheduling`c ON c.`cs_id`= a.`batch` JOIN `participants_registration_course_details`e ON e.`course_id`= c.`cs_id` "
							+ " JOIN `participants`f ON f.`participant_id`=e.`participant_id` "
							+ "LEFT JOIN `test_participants`g ON g.`part_id`= f.`participant_id` AND g.`ts_id`=a.`id`"
							+ "JOIN  `email_history`h ON h.`ts_id`= a.`id` WHERE a.`id`= "+ rs.getString("id");
					
					  List<Object> submenuList = new ArrayList<Object>();
					  submenuList = getPartStatusList(submenuQuery);
					  map.put("submenuList", submenuList);
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					if(rs.getString("is_test_started")!= null && rs.getString("is_test_started").equals("1")){
						map.put("teststarted", "Test Started");
					}else{
						map.put("teststarted", "Not started");
					}
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
		
		
		
		public List<Object> getPartStatusList(String submenuQuery) {	
			
			@SuppressWarnings("unchecked")
			List<Object> submenuList = jdbcTemplate.query(submenuQuery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int index) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					//map.put("partid",rs.getString("part_id"));
					//map.put("mailstatus", rs.getString("imh_staus"));
					//map.put("teststatus", rs.getString("teststatus"));
					map.put("username",rs.getString("username"));
					map.put("index", index);
					
					if(rs.getString("imh_staus")!= null && rs.getString("imh_staus").equals("1")){
						map.put("mailstatus", "Mail sent");
					}else if(rs.getString("imh_staus")!= null && rs.getString("imh_staus").equals("0")){
						map.put("mailstatus", "Mail not  sent");
					}else{
						map.put("mailstatus", "Invalid ");
					}
				
					if(rs.getString("is_participant_start")!= null && rs.getString("is_participant_start").equals("1")){
						map.put("teststatus", " Started");
					}else if(rs.getString("is_participant_start")!= null && rs.getString("is_participant_start").equals("0")){
						map.put("teststatus", "Not Started   ");
					}else{
						map.put("teststatus", "Completed ");
					}
					
					index++;
					return map;
				}
			});
					
			return submenuList;
		}

		
		
		@Override
		public oep_ResponseInfo getTestadministratordetails(String id) {
			
			String query = "SELECT a.`test_id`,a.`id`,a.`batch`,`part_id`,DATE_FORMAT(`testdate`, '%a %b %e %Y')testdate,`starttime`,`endtime`,a.`status`,b.`test_name`,`username`"
					+ " FROM `test_administrator` a JOIN `question_master` b ON a.`test_id` = b.`test_id` "
					+ " JOIN `users`c ON c.`userid` = `part_id` WHERE a.`id`= "+id+" ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> List = jdbcTemplate.query(query, new RowMapper() {
				int count = 0;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("testid", rs.getString("test_id"));
					map.put("id", rs.getString("id"));
					map.put("partid", rs.getString("part_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("starttime", rs.getString("starttime"));
					map.put("endtime", rs.getString("endtime"));
					map.put("testname", rs.getString("test_name"));
					map.put("participantname", rs.getString("username"));
					
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
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
		public oep_ResponseInfo getScheduleList() {
			
			String query = "SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,"
					+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,`total_participants_allowed`, a.`applicable_ic`,"
					+ " COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'')course_name ,"
					+ "`faculty_name`,a.`status`,`username`FROM `course_scheduling` a JOIN `course_master` b ON b.`course_id` = a.`program_name`"
					+ " JOIN `faculty_master` c ON a.`faculty_name`= c.`faculty_id`    ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> scheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("scheduleid", rs.getString("cs_id"));
					map.put("programname", rs.getString("course_name"));
					map.put("Incharge", rs.getString("username"));
					map.put("start_date", rs.getString("startdate"));
					map.put("end_date", rs.getString("enddate"));
					map.put("totalparticipants", rs.getString("total_participants_allowed"));
					map.put("appic", rs.getString("applicable_ic"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(scheduleList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
	
		
		public oep_ResponseInfo getSchListforTestSchedule(String roleid, String userid, String qnid) {
			String query = "";
			
			if(Integer.parseInt(roleid) > 0){
				
			if(Integer.parseInt(roleid) == 1){
				/*query = "SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,"
						+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,`total_participants_allowed`, a.`applicable_ic`,"
						+ " COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'')course_name ,"
						+ "`faculty_name`,a.`status`,`username`FROM `course_scheduling` a JOIN `course_master` b ON b.`course_id` = a.`program_name`"
						+ " JOIN `faculty_master` c ON a.`faculty_name`= c.`faculty_id` "
						+ " JOIN `subject_master` d ON  d.`course_id` = b.`course_id` JOIN `question_master` "
						+ " e ON e.`sub_id` = d.`sub_id` WHERE e.`id` = '"+qnid+"'  ";*/
				
				query = "SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,"
						+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,`total_participants_allowed`, a.`applicable_ic`,"
						+ " COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'')course_name ,"
						+ "`faculty_name`,a.`status`,`username`FROM `course_scheduling` a JOIN `course_master` b ON b.`course_id` = a.`program_name`"
						+ " JOIN `faculty_master` c ON a.`faculty_name`= c.`faculty_id` "
						+ " JOIN `question_master`e ON e.`sub_id` = b.`course_id` WHERE e.`id` = '"+qnid+"'  ";
			}else{
			/*	query = "SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,"
						+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,`total_participants_allowed`, a.`applicable_ic`,"
						+ " COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'')course_name ,"
						+ "`faculty_name`,a.`status`,`username`FROM `course_scheduling` a JOIN `course_master` b ON b.`course_id` = a.`program_name`"
						+ " JOIN `faculty_master` c ON a.`faculty_name`= c.`faculty_id` "
						+ " JOIN `subject_master` d ON  d.`course_id` = b.`course_id` JOIN `question_master` "
						+ " e ON e.`sub_id` = d.`sub_id` WHERE e.`id` = '"+qnid+"' AND c.`userid` =  '"+userid+"'  ";*/
				
				query = "SELECT `cs_id`,`program_name`,`start_time`,`end_time`,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,"
						+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,`total_participants_allowed`, a.`applicable_ic`,"
						+ " COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'')course_name ,"
						+ "`faculty_name`,a.`status`,`username`FROM `course_scheduling` a JOIN `course_master` b ON b.`course_id` = a.`program_name`"
						+ " JOIN `faculty_master` c ON a.`faculty_name`= c.`faculty_id` "
						+ " JOIN `question_master`e ON e.`sub_id` = b.`course_id` WHERE e.`id` = '"+qnid+"' AND c.`userid` =  '"+userid+"'  ";
			}
			 
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> scheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("scheduleid", rs.getString("cs_id"));
					map.put("programname", rs.getString("course_name"));
					map.put("Incharge", rs.getString("username"));
					map.put("start_date", rs.getString("startdate"));
					map.put("end_date", rs.getString("enddate"));
					map.put("totalparticipants", rs.getString("total_participants_allowed"));
					map.put("appic", rs.getString("applicable_ic"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(scheduleList);
			responseInfo.setInventoryResponse(response);
			
			}
			else{
				response.setResponseType("F");
				responseInfo.setInventoryResponse(response);
			}
			return responseInfo;
		}
		
		@Override
		public oep_ResponseInfo getTestscheduleList() {
			
			String query = "SELECT a.`id`,a.`ques_master_id`,`is_test_started`,a.`batch`,DATE_FORMAT(testdate,'%e/%c/%Y')testdate,"
			+ " `start_time`,`end_time`,a.`status`,b.`test_name`,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm'"
			+ " THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am'"
			+ "  THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1),' ',1)END AS starthours,"
			+ "  CASE WHEN  SUBSTRING_INDEX(`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) + 12 "
			+ "  WHEN SUBSTRING_INDEX(`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) END AS endhours,"
			+ "  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
			+ "  SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', -1), ' ', 1)endminutetime,"
			+ " HOUR(a.`start_time`)starthour,MINUTE(a.`start_time`)startminute, HOUR(a.`end_time`)endhour,MINUTE(a.`end_time`)endminute"
			+ "  FROM `test_schedule` a"
			+ "  JOIN `question_master` b ON a.`ques_master_id` = b.`id`"
			+ "  WHERE `testdate` = CURRENT_DATE   ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> TestscheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", rs.getString("id"));
					map.put("testid", rs.getString("ques_master_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					map.put("testname", rs.getString("test_name"));
					map.put("starthours", rs.getString("starthours"));
					map.put("endhours", rs.getString("endhours"));
					map.put("startminutetime", rs.getString("startminutetime"));
					map.put("endminutetime", rs.getString("endminutetime"));
					map.put("starthours", rs.getString("starthours"));
					map.put("endhours", rs.getString("endhours"));
					map.put("startminute", rs.getString("startminute"));
					map.put("endminute", rs.getString("endminute"));
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					if(rs.getString("is_test_started")!= null && rs.getString("is_test_started").equals("1")){
						map.put("teststarted", "Test Started");
					}else{
						map.put("teststarted", "Not started");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(TestscheduleList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		@Override
		public oep_ResponseInfo getallTestscheduleList() {
			
			String query = "SELECT a.`id`,a.`ques_master_id`,`is_test_started`,a.`batch`,DATE_FORMAT(testdate,'%e/%c/%Y')testdate,"
			+ " `start_time`,`end_time`,a.`status`,b.`test_name`,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm'"
			+ " THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am'"
			+ "  THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1),' ',1)END AS starthours,"
			+ "  CASE WHEN  SUBSTRING_INDEX(`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) + 12 "
			+ "  WHEN SUBSTRING_INDEX(`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) END AS endhours,"
			+ "  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
			+ "  SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', -1), ' ', 1)endminutetime  FROM `test_schedule` a"
			+ "  JOIN `question_master` b ON a.`ques_master_id` = b.`id`  ORDER BY  a.`id` ASC";
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> TestscheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", rs.getString("id"));
					map.put("testid", rs.getString("ques_master_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					map.put("testname", rs.getString("test_name"));
					map.put("starthours", rs.getString("starthours"));
					map.put("endhours", rs.getString("endhours"));
					map.put("startminutetime", rs.getString("startminutetime"));
					map.put("endminutetime", rs.getString("endminutetime"));
					map.put("isstatus", rs.getString("status"));
					map.put("isteststarted", rs.getString("is_test_started"));
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					if(rs.getString("is_test_started")!= null && rs.getString("is_test_started").equals("1")){
						map.put("teststarted", "Test Started");
					}else{
						map.put("teststarted", "Not started");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(TestscheduleList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		
		
		
		@Override
		public oep_ResponseInfo getTestadministratorList() {
			
			String query = "SELECT a.`id`,a.`test_id`,a.`batch`,`part_id`,DATE_FORMAT(testdate,'%e/%c/%Y')testdate,`starttime`,`endtime`,"
					+ "a.`status`,b.`test_name` FROM `test_administrator` a JOIN `question_master` b ON a.`test_id` = b.`test_id`  ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> TestadminList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", rs.getString("id"));
					map.put("partid", rs.getString("part_id"));
					map.put("testid", rs.getString("test_id"));
					map.put("batch", rs.getString("batch"));
					map.put("testdate", rs.getString("testdate"));
					map.put("starttime", rs.getString("starttime"));
					map.put("endtime", rs.getString("endtime"));
					map.put("testname", rs.getString("test_name"));
					
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(TestadminList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		
		@Override
		public oep_ResponseInfo getQuestionBankList() {
			
		/*	String query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`sub_name` FROM `question_master` a "
					+ " JOIN `subject_master` b ON a.`sub_id` = b.`sub_id`";*/
			
			String query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`course_name` FROM `question_master` a "
					+ " JOIN `course_master` b ON a.`sub_id` = b.`course_id`";
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> scheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("quesid", rs.getString("id"));
					map.put("id", rs.getString("id"));
					map.put("batch", rs.getString("batch"));
					map.put("testid", rs.getString("test_id"));
					map.put("testname", rs.getString("test_name"));
					/*	map.put("subjectid", rs.getString("sub_id"));
					map.put("subjectname", rs.getString("sub_name"));*/
					map.put("courseid", rs.getString("sub_id"));
					map.put("coursename", rs.getString("course_name"));

										
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(scheduleList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		public oep_ResponseInfo getQBMListforSchedule(String userid, String roleid) {
			
			String query = "";
			
			if(Integer.parseInt(roleid) > 0){
				
			if(Integer.parseInt(roleid) == 1){
		/*	query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`sub_name` FROM `question_master` a "
					+ " JOIN `subject_master` b ON a.`sub_id` = b.`sub_id`";*/
			
			query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`course_name` FROM `question_master` a "
					+ " JOIN `course_master` b ON a.`sub_id` = b.`course_id`";
			}
			else{
			query = "SELECT `id`,`test_id`,`batch`,`test_name`,a.`sub_id`,a.`status`,`course_name` FROM `question_master` a "
						+ " JOIN `course_master` b ON a.`sub_id` = b.`course_id` JOIN `course_scheduling` c ON "
						+ " c.`program_name` = a.`sub_id` JOIN `faculty_master` d ON d.`faculty_id` = c.`faculty_name`"
						+ " WHERE d.`userid` = '"+userid+"'";
			}
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> scheduleList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("quesid", rs.getString("id"));
					map.put("id", rs.getString("id"));
					map.put("batch", rs.getString("batch"));
					map.put("testid", rs.getString("test_id"));
					map.put("testname", rs.getString("test_name"));
				/*	map.put("subjectid", rs.getString("sub_id"));
					map.put("subjectname", rs.getString("sub_name"));
*/
					map.put("courseid", rs.getString("sub_id"));
					map.put("coursename", rs.getString("course_name"));

										
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(scheduleList);
			responseInfo.setInventoryResponse(response);
			}
			else{
				response.setResponseType("F");
				responseInfo.setInventoryResponse(response);
			}
			return responseInfo;
		}
		
		@Override
		public oep_ResponseInfo getIdformat(String id, String type) {
			log.info(id);
			
			if(type != null && type.length() > 0){
			
			String query = "";
			
			if(type.equals("qbm")){
				query = " SELECT CONCAT(`FN_OEP_ID_FORMAT`("+id+",(SELECT COALESCE(MAX(`id`),1)FROM `question_master`)),"
						+ "(SELECT COALESCE(MAX(`id`)+1 ,1) FROM `question_master`) )refno ";
			}else if(type.equals("tsh")){
				query = " SELECT CONCAT(`FN_OEP_ID_FORMAT`("+id+",(SELECT COALESCE(MAX(`id`),1)FROM `test_schedule`)),"
						+ "(SELECT COALESCE(MAX(`id`)+1 ,1) FROM `test_schedule`) )refno ";
			}
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
				int count = 0;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("refno", rs.getString("refno"));
					
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(questiondetailsList);
			responseInfo.setInventoryResponse(response);
			
			}else{
				response.setResponseType("S");
				responseInfo.setInventoryResponse(response);
			}
			return responseInfo;
		}
		
		@Override
		public oep_ResponseInfo saveparticpantsimport(String registerData,
				HttpServletRequest httpRequest) {
			
			try {
				oep_CourseRequest request = (oep_CourseRequest) oep_UTIL.convertJSONtooOBJECT(registerData, oep_CourseRequest.class);
				HttpSession session = httpRequest.getSession();
				String imagePath="";
				String filepath = request.getFilename(); 
				int scheduleid = 0;
				Random r = new Random( System.currentTimeMillis() );
				scheduleid = 10000 + r.nextInt(20000);
				 String newpath = oep_UTIL.getserverfilepath(httpRequest);
				    String modulename = oep_UTIL.getmodulename("schedule", 1);
					newpath = newpath.concat(File.separator).concat(modulename);
					File uploadPath = new File(newpath);
					if(!uploadPath.exists())
					{
						uploadPath.mkdirs();
					}
					String scheduleidStr =  String.valueOf(scheduleid);
					newpath = newpath.concat(File.separator).concat(scheduleidStr+".xlsx"); 

					new File(filepath).renameTo(new File(newpath));
					imagePath = oep_UTIL.filepath(newpath);
					int digits = 0;
					Random s = new Random( System.currentTimeMillis() );
					  digits = 10000 + s.nextInt(20000);
					int fileSeq = digits;
					
					String filename = scheduleidStr+".xlsx";
					StringTokenizer token = new StringTokenizer(filename,".");
					session.setAttribute("scheduleimportid", fileSeq);
					

					int rowCount = 1;
					FileInputStream fileInputStream = new FileInputStream(newpath);
					
					XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);					
					XSSFSheet worksheet = workbook.getSheet("sample");					
					
					/*XSSFRow row1 = worksheet.getRow(0);				
					XSSFCell cellA1 = row1.getCell((short) 0);
					String a1Val = cellA1.getStringCellValue();*/
					int totalCellCount = 0;
					List<Object> excelDatas = new ArrayList<Object>();
					Iterator rows = worksheet.rowIterator();
					while (rows.hasNext()) {
						
						XSSFRow row = (XSSFRow) rows.next();
						if(row.getRowNum() ==0)
						     continue;
						if(rowCount > 0)
						{
							Iterator cells = row.cellIterator();
							int cellCount = 0;
							Map<String, Object> map = new HashMap<String, Object>();
							
							int lastColumn = 6;
						    {
						    	for (int cn = 0; cn < 6; cn++) {
						          Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
						        
						          int myno = cn+1;
						          if (c == null) {
						        	  map.put("cell"+myno, "-");
						          } else {
						        	  switch(c.getCellType())
					                    {
					                    case 0:
					                    	
					                    	if (HSSFDateUtil.isCellDateFormatted(c)) {
								                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								                  try {
								                    String date = dateFormat.format(c.getDateCellValue());
								                    map.put("cell"+myno, date);
								                    break;
								                  } catch (Exception e) {
								                    e.printStackTrace();
								                    break;
								                  }
								                  
								                }else{
								                	int value = new Double(c.getNumericCellValue()).intValue();
								                	
							                    	map.put("cell"+myno,  String.valueOf(value));
							                    	break;
							                    	}
					                    case 1:
					                    
					                    	map.put("cell"+myno, c.getStringCellValue());
					                    }
						          }
						       }
						    	 
						       map.put("cell"+(lastColumn + 1), fileSeq);
				               map.put("cell"+(lastColumn + 2), request.getUserid());
				               map.put("cell"+(lastColumn + 3), request.getScheduleid());
				               excelDatas.add(map);  
				              
						}
							
		                    rowCount++;
					}
					}
					
					
					importscheduleitems(excelDatas);
					 log.info(excelDatas);
						
						String selectQuery = "SELECT CASE WHEN `status` = 'Success' THEN "
								+ "CONCAT('<label style=\"color:green;font-size:19px !important;\"><i class=\"mdi-action-check-circle\"></i> ',"
								+ "`status`,'</label>') ELSE CONCAT('<label style=\"color:red;font-size:19px !important;\"><i class=\"mdi-alert-error\"></i>  ',"
								+ "`status`,'</label>')END AS uploadstatus,"
								+ "`error_msg` errormsg,`seq_no`,`record_seq_no`,`status` uploadstatusdesc FROM `oep_question_import_status` WHERE `seq_no` = "+fileSeq;				
						
						        log.info("selectQuery  "+selectQuery);

								@SuppressWarnings("unchecked")
								List<Object> produtimportstatuslist = jdbcTemplate.query(selectQuery, new RowMapper() {
									
									public Object mapRow(ResultSet rs, int arg1) throws SQLException {
									
										HashMap<String, String> map = new HashMap<String, String>();
										map.put("uploadstatus", rs.getString("uploadstatus"));
										map.put("recindex", rs.getString("seq_no"));
										map.put("index", rs.getString("record_seq_no"));
										map.put("out_result_msg", rs.getString("errormsg"));
										map.put("uploadstatusdesc", rs.getString("uploadstatusdesc"));
										return map;
									}
								});
								Iterator produtimportstatuslistITR = produtimportstatuslist.iterator();
								while(produtimportstatuslistITR.hasNext())
								{
									HashMap<String, String> mainproductMap = (HashMap<String, String>) produtimportstatuslistITR.next();
									
									int indexId = Integer.parseInt(mainproductMap.get("index"));
									indexId = indexId-1;
									
									if(mainproductMap.get("uploadstatusdesc").equals("Success")){
										
										Map<String, Object> map = (Map<String, Object>) excelDatas.get(indexId);
										String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%*";
										StringBuilder builder = new StringBuilder();
										int count = 8;
										while (count-- != 0) {
										int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
										builder.append(ALPHA_NUMERIC_STRING.charAt(character));
										}
										
										String newPW = builder.toString();		
										 String  encryptnewPW = oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", newPW);
										LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
										inParamMap.put("P_ROLE_ID",request.getRoleid());
										inParamMap.put("P_USER_ID",request.getUserid());
										inParamMap.put("P_COURSE_ID",request.getCourseid());
										inParamMap.put("P_PASSWORD",encryptnewPW);
										inParamMap.put("P_PARTICIPANT_NAME",request.getParticipantname());
										inParamMap.put("P_PS_NUMBER",request.getTestdate());
										inParamMap.put("P_EMAIL", map.get("cell4").toString());			
										inParamMap.put("P_APPLICABLE_IC",request.getApplicableic());
										inParamMap.put("P_JOB_CODE",request.getJobcode());
										inParamMap.put("P_OPRN","UPD_PASSWORD");
										
										log.info(inParamMap);
										
										oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_COURSE_MANUAL_REGISTRATION", inParamMap);
										String mailid= map.get("cell4").toString();
										String msg="You have been sucessfully Registered  in L&T.Your Password is "+newPW;
										String subject = "Registartion";
										oep_UTIL.sendMail(jdbcTemplate, mailid, msg, subject,"0");
									}
									
								}
								response.setResponseType("S");
								response.setResponseObj(produtimportstatuslist);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
				
		@Transactional
		public void importscheduleitems( final List<Object> list) 
		{
			try
			{
				StringBuilder parametersSB = new StringBuilder();
				
				int totalCellCount = 9;
				
				for(int cell = 1;cell <= totalCellCount;cell++)
				{
					if(cell != totalCellCount+1)
					{
						parametersSB.append("?,");
					}
					else
					{
						parametersSB.append("?");
					}
				}
						parametersSB.append("?");
				
				
				
					jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_IMPORT_PARTICIPANT_REGISTRATION( "+parametersSB+" )}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return list.size();
					}
		
				public void setValues(PreparedStatement ps, int index) throws SQLException 
				{
					
					
					Map<String, Object> map = (Map<String, Object>) list.get(index);
					          for(int cell = 1;cell <= 9;cell++)
					        {
						 if(map.get("cell"+cell) == null)
						 {
							ps.setString(cell, "string");
						}
						 else
						 {
							ps.setString(cell, map.get("cell"+cell).toString());
			             }
					        }
							 ps.setInt(10, index);
							
				 }
					
					
				});
			}
			
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}


		@Override
		public oep_ResponseInfo getcourseparticipantdetails(String userid) {
			
			String query = "SELECT * FROM `participants`  a JOIN `users` b ON a.`user_id`=b.`userid` WHERE `user_id`= "+userid+" ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(query, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("partcipantname", rs.getString("username"));
					map.put("psnumber", rs.getString("ps_number"));
					map.put("email", rs.getString("email"));
					map.put("applicableic", rs.getString("app_ic"));
					map.put("jobcode", rs.getString("jobcode"));
					return map;
					
				}
			});
		
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		@Override
		public oep_ResponseInfo getfacultylistforreport() {
			// TODO Auto-generated method stub
			String facultyquery="SELECT a.faculty_id, CONCAT(a.`username`,' - ',a.`email`) faculty_firstname FROM `faculty_master` a "
					+ " JOIN `course_master` c ON a.`main_subject`=c.`course_id` JOIN `course_scheduling` d ON d.`program_name`=c.`course_id`  "
					+ " JOIN `test_schedule` e ON e.`batch`=d.`cs_id` GROUP BY a.`faculty_id`";
			@SuppressWarnings("unchecked")
			List<Object> facultyList = jdbcTemplate.query(facultyquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("facultyid", rs.getString("faculty_id"));
					map.put("facultyname", rs.getString("faculty_firstname"));
				 
					return map;
					
				}
			});
			
			response.setResponseType("S");
			response.setResponseObj(facultyList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
		@Override
		public oep_ResponseInfo getfacultyidforreport(String userid,String roleid) {
			// TODO Auto-generated method stub
			if(roleid.equals("2")){
			String facultyquery="SELECT * FROM `faculty_master` WHERE `userid`="+userid;
			@SuppressWarnings("unchecked")
			List<Object> facultyList = jdbcTemplate.query(facultyquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("facultyid", rs.getString("faculty_id"));
					map.put("facultyname", rs.getString("faculty_firstname"));
				
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(facultyList);
			}
			else if(roleid.equals("4")){
				String participantquery="SELECT * FROM `participants` WHERE `user_id`="+userid;
				@SuppressWarnings("unchecked")
				List<Object> facultyList = jdbcTemplate.query(participantquery, new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("facultyid", rs.getString("participant_id"));
						map.put("facultyname", rs.getString("username"));
					
						return map;
						
					}
				});
				response.setResponseType("S");
				response.setResponseObj(facultyList);
			}
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}


		@Override
		public oep_ResponseInfo getcoursedetailsforreport(String facultyid,String roleid) {
			// TODO Auto-generated method stub
			
			if(!facultyid.equals("0")){
			if(roleid.equals("2")){
			/*String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a JOIN `subject_master` b ON a.`course_id`= b.`course_id`"
                                       + " JOIN `faculty_master` c ON c.`main_subject`= b.`sub_id` WHERE c.`faculty_id`="+facultyid;*/
				
				String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a "
                        + " JOIN `faculty_master` c ON c.`main_subject`=  a.`course_id` WHERE c.`faculty_id`="+facultyid;
				
			log.info(coursedetailsquery);
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
				
					return map;
					
				}
			});
			
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			}else if(roleid.equals("4")){
				String coursedetailsquery="SELECT c.`course_id`,c.`course_name` FROM `course_scheduling` a   "
                                       + " JOIN `participants_registration_course_details` b ON "
                                       + " b.`course_id`= a.`cs_id` JOIN `course_master` c ON c.`course_id` = a.`program_name`"
                                       + " WHERE b.`participant_id`="+facultyid;
			log.info(coursedetailsquery);
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
				
					return map;
					
				}
			});
			
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			}else{
	/*String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a JOIN `subject_master` b ON a.`course_id`= b.`course_id`"
            + " JOIN `faculty_master` c ON c.`main_subject`= b.`sub_id` WHERE c.`faculty_id`="+facultyid;*/
	
	String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a "
            + " JOIN `faculty_master` c ON c.`main_subject`= a.`course_id` WHERE c.`faculty_id`="+facultyid;
	
		log.info(coursedetailsquery);
		@SuppressWarnings("unchecked")
		List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseid", rs.getString("course_id"));
		map.put("coursename", rs.getString("course_name"));
		
		return map;
		
		}
		});
		
		response.setResponseType("S");
		response.setResponseObj(coursedetailsList);
		}
			
		}else{
			String coursedetailsquery="SELECT `course_id`,`course_name` FROM `course_master` ";
			
		log.info(coursedetailsquery);
		@SuppressWarnings("unchecked")
		List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("courseid", rs.getString("course_id"));
				map.put("coursename", rs.getString("course_name"));
			
				return map;
				
			}
		});
		
		response.setResponseType("S");
		response.setResponseObj(coursedetailsList);
		}
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}

		
		@Override
		public oep_ResponseInfo gettestdetails(String courseid,String roleid) {
			// TODO Auto-generated method stub
			if(roleid.equals("2")){
			/*String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a JOIN `subject_master` b ON a.`course_id`= b.`course_id`"
                                       + " JOIN `faculty_master` c ON c.`main_subject`= b.`sub_id` WHERE c.`faculty_id`="+facultyid;*/
				
				String coursedetailsquery="SELECT `id` test_id, `test_name` FROM `question_master` WHERE `sub_id` = "+courseid;
				
			log.info(coursedetailsquery);
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("testid", rs.getString("test_id"));
					map.put("testname", rs.getString("test_name"));
				
					return map;
					
				}
			});
			
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			}else if(roleid.equals("4")){
				String coursedetailsquery="SELECT `id` test_id, `test_name` FROM `question_master` WHERE `sub_id` = "+courseid;
			log.info(coursedetailsquery);
			@SuppressWarnings("unchecked")
			List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("testid", rs.getString("test_id"));
					map.put("testname", rs.getString("test_name"));
				
					return map;
					
				}
			});
			
			response.setResponseType("S");
			response.setResponseObj(coursedetailsList);
			}else{
	/*String coursedetailsquery="SELECT a.`course_id`,`course_name` FROM `course_master` a JOIN `subject_master` b ON a.`course_id`= b.`course_id`"
            + " JOIN `faculty_master` c ON c.`main_subject`= b.`sub_id` WHERE c.`faculty_id`="+facultyid;*/
	
	    String coursedetailsquery="SELECT `id` test_id, `test_name` FROM `question_master` WHERE `sub_id` = "+courseid;
	
		log.info(coursedetailsquery);
		@SuppressWarnings("unchecked")
		List<Object> coursedetailsList = jdbcTemplate.query(coursedetailsquery, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("testid", rs.getString("test_id"));
		map.put("testname", rs.getString("test_name"));
		
		return map;
		
		}
		});
		
		response.setResponseType("S");
		response.setResponseObj(coursedetailsList);
		}
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		

		@Override
		public oep_ResponseInfo generatebatchreport(String courseid,String roleid, String userid, String testid) {
			
			String batchcountquery = "";
			
			if(Integer.parseInt(roleid)!=4){
				batchcountquery="SELECT * FROM `course_scheduling` a JOIN `course_master` b ON a.`program_name`=b.`course_id` "
						+ " JOIN `test_schedule` c ON c.`batch` = a.`cs_id` JOIN `question_master` d ON d.`id` = c.`ques_master_id`"
						+ " WHERE b.`course_id`='"+courseid+"' AND a.`faculty_name` = '"+userid+"' AND "
								+ "((SELECT COALESCE(MAX(`total_mark`),0) FROM `test_schedule` c  LEFT JOIN "
								+ "`test_participants` d ON d.`ts_id` = c.`id` WHERE c.`batch` = a.`cs_id`) != 0 "
								+ "OR (SELECT CEILING((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)"
								+ "FROM `test_schedule` c  LEFT JOIN `test_participants` d ON d.`ts_id` = c.`id` WHERE"
								+ " c.`batch` = a.`cs_id`) IS NOT NULL ) AND d.`id` = '"+testid+"'"
								+ "ORDER BY  b.`course_id`";
			}else{
				batchcountquery="SELECT * FROM `course_scheduling` a JOIN `course_master` b ON a.`program_name`=b.`course_id`"
						+ " JOIN `participants_registration_course_details` c ON c.`course_id` = a.`cs_id`"
						+ " JOIN `participants` d ON d.`participant_id`= c.`participant_id` JOIN `users` e ON e.`userid`= d.`user_id` "
						+ " JOIN `test_schedule` g ON g.`batch` = a.`cs_id` JOIN `question_master` f ON f.`id` = g.`ques_master_id` "
						+ " WHERE b.`course_id`='"+courseid+"' AND d.`user_id` = '"+userid+"' AND f.`id` = '"+testid+"'   ORDER BY  b.`course_id`";
			}
			
			log.info(batchcountquery);
			@SuppressWarnings("unchecked")
			List<Object> batchList = jdbcTemplate.query(batchcountquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("coursescheduleid", rs.getString("cs_id"));
					map.put("courseschedule", rs.getString("schedule_name"));
				
					return map;
					
				}
			
			});
			
			
			List<Object> BatchDetails = new ArrayList<Object>();
			List<Object> list = new ArrayList<Object>();
			Iterator batchlistITR = batchList.iterator();
			while(batchlistITR.hasNext())
			{
				HashMap<String, String> mainMap = (HashMap<String, String>) batchlistITR.next();
				log.info("scheduleid "+ mainMap.get("coursescheduleid"));

				String reportsquery = "";
				
				if(Integer.parseInt(roleid)!=4){
				reportsquery="SELECT COALESCE(MAX(`total_mark`),0)highmark,CEILING((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)total_percentage, "
						+ "CASE WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 0 AND 25 THEN '#31dae1'  WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 25 AND 50  THEN '#339999' "
						
						+" WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201' END AS markcolor,"
                        + "CASE WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 0 AND 25 THEN '#31dae1'  WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 25 AND 50  THEN '#339999' "
						
						+" WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201' END AS percentagecolor "
						+ "FROM `test_participants` a JOIN `test_schedule` b "
                                     + " ON a.`ts_id` = b.`id` JOIN `course_scheduling` c ON c.`cs_id`= b.`batch` "
                                     + " JOIN `question_master` d ON d.`id` = b.`ques_master_id`"
                                     + " WHERE  c.`cs_id` ='"+mainMap.get("coursescheduleid")+" ' "
                                     		+ "AND `is_participant_start` = 2 AND c.`faculty_name` = '"+userid+"' AND d.`id` = '"+testid+"' ";
				}else{
					reportsquery="SELECT COALESCE(MAX(`total_mark`),0)highmark,CEILING((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)total_percentage, "
							+ "CASE WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 0 AND 25 THEN '#31dae1'  WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 25 AND 50  THEN '#339999' "
							
							+" WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201' END AS markcolor,"
	                        + "CASE WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 0 AND 25 THEN '#31dae1'  WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 25 AND 50  THEN '#339999' "
							
							+" WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100)   BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201' END AS percentagecolor "
							+ "FROM `test_participants` a JOIN `test_schedule` b "
	                                     + " ON a.`ts_id` = b.`id` JOIN `course_scheduling` c ON c.`cs_id`= b.`batch` "
	                                     + " JOIN `question_master` d ON d.`id` = b.`ques_master_id`  "
	                                     + " WHERE  c.`cs_id` ='"+mainMap.get("coursescheduleid")+" ' "
	                                     		+ "AND `is_participant_start` = 2 AND a.`userid` = '"+userid+"' AND d.`id` = '"+testid+"' ";	
				}
				
				log.info(reportsquery);
            	
              
				@SuppressWarnings("unchecked")
		          List<Object>reportList = jdbcTemplate.query(reportsquery, new RowMapper() {
		        	 
						@Override
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						
							Map<String, Object> map = new HashMap<String, Object>();
							
							map.put("highmark", rs.getString("highmark"));
							map.put("cumulative",rs.getString("total_percentage"));
							map.put("score", rs.getString("total_percentage"));
							map.put("highmarkcolor", rs.getString("markcolor"));
							map.put("cumulativecolor",rs.getString("percentagecolor"));
							map.put("scorecolor", rs.getString("percentagecolor"));
							
							return map;
							
						}
					
					});
				list.add(reportList.get(0));
				
				String query="SELECT CONCAT(COALESCE(`faculty_firstname`,' '),' ',"
						+ "COALESCE(`faculty_lastname`,''))faculty_name, `test_schedule_id`,`schedule_name`,"
						+ "COALESCE(`total_participant_attempted`,0)total_participant_attempted,DATE_FORMAT(`testdate`, '%d/%m/%Y') test_date, "
						+ "FN_OEP_TIME(c.`start_time`,c.`end_time`) duration FROM `course_scheduling` a "
						+ " JOIN `course_master` b ON a.`program_name`=b.`course_id` "
						+ "   JOIN `test_schedule` c ON c.`batch` = a.`cs_id` "
						+ "  JOIN  `faculty_master` e ON  e.`faculty_id` = a.`faculty_name` "
						+ " JOIN `question_master` f ON f.`id` = c.`ques_master_id` "
						+ " WHERE a.`cs_id`='"+mainMap.get("coursescheduleid")+"' AND f.`id` = '"+testid+"' ORDER BY  b.`course_id` LIMIT 1";
				log.info(query);
				@SuppressWarnings("unchecked")
				 List<Object> firstbatchDetail= jdbcTemplate.query(query, new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("facultyname", rs.getString("faculty_name"));
						map.put("testid", rs.getString("test_schedule_id"));
						map.put("testdate", rs.getString("test_date"));
						map.put("duration", rs.getString("duration"));
						map.put("schedulename", rs.getString("schedule_name"));
						map.put("partcount", rs.getString("total_participant_attempted"));
						return map;
						
					}
				
				});
				
				BatchDetails.add(firstbatchDetail);
			}
			
			
			Map<String, Object> detailsmap = new HashMap<String, Object>();
			detailsmap.put("batchList", batchList);
			detailsmap.put("reportslist", list);
			detailsmap.put("batchDetails", BatchDetails);
			
			response.setResponseType("S");
			response.setResponseObj(detailsmap);
			responseInfo.setInventoryResponse(response);
			return responseInfo; 
		}
			
		
		@Override
		public oep_ResponseInfo getTrainingcourseList() {
			
			String query = "SELECT cs_id,`course_id`,`course_name`,`course_desc`,a.`status`,`duration`,`schedule_name`,"
					+ " DATE_FORMAT(`start_date`,'%e/%c/%Y')start_date, DATE_FORMAT(`end_date`,'%e/%c/%Y')end_date,"
					+ "`start_time`,`end_time`,`total_participants_allowed`,`username` FROM `course_master`a "
					+ " JOIN `course_scheduling`b ON a.`course_id`=b.`program_name` JOIN `faculty_master`c ON c.`faculty_id`=b.`faculty_name`"
					+ " WHERE a.`status`= 1 GROUP BY a.`course_id` ";
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> courseList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("scheduleid", rs.getString("cs_id"));
					map.put("courseid", rs.getString("course_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("coursedesc", rs.getString("course_desc"));
					map.put("courseduration", rs.getString("duration"));					
					map.put("startdate", rs.getString("start_date"));
					map.put("schedulename", rs.getString("schedule_name"));
					map.put("enddate", rs.getString("end_date"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					map.put("totalparticipants", rs.getString("total_participants_allowed"));
					map.put("username", rs.getString("username"));
					
					if(rs.getString("status")!= null && rs.getString("status").equals("1")){
						map.put("status", "Active");
					}else{
						map.put("status", "Inactive");
					}
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			
			
			String query1 = "SELECT b.`cs_id`,`schedule_name`,`course_name`,`program_name`,`start_time`,`end_time`,`total_participants_allowed`,"
					+ "  DATE_FORMAT(`start_date`,'%e/%c/%Y')start_date, DATE_FORMAT(`end_date`,'%e/%c/%Y')end_date, `username`,"
					+ " (`total_participants_allowed`- (SELECT COUNT(*) FROM `participants_registration_course_details` c  WHERE c.`course_id`=b.`cs_id` ))"
					+ " seatsavailable FROM  `course_scheduling` b  JOIN `course_master` a ON b.`program_name`=a.`course_id`"
					+ " JOIN `faculty_master`c ON c.`faculty_id`=b.`faculty_name`";
			log.info(query1);
			
			@SuppressWarnings("unchecked")
			List<Object> scheduleList = jdbcTemplate.query(query1, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("scheduleid", rs.getString("cs_id"));		
					map.put("startdate", rs.getString("start_date"));
					map.put("coursename", rs.getString("course_name"));
					map.put("schedulename", rs.getString("schedule_name"));
					map.put("programname", rs.getString("program_name"));
					map.put("enddate", rs.getString("end_date"));
					map.put("starttime", rs.getString("start_time"));
					map.put("endtime", rs.getString("end_time"));
					map.put("totalparticipants", rs.getString("seatsavailable"));
					map.put("username", rs.getString("username"));				
					
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			
			
			Map<String, Object> detailsmap = new HashMap<String, Object>();
			detailsmap.put("courseList", courseList);
			detailsmap.put("scheduleList", scheduleList);
			/*detailsmap.put("batchDetails", BatchDetails);*/
			
			response.setResponseType("S");
			response.setResponseObj(detailsmap);
			responseInfo.setInventoryResponse(response);
			return responseInfo; 
		}





		@Override
		public oep_ResponseInfo generatefacultyreport(String courseid,
				String roleid, String userid, String testid) {
			
			String facultycountquery = "";
			
			if(Integer.parseInt(roleid)!=4){
				facultycountquery="SELECT `faculty_id`,CONCAT(`faculty_firstname`,' - ',`email`)faculty_name "
						+ "FROM `test_schedule` a JOIN `question_master` b ON a.`ques_master_id` = b.`id` JOIN "
						+ "`course_scheduling` c ON a.`batch` = c.`cs_id`  JOIN `faculty_master` d ON d.`faculty_id` "
						+ "= c.`faculty_name`  WHERE c.`program_name` = '"+courseid+"' AND b.`id` = '"+testid+"' GROUP BY `faculty_id`";
			}
			
			log.info(facultycountquery);
			@SuppressWarnings("unchecked")
			List<Object> facultyList = jdbcTemplate.query(facultycountquery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("facultyid", rs.getString("faculty_id"));
					map.put("facultyname", rs.getString("faculty_name"));
				
					return map;
					
				}
			
			});
			
			
			List<Object> facultyDetails = new ArrayList<Object>();
			List<Object> list = new ArrayList<Object>();
			Iterator facultyListITR = facultyList.iterator();
			while(facultyListITR.hasNext())
			{
				HashMap<String, String> mainMap = (HashMap<String, String>) facultyListITR.next();
				log.info("facultyid "+ mainMap.get("facultyid"));

				String reportsquery = "";
				
				if(Integer.parseInt(roleid)!=4){
				reportsquery="SELECT COALESCE(MAX(`total_mark`),0)highmark, CEILING((COALESCE(SUM(`total_mark`),0)/"
						+ "COALESCE(SUM(`full_mark`),0) )*100)total_percentage, CASE WHEN COALESCE(MAX(`total_mark`),0) "
						+ " BETWEEN 0 AND 25 THEN '#31dae1'  WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 25 AND 50  THEN "
						+ "'#339999'  WHEN COALESCE(MAX(`total_mark`),0)  BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201'"
						+ " END AS markcolor, CASE WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100) "
						+ "  BETWEEN 0 AND 25 THEN '#31dae1'  WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0))*100)   "
						+ "  BETWEEN 25 AND 50  THEN '#339999'  WHEN ((COALESCE(SUM(`total_mark`),0)/COALESCE(SUM(`full_mark`),0) )*100) "
						+ "  BETWEEN 50 AND 75  THEN  '#A9A9A9' ELSE  '#E00201' END AS percentagecolor FROM `test_participants` "
						+ "a JOIN `test_schedule` b  ON a.`ts_id` = b.`id` JOIN `course_scheduling` c ON c.`cs_id`= b.`batch` "
						+ "JOIN `question_master` d ON d.`id` = b.`ques_master_id` WHERE  `is_participant_start` = 2 "
						+ "AND d.`id` = '"+testid+"' AND c.`program_name` = '"+courseid+"' AND c.`faculty_name` = '"+mainMap.get("facultyid")+"' ";
				}
				
				log.info(reportsquery);
            	
              
				@SuppressWarnings("unchecked")
		          List<Object>reportList = jdbcTemplate.query(reportsquery, new RowMapper() {
		        	 
						@Override
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						
							Map<String, Object> map = new HashMap<String, Object>();
							
							map.put("highmark", rs.getString("highmark"));
							map.put("cumulative",rs.getString("total_percentage"));
							map.put("score", rs.getString("total_percentage"));
							map.put("highmarkcolor", rs.getString("markcolor"));
							map.put("cumulativecolor",rs.getString("percentagecolor"));
							map.put("scorecolor", rs.getString("percentagecolor"));
							
							return map;
							
						}
					
					});
				list.add(reportList.get(0));
				
				String query=" SELECT `course_name`,`test_name` FROM `course_master` a JOIN `question_master` b "
						+ "WHERE b.`id` = '"+courseid+"' AND a.`course_id` = '"+testid+"'";
				log.info(query);
				@SuppressWarnings("unchecked")
				 List<Object> firstbatchDetail= jdbcTemplate.query(query, new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("coursename", rs.getString("course_name"));
						map.put("testname", rs.getString("test_name"));
						return map;
						
					}
				
				});
				
				facultyDetails.add(firstbatchDetail);
			}
			
			
			Map<String, Object> detailsmap = new HashMap<String, Object>();
			detailsmap.put("facultyList", facultyList);
			detailsmap.put("reportslist", list);
			detailsmap.put("facultyDetails", facultyDetails);
			
			response.setResponseType("S");
			response.setResponseObj(detailsmap);
			responseInfo.setInventoryResponse(response);
			return responseInfo; 
		}
		
		@Override
		public oep_ResponseInfo getDepartmentList() {
			
		
			
			String query = "SELECT `id`,`desc` FROM `department`";
			
			log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> departmentList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("deptid", rs.getString("id"));
					map.put("name", rs.getString("desc"));
					map.put("index",count);
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(departmentList);
			responseInfo.setInventoryResponse(response);
			return responseInfo;
		}
		
}

