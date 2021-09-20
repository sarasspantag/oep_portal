package com.oep.services.forms.login;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hero.heroutils.HERO_TOKEN_UTIL;
import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.admin.security.emailsender;
import com.oep.services.admin.util.OepUserDetail;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.course.oep_CourseRequest;
	
    public class oep_LoginDAOImpl implements oep_ILoginDAO{
	
	private static Logger log = Logger.getLogger(oep_LoginDAOImpl.class);
	private static Logger error_log = Logger.getLogger("requestAppender");
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private static emailsender mailMail;
	
	@Autowired
	private oep_Response response;
	
	@Autowired
	private oep_ResponseInfo responseInfo;
	
	HttpSession session = null; 

	
	@Override
	public oep_ResponseInfo validLogin(String formData,final HttpServletRequest httpRequest) throws ClassNotFoundException {
			
		String tokenkey = "";
		String moduleUrl = "";
		session = httpRequest.getSession();
		JSONObject responseJSON;
		try {
			
			//String applnurl=oep_UTIL.getApplicationSontext(httpRequest).concat("/tokencentre/generatetokenkey");	
			//URL url = new URL(applnurl);			
			//responseJSON = HERO_TOKEN_UTIL.HTTPpostMethod(formData,url);
			
			//log.info("output"+oep_UTIL.generatetokenkey(formData,jdbcTemplate));
			//String output = oep_UTIL.generatetokenkey(formData,jdbcTemplate);
			//responseJSON = (JSONObject) new JSONParser().parse(output);

			//log.info("responseJSON  "+responseJSON);
			String general_Aud ="" ;
			OepUserDetail userinfoJSON = oep_UTIL.generatetokenkey(formData,jdbcTemplate);
			log.info("userinfoJSON "+userinfoJSON);
			//JSONObject userinfoJSON = HERO_TOKEN_UTIL.converttoJSON(responseJSON.toString());
			tokenkey = userinfoJSON.getTokenkey();			
			log.info("tokenkey "+tokenkey);
			if(tokenkey != null){			
			List<Object> userDetailsArr =  userinfoJSON.getUserDetails();		
			log.info(userDetailsArr);
			
			if(userDetailsArr != null && userDetailsArr.size() > 0)
			{				
				Map<String, Object> responseObj =  (Map<String, Object>) userDetailsArr.get(0);
				
				moduleUrl = "../onlineexamine/OEP_ADM_SERVC_TOKEN_VALIDATOR?tokenkey="+tokenkey+" ";							
				
				String applntype = "1";
				String role = (String) responseObj.get("roleid");				
				String username = (String) responseObj.get("username");
				String userid = (String) responseObj.get("userid");	
				
				
				if(role.equals("5")){
					general_Aud = "1";
				}else{
					general_Aud = "0";
				}
				
				session.setAttribute("usertype",Integer.parseInt((String)responseObj.get("roleid")));
				session.setAttribute("uid",responseObj.get("userid"));
				session.setAttribute("tokenkey", tokenkey);
							
			}
					Map<String, Object> userDetailMap = new HashMap<String, Object>();
					userDetailMap.put("tokenkey", tokenkey);
					userDetailMap.put("general_Aud", general_Aud);
					userDetailMap.put("moduleUrl", moduleUrl);
					userDetailMap.put("userDetails", userDetailsArr);

					  String emailSettingsQuery = "SELECT * FROM `email_settings`";
						
						@SuppressWarnings("unchecked")
						List<Object> emailSettingsList = jdbcTemplate.query(emailSettingsQuery,
								new RowMapper() {

									@Override
									public Object mapRow(ResultSet rs, int arg1)
											throws SQLException {
									
										Map<String, Object> emailMap = new HashMap<String, Object>();
										
										emailMap.put("emailid", rs.getString("email_id"));
										emailMap.put("emailpassword", rs.getString("email_password"));
										
										return emailMap;
									}
								});
						oep_UTIL.emailSettingsList = emailSettingsList;
						
					response.setResponseObj(userDetailMap);
					response.setResponseType("S");
					responseInfo.setInventoryResponse(response);
			}
			else{
				response.setResponseObj(null);
					response.setResponseType("F");
					responseInfo.setInventoryResponse(response);
			}
		
//		 catch (ParseException e1) {
//			
//			error_log.info(e1);
//		} 
		}catch (Exception e) {	
			error_log.info(e);
		}		

		return responseInfo;
	}

	
	@Override
	public oep_ResponseInfo saveuser(String uploadData, HttpServletRequest httpRequest) throws ClassNotFoundException {
				
		try{
			
			oep_LoginRequest request = (oep_LoginRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_LoginRequest.class);
			HttpSession session = httpRequest.getSession();
	
			String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%*";
			StringBuilder builder = new StringBuilder();
			int count = 8;
			while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			
			String newPW = builder.toString();
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_PASSWORD", newPW);		
		    
		    newPW = oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", newPW);
		    
			SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_USER_MODULE");
			inParamMap.put("P_USERNAME",request.getUsername());
			inParamMap.put("P_USERID",request.getUserid());
			inParamMap.put("P_STATUS",request.getStatus());
			inParamMap.put("P_EMAIL",request.getEmail());
			inParamMap.put("P_APPLICABLE_IC",request.getAppic());
			inParamMap.put("P_ROLE",request.getRole());		
			inParamMap.put("P_PS_NUMBER",request.getTotalparticipants());
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_ENCRYPT_PASSWORD", newPW);
			
			log.info("inParamMap         "+inParamMap);			
			
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> resultMap = jdbcCALL.execute(in);
			log.info(resultMap);		
			
			response.setResponseObj(resultMap);
			String imh_id = (String) resultMap.get("out_genrate_mailid");
			log.info("imh_id value is "+  imh_id);	
			oep_UTIL.sendMailfromSql(imh_id);
			
		}catch(Exception e)
		{
			e.printStackTrace();			
		}

		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo savemailtemplate(String uploadData, HttpServletRequest httpRequest) throws ClassNotFoundException {
				
		try{
			
			oep_LoginRequest request = (oep_LoginRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_LoginRequest.class);
			HttpSession session = httpRequest.getSession();
	
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
		   
		    
			SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_SAVE_EMAILTEMPLATE");
			inParamMap.put("P_TEMPLATENAME",request.getName());
			inParamMap.put("P_TEMPLATE_ID",request.getTempid());
			inParamMap.put("P_SUBJECT",request.getSubject());
			inParamMap.put("P_CONTENT",request.getContent());			
			inParamMap.put("P_OPRN",request.getOprn());		
			
			log.info("inParamMap         "+inParamMap);			
			
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> resultMap = jdbcCALL.execute(in);
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
	public oep_ResponseInfo signout(String tokenkey)throws ClassNotFoundException {
		
		String username = "",usertypedesc="";		
		int tokenid=0,userid=0,role=0,currencyid=0,storeid=0;
		
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_OEP_TOKEN_MASTER");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_TOKEN_ID", tokenid);
		inParamMap.put("P_TOKEN_KEY", tokenkey);
		inParamMap.put("P_TOKEN_LOGINOUT_STATUS", "1");
		inParamMap.put("P_TOKEN_UD_USERID", userid);
		inParamMap.put("P_TOKEN_UD_USERNAME", username);
		inParamMap.put("P_TOKEN_UD_ROLE", role);
		inParamMap.put("P_OPRN", "UPD");		
		log.info("inParamMap         "+inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info(resultMap);		
		response = oep_UTIL.returnResponse(resultMap, response);		
		responseInfo.setInventoryResponse(response);
		
		return responseInfo;
	
	}	
	
	@Override
	public oep_ResponseInfo getMenuList(String roleid) {

		String query = "SELECT A.`moduleid`,`modulename`,`modulepath`,`is_submodule`,`parentid`,"
				+ "COALESCE(`user_menu_sno`,0)user_menu_sno,COALESCE(`userid`,0)userid,COALESCE(`roleid`,0)usertype,"
				+ "`menu_access`,`roleid`,fafafont FROM `module` A LEFT OUTER JOIN `user_menus` B ON A.`moduleid` = B.`moduleid`"
				+ "	WHERE `roleid` = "+roleid+" AND parentid = 0 AND menu_access = 1 AND `status` = '1'"
				+ " AND (app_type = 1 OR app_type = 2) AND `is_admin_menu` = '1'  ORDER BY `moduleid` ASC";
		
		        log.info(query);
		    	List<Object> usermenuList = jdbcTemplate.query(query, new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int index) throws SQLException {
						
						Map<String, Object> map = new HashMap<String, Object>();
				map.put("moduleid",rs.getString("moduleid"));
				map.put("modulename", rs.getString("modulename"));
				map.put("issubmodule",rs.getString("is_submodule"));
				map.put("modulepath",rs.getString("modulepath"));
				map.put("parentid",rs.getString("parentid"));
				map.put("usertype",rs.getString("usertype"));
				map.put("icon",rs.getString("fafafont"));
				if(rs.getInt("menu_access") == 0)
				{
					map.put("statusdisp", "InActive");
				}
				else 
				{
					map.put("statusdisp", "Active");
				}
				
				if(rs.getInt("is_submodule") == 0)
				{
					map.put("mainmenudisp", "mainmenudisp");
					map.put("submenudisp", "mainmenuhidedisp");
					map.put("space", "");
					map.put("style", "style='background-color: #319db5;font-weight: bolder;'");
				}
				else
				{
					map.put("mainmenudisp", "mainmenuhidedisp");
					map.put("submenudisp", "mainmenudisp");
					map.put("space", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					map.put("style", "");
				}
				
				map.put("menudetails",rs.getInt("menu_access")+"$$$"+rs.getInt("is_submodule")+"$$$"+rs.getInt("parentid")+"$$$"+rs.getInt("moduleid")+"$$$"
						+rs.getString("user_menu_sno"));
				map.put("menusno",rs.getString("user_menu_sno"));
				map.put("usertype",rs.getString("usertype"));
				
				String submenuQuery = "SELECT A.`moduleid`,`modulename`,`is_submodule`,`parentid`,COALESCE(`user_menu_sno`,0)user_menu_sno,COALESCE(`userid`,0)userid,"
				  		+ "COALESCE(`roleid`,0)usertype,`menu_access`,`modulepath`"
							+ " FROM `module` A LEFT OUTER JOIN `user_menus` B ON A.`moduleid` = B.`moduleid` "
							+ "WHERE `roleid` = "+rs.getString("usertype")+" AND parentid = "+rs.getString("moduleid")
							+" AND menu_access = 1 AND `status` = '1' AND `is_admin_menu` = 1 ORDER BY `userid`,`moduleid` ASC";			
				
				  List<Object> submenuList = new ArrayList<Object>();
				  submenuList = getSubMenuList(submenuQuery);
				  map.put("submenuList", submenuList);
				  //log.info(submenuQuery);
				index++;
				return map;
			}
		});	    	
		    	response.setResponseType("S");
				response.setResponseObj(usermenuList);
				responseInfo.setInventoryResponse(response);
				return responseInfo;		
	}

	
	
	public List<Object> getSubMenuList(String submenuQuery) {	
		
		@SuppressWarnings("unchecked")
		List<Object> submenuList = jdbcTemplate.query(submenuQuery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("moduleid",rs.getString("moduleid"));
				map.put("modulename", rs.getString("modulename"));
				map.put("issubmodule",rs.getString("is_submodule"));
				map.put("parentid",rs.getString("parentid"));
				map.put("index", index);
				
				if(rs.getInt("is_submodule") == 0)
				{
					map.put("mainmenudisp", "mainmenudisp");
					map.put("submenudisp", "mainmenuhidedisp");
					map.put("space", "");
					map.put("style", "style='background-color: #319db5;font-weight: bolder;'");
				}
				else
				{
					map.put("mainmenudisp", "mainmenuhidedisp");
					map.put("submenudisp", "mainmenudisp");
					map.put("space", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					map.put("style", "");
				}
				
				map.put("menudetails",rs.getInt("menu_access")+"$$$"+rs.getInt("is_submodule")+"$$$"+rs.getInt("parentid")+"$$$"+rs.getInt("moduleid")+"$$$"
						+rs.getString("user_menu_sno"));
				map.put("menusno",rs.getString("user_menu_sno"));
				map.put("usertype",rs.getString("usertype"));
				map.put("modulepath", rs.getString("modulepath"));
				
				index++;
				return map;
			}
		});
				
		return submenuList;
	}

	
	
	@Override
	public oep_ResponseInfo getuserdetails(String userid) {
		
		/*String query = "SELECT a.`username`,a.`userid`,`role`,a.`email`,e.`participant_id`,`app_ic`,`ps_number`,`jobcode`,`is_approved`,"
				+ " `phone_no`,a.`dob`,a.`status`,`role_name`,`sub_name`,`course_name` FROM `users`a "
				+ "  JOIN `roles` b ON a.`role` = b.`id`  LEFT JOIN `faculty_master`c ON c.`userid`= a.`userid`"
				+ "  LEFT JOIN `subject_master`d ON d.`sub_id`=c.`main_subject`  JOIN `participants`e ON a.`userid` = e.`user_id`"
				+ "  JOIN `participants_registration_course_details`f ON f.`participant_id`= e.`participant_id`"
				+ "   LEFT JOIN `course_master`g ON g.`course_id` = f.`course_id` WHERE a.`userid` = "+userid+"  ";*/
		
		String query = "SELECT a.`username`,a.`userid`,`role`,a.`email`,e.`participant_id`,`app_ic`,`ps_number`,`jobcode`,`is_approved`,"
				+ " `phone_no`,a.`dob`,a.`status`,`role_name`,`course_name`,f.`course_id` FROM `users`a "
				+ "  JOIN `roles` b ON a.`role` = b.`id`  LEFT JOIN `faculty_master`c ON c.`userid`= a.`userid`"
				+ "   LEFT JOIN `participants`e ON a.`userid` = e.`user_id`"
				+ "  LEFT JOIN `participants_registration_course_details`f ON f.`participant_id`= e.`participant_id`"
				+ "   LEFT JOIN `course_master`g ON g.`course_id` = c.`main_subject` WHERE a.`userid` = "+userid+"  ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> userdetailsList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleid", rs.getString("role"));
				map.put("coursename", rs.getString("course_name"));
				map.put("jobcode", rs.getString("jobcode"));
			//	map.put("subject", rs.getString("sub_name"));
				map.put("rolename", rs.getString("role_name"));
				map.put("status", rs.getString("status"));	
				map.put("phone_no", rs.getString("phone_no"));
				map.put("username", rs.getString("username"));
				map.put("participantname", rs.getString("username"));
				map.put("userid", rs.getString("userid"));
				map.put("email", rs.getString("email"));
				map.put("email_id", rs.getString("email"));
				map.put("participantid", rs.getString("participant_id"));
				map.put("app_ic", rs.getString("app_ic"));
				map.put("psnumber", rs.getString("ps_number"));
				map.put("isapproved", rs.getString("is_approved"));
				map.put("courseid", rs.getString("course_id"));
				map.put("index", count);
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(userdetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo registerforgotpassword(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException {
		
		oep_LoginRequest request = (oep_LoginRequest) oep_UTIL.convertJSONtooOBJECT(formData, oep_LoginRequest.class);
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%*";
		StringBuilder builder = new StringBuilder();
		int count = 8;
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		String newPW = builder.toString();
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_OLD_PASSWORD", newPW);
	    
	    newPW = oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", newPW);
	    
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_NEWLOGIN_MODULE");
		inParamMap.put("P_USERID", request.getUsername());
		inParamMap.put("P_NEW_PASSWORD", newPW);
		inParamMap.put("P_OPRN", "FORGOT_PW");
		
		log.info("inParamMap         "+inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info(resultMap);
		response.setResponseObj(resultMap);
		String imh_id = (String) resultMap.get("out_genrate_id");
		log.info("imh_id value is "+  imh_id);	
		oep_UTIL.sendMailfromSql(imh_id);
		
		response = oep_UTIL.returnResponse(resultMap, response);		
		responseInfo.setInventoryResponse(response);		
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo changepassword(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException {
		
		
		oep_LoginRequest request = (oep_LoginRequest) oep_UTIL.convertJSONtooOBJECT(formData, oep_LoginRequest.class);
		
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_NEWLOGIN_MODULE");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_USERID", request.getUserid());
		inParamMap.put("P_OLD_PASSWORD", oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", request.getPassword()));
		inParamMap.put("P_NEW_PASSWORD", oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", request.getConfirmpassword()));
		inParamMap.put("P_OPRN", "CHANGE_PW");
		
		log.info("inParamMap         "+inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info(resultMap);
		
		response = oep_UTIL.returnResponse(resultMap, response);		
		responseInfo.setInventoryResponse(response);		
		return responseInfo;
	}
	
	@Override
	public oep_ResponseInfo getroleList() {
		
		String query = "SELECT `id`,`role_name`,`description` FROM `roles` WHERE `id` = 2 OR `id` = 3 OR `id`=1";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> roleList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleid", rs.getString("id"));
				map.put("rolename", rs.getString("role_name"));
				map.put("description", rs.getString("description"));	
				
				return map;
				
			}
		});
		
		response.setResponseType("S");
		response.setResponseObj(roleList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo gettemplateList() {
		
		String query = "  SELECT `email_temp_name`,`email_temp_id`,`email_temp_subject`,`email_temp_content` FROM `email_template`";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> roleList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("email_temp_name"));
				map.put("id", rs.getString("email_temp_id"));
				map.put("subject", rs.getString("email_temp_subject"));
				map.put("content", rs.getString("email_temp_content"));
				map.put("index",count);
				count++;
				return map;
				
			}
		});
		
		response.setResponseType("S");
		response.setResponseObj(roleList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo gettemplatetails(String id) {
		
		String query = "  SELECT `email_temp_name`,`email_temp_id`,`email_temp_subject`,`email_temp_content`"
				+ " FROM `email_template` WHERE `email_temp_id` = "+id+" ";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> userdetailsList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", rs.getString("email_temp_name"));
				map.put("id", rs.getString("email_temp_id"));
				map.put("subject", rs.getString("email_temp_subject"));
				map.put("content", rs.getString("email_temp_content"));
				map.put("index",count);
				count++;
				return map;
			
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(userdetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	
	@Override
	public oep_ResponseInfo getuserList() {
		
		/*String query = "  SELECT `username`,`userid`,`role`,`email`,`phone_no`,`dob`,`status`,`role_name` FROM  `users`a "
				+ " JOIN `roles` b ON a.`role` = b.`id` WHERE `userid` != 1  AND a.`role` != 4 AND a.`role` != 6 ";*/
		
		String query = "  SELECT `username`,`userid`,`role`,`email`,`phone_no`,`dob`,`status`,`role_name` FROM  `users`a "
				+ " JOIN `roles` b ON a.`role` = b.`id` WHERE `userid` != 1  AND a.`role` != 6 ";
		
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> userList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleid", rs.getString("role"));
				map.put("rolename", rs.getString("role_name"));
				map.put("status", rs.getString("status"));	
				map.put("phone_no", rs.getString("phone_no"));
				map.put("username", rs.getString("username"));
				map.put("userid", rs.getString("userid"));
				map.put("email", rs.getString("email"));
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
		response.setResponseObj(userList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo savefile(String formData,String id,String form,String type,
			String sessionname,String foldername,CommonsMultipartFile file,HttpServletRequest httpRequest) throws ClassNotFoundException {
		
		
		String resultfilename = "";		
		List<Object> outputList = new ArrayList<Object>(); 
		int inttype = 0,intid = 0;
		
		String filename=file.getOriginalFilename();
		 
		if(type != null)
       {
     	  inttype = Integer.parseInt(type);		
       }
       if(id != null)
       {
       	intid = Integer.parseInt(id);
       }
       
       	int extensionIndex = filename.lastIndexOf(".");      	
       	if(extensionIndex == -1  ){ 
       		
     			return (oep_ResponseInfo) outputList;
       	}
       	else{
       		
       	String fileextension = filename.substring(extensionIndex,filename.length());
       	
			String path=oep_UTIL.getuploadfilepath(httpRequest, form, inttype,intid); 
			filename = oep_UTIL.getuploadfilename(filename, intid,fileextension);	
			/*resultfilename = filename;*/
	        
	        try{
	        	
	        byte barr[]=file.getBytes();  
	        File uploadPath = new File(path);
	      
	        if(!uploadPath.exists())
	        {
	        	uploadPath.mkdirs();
	        }
	       
	        filename = path+File.separator+filename;
	        resultfilename = filename;
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filename));  
	        
	        HttpSession session = httpRequest.getSession();
	        session.setAttribute(sessionname, filename);
	        
	        log.info("filename"+filename);
	        log.info("sessionname"+sessionname);
	        
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	          
	        }
	        catch(Exception e)
	        {
	        	
	        }  
	      if(fileextension.equals(".zip")){
	    	  unZipIt(filename,httpRequest);
	    	  
	      }else{
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("id", id);
	        map.put("type", type);
	        map.put("form", form);
	        map.put("filename", resultfilename);
	        map.put("foldername", foldername);
	        map.put("color","green");
	        map.put("out_result_msg","File Uploaded Successfully");
	        map.put("out_result_type","S");
	        map.put("update-count-1", inttype);
	       
	        outputList.add(map);
	      
       	 response.setResponseObj(map);
	     response.setResponseType("S");
		 responseInfo.setInventoryResponse(response);
	      }
	     return responseInfo;
		
	}
	}
  public void unZipIt(String zipFile,HttpServletRequest httpRequest){

      byte[] buffer = new byte[1024];
       	
        try{
        	String path=oep_UTIL.getuploadfilepath(httpRequest, "product", 1,1); 
       	//create output directory is not exists
       	File folder = new File(path);
       	if(!folder.exists()){
       		folder.mkdir();
       	}
       		
       	//get the zip file content
       	ZipInputStream zis = 
       		new ZipInputStream(new FileInputStream(zipFile));
       	//get the zipped file list entry
       	ZipEntry ze = zis.getNextEntry();
       		
       	while(ze!=null){
       			
       	   String fileName = ze.getName();
       	   log.info("file name zip"+fileName);
              File newFile = new File(path + File.separator + fileName);
            
                   
               //create all non exists folders
               //else you will hit FileNotFoundException for compressed folder
               new File(newFile.getParent()).mkdirs();
                 
               FileOutputStream fos = new FileOutputStream(newFile);             

               int len;
               while ((len = zis.read(buffer)) > 0) {
          		fos.write(buffer, 0, len);
               }
           		
               fos.close();   
               ze = zis.getNextEntry();
       	}
       	
           zis.closeEntry();
       	zis.close();
       		
     
       		
       }catch(IOException ex){
          ex.printStackTrace(); 
       }
	
      } 
  
	@Override
	public oep_ResponseInfo getloadscheduleevents(int roleid,int userid) {
		String query = "";
		if(roleid == 2){
			query = "SELECT `cs_id` cs_id,`program_name` program_name,CONCAT(`course_name`,'-',`schedule_name`)course_name,`course_desc` course_desc,`duration` duration,`total_participants_allowed` total_participants_allowed,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,CONCAT(a.`start_time`,'-',a.`end_time`)time,"
					+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(a.`start_time`,' ' , -1) = 'pm' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(a.`start_time`,' ' , -1) = 'am' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`start_time`, ':', 1), ' ', 1) END AS starthours,CASE WHEN  SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'pm'"
							+ " THEN SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'am' THEN"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), ' ', 1) END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(a.`start_time`, ':', -1), ' ', 1)startminutetime,"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', -1), ' ', 1)endminutetime,(`total_participants_allowed`- (SELECT COUNT(*) FROM `participants_registration_course_details` c WHERE c.`course_id`=a.`cs_id` ))seatsavailable,"
							+ " 'course' scheduletype,a.`start_time` starttime,a.`end_time` endtime,`start_date` testdate  "
							+ " FROM `course_scheduling` a JOIN `course_master` b ON a.`program_name`=b.`course_id`"
							+ "  JOIN `faculty_master` c ON c.`faculty_id`=a.`faculty_name` WHERE c.`userid`="+userid
							+ " UNION ALL "
							+ "  SELECT c.`id` cs_id,`ques_master_id` program_name,CONCAT(`test_name`,'-',`test_schedule_id`)course_name,`test_name` course_desc,"
							+ " c.`start_time` duration,`total_participant_attempted` total_participants_allowed,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')startdate,"
							+ " CONCAT(c.`start_time`,'-',c.`end_time`)TIME,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(c.`start_time`,' ' , -1) = 'pm' THEN  "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(c.`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(c.`start_time`,' ' , -1) = 'am' THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(c.`start_time`, ':', 1), ' ', 1) "
							+ " END AS starthours,CASE WHEN  SUBSTRING_INDEX(c.`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', 1), ' ', 1) + 12 "
							+ " WHEN SUBSTRING_INDEX(c.`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', 1), ' ', 1) "
							+ "  END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(c.`start_time`, ':', -1), ' ', 1)startminutetime, "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', -1), ' ', 1)endminutetime,'1' seatsavailable,'test' scheduletype,"
							+ " c.`start_time` starttime,c.`end_time` endtime,`testdate` `testdate`"
							+ " FROM `test_schedule` c JOIN `question_master` d ON c.`ques_master_id`=d.`id` "
							+ " JOIN `course_scheduling` e ON e.`cs_id`=c.`batch` JOIN `faculty_master` f ON f.`faculty_id`=e.`faculty_name`  WHERE f.`userid`="+userid;
			
		System.out.println("query"+query);
		}else if(roleid == 4){
			query = "SELECT `cs_id` cs_id,`program_name` program_name,CONCAT(`course_name`,'-',`schedule_name`)course_name,`course_desc` course_desc,`duration` duration,`total_participants_allowed` total_participants_allowed,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,CONCAT(`start_time`,'-',a.`end_time`)time,"
					+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) END AS starthours,CASE WHEN  SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'pm'"
							+ " THEN SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'am' THEN"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), ' ', 1) END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', -1), ' ', 1)endminutetime,(`total_participants_allowed`- (SELECT COUNT(*) FROM `participants_registration_course_details` c WHERE c.`course_id`=a.`cs_id` ))seatsavailable,"
							+ " 'course' scheduletype,`start_time` starttime,a.`end_time` endtime,`start_date` testdate  "
							+ " FROM `course_scheduling` a JOIN `course_master` b ON a.`program_name`=b.`course_id`"
							+ "  JOIN  `participants_registration_course_details` c ON c.`course_id`=a.`cs_id` JOIN `participants` d ON d.`participant_id`=c.`participant_id` WHERE `user_id`="+userid
							+ " UNION ALL "
							+ "  SELECT c.`id` cs_id,`ques_master_id` program_name,CONCAT(`test_name`,'-',`test_schedule_id`)course_name,`test_name` course_desc,"
							+ " `start_time` duration,`total_participant_attempted` total_participants_allowed,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')startdate,"
							+ " CONCAT(`start_time`,'-',c.`end_time`)TIME,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN  "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am' THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) "
							+ " END AS starthours,CASE WHEN  SUBSTRING_INDEX(c.`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', 1), ' ', 1) + 12 "
							+ " WHEN SUBSTRING_INDEX(c.`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', 1), ' ', 1) "
							+ "  END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime, "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(c.`end_time`, ':', -1), ' ', 1)endminutetime,'1' seatsavailable,'test' scheduletype,"
							+ " `start_time` starttime,c.`end_time` endtime,`testdate` `testdate`"
							+ " FROM `test_schedule` c JOIN `question_master` d ON c.`ques_master_id`=d.`id` JOIN `test_participants` e ON e.`ts_id`=c.`id` WHERE e.`userid`="+userid;
			System.out.println("particiquery"+query);
		}else{
			query = "SELECT `cs_id` cs_id,`program_name` program_name,CONCAT(`course_name`,'-',`schedule_name`)course_name,`course_desc` course_desc,`duration` duration,`total_participants_allowed` total_participants_allowed,DATE_FORMAT(`start_date`, '%a %b %e %Y')startdate,CONCAT(`start_time`,'-',`end_time`)time,"
					+ " DATE_FORMAT(`end_date`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am' THEN "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) END AS starthours,CASE WHEN  SUBSTRING_INDEX(`end_time`, ' ', -1) = 'pm'"
							+ " THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`end_time`, ' ', -1) = 'am' THEN"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', -1), ' ', 1)endminutetime,(`total_participants_allowed`- (SELECT COUNT(*) FROM `participants_registration_course_details` c WHERE c.`course_id`=a.`cs_id` ))seatsavailable,"
							+ " 'course' scheduletype,`start_time` starttime,`end_time` endtime,`start_date` testdate  "
							+ " FROM `course_scheduling` a JOIN `course_master` b ON a.`program_name`=b.`course_id`"
							+ " UNION ALL "
							+ "  SELECT c.`id` cs_id,`ques_master_id` program_name,CONCAT(`test_name`,'-',`test_schedule_id`)course_name,`test_name` course_desc,"
							+ " `start_time` duration,`total_participant_attempted` total_participants_allowed,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')startdate,"
							+ " CONCAT(`start_time`,'-',`end_time`)TIME,DATE_FORMAT(c.`testdate`, '%a %b %e %Y')enddate,CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN  "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am' THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) "
							+ " END AS starthours,CASE WHEN  SUBSTRING_INDEX(`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) + 12 "
							+ " WHEN SUBSTRING_INDEX(`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', 1), ' ', 1) "
							+ "  END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime, "
							+ " SUBSTRING_INDEX(SUBSTRING_INDEX(`end_time`, ':', -1), ' ', 1)endminutetime,'1' seatsavailable,'test' scheduletype,"
							+ " `start_time` starttime,`end_time` endtime,`testdate` `testdate`"
							+ " FROM `test_schedule` c JOIN `question_master` d ON c.`ques_master_id`=d.`id`";
	
		}
		@SuppressWarnings("unchecked")
		List<Object> loadeventsList = jdbcTemplate.query(query, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("eventid", rs.getString("cs_id"));
				map.put("time", rs.getString("time"));
				map.put("eventname", rs.getString("course_name"));
				map.put("eventdesc", rs.getString("course_desc"));
				map.put("duration", rs.getString("duration") +" "+ "Days");
				map.put("totalparticipants", rs.getString("total_participants_allowed"));
				map.put("seatsavailable", rs.getString("seatsavailable"));
				//map.put("startdate", rs.getString("startdate") + " 12:00:00 GMT+0530 (India Standard Time)");	
				map.put("startdate", rs.getString("startdate") +" "   +rs.getString("starthours")+":"+rs.getString("startminutetime")+":00 GMT+0530 (India Standard Time)");	
				map.put("enddate", rs.getString("enddate")+" " +rs.getString("endhours")+":"+rs.getString("endminutetime")+":00 GMT+0530 (India Standard Time)");	
				map.put("scheduletype", rs.getString("scheduletype"));
				map.put("starttime", rs.getString("starttime"));
				map.put("endtime", rs.getString("endtime"));
				map.put("testdate", rs.getString("testdate"));
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(loadeventsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}

	@Override
	public oep_ResponseInfo getdecrypturl(String formData) {
		// TODO Auto-generated method stub
		log.info("url "+formData);
		
		try {
			
			oep_LoginRequest request = (oep_LoginRequest) oep_UTIL.convertJSONtooOBJECT(formData, oep_LoginRequest.class);
		
			
			String testurl = request.getUrl().substring(request.getUrl().lastIndexOf("?") + 1);
			log.info("decrypturl "+testurl);
			/*String decoded = java.net.URLDecoder.decode(testurl, "UTF-8");
			log.info("decrypturl "+decoded);
			oep_UTIL td= new oep_UTIL();
				String decrypturl = td.urldecrypt(decoded);
				log.info("decrypturl "+decrypturl);*/
			
				String testscheduleid =  testurl.substring(testurl.lastIndexOf("=") + 1);
				
			    log.info("testscheduleid "+testscheduleid);
			 	
				response.setResponseType("S");
				response.setResponseObj(testscheduleid);
			responseInfo.setInventoryResponse(response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
		return responseInfo;
	}



	@Override
	public oep_ResponseInfo getdashboarddetails() {
		// TODO Auto-generated method stub
		String cancelledtestquery="SELECT COUNT(*) cancelled FROM `test_schedule` WHERE MONTH(`testdate`) = MONTH(CURRENT_DATE()) AND YEAR(`testdate`) = YEAR(CURRENT_DATE()) AND `status` = 0";
		
		@SuppressWarnings("unchecked")
		List<Object>cancelltestList = jdbcTemplate.query(cancelledtestquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cancelled", rs.getString("cancelled"));
				return map;
			}
		});
		
       String completedtestquery="SELECT COUNT(*) completed FROM `test_schedule` WHERE MONTH(`testdate`) = MONTH(CURRENT_DATE()) AND YEAR(`testdate`) = YEAR(CURRENT_DATE()) AND `is_test_started` ='2'";
		
		@SuppressWarnings("unchecked")
		List<Object>completedtestList = jdbcTemplate.query(completedtestquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("completed", rs.getString("completed"));
				return map;
			}
		});
		
		String scheduledcoursequery = "SELECT COUNT(*)scheduled FROM `course_scheduling` WHERE  MONTH(`start_date`) = MONTH(CURRENT_DATE()) AND YEAR(`start_date`) = YEAR(CURRENT_DATE())";
		@SuppressWarnings("unchecked")
		List<Object>scheduledcourseList = jdbcTemplate.query(scheduledcoursequery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("scheduled", rs.getString("scheduled"));
				return map;
			}
		});
		
		String newpartcipantquery = "SELECT COUNT(*)participant FROM `participants` WHERE MONTH(`created_date`) = MONTH(CURRENT_DATE()) AND YEAR(`created_date`) = YEAR(CURRENT_DATE()) ";
		@SuppressWarnings("unchecked")
		List<Object>newparticipantList = jdbcTemplate.query(newpartcipantquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("participant", rs.getString("participant"));
				return map;
			}
		});
		String newtaskquery="SELECT ((SELECT COUNT(*) FROM `course_master` WHERE `created_date`=CURDATE()) + "
				+ " (SELECT COUNT(*) FROM `participants_registration_course_details` WHERE `created_date`=CURDATE()) +(SELECT COUNT(*) "
				+ "FROM `participants_registration_course_details` WHERE `created_date`=CURDATE()) +(SELECT COUNT(*) FROM `question_master`"
				+ " WHERE `created_date`=CURDATE()) +(SELECT COUNT(*) FROM `test_schedule` WHERE `created_at`=CURDATE()) +(SELECT COUNT(*) FROM `test_participants`"
				+ " WHERE `started_at`=CURDATE()) +(SELECT COUNT(*) FROM `users` WHERE `created_at`=CURDATE()) +(SELECT COUNT(*) FROM `course_scheduling`"
				+ " WHERE `created_at`=CURDATE()))AS newtaskcount";
		
		@SuppressWarnings("unchecked")
		List<Object>newtaskList = jdbcTemplate.query(newtaskquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("newtaskcount", rs.getString("newtaskcount"));
				return map;
			}
		});
		
		
		String resultAnnounchedquery = "SELECT COUNT(*) result FROM `test_participants` WHERE `is_participant_start` = 2 AND `started_at` = CURDATE()";
		@SuppressWarnings("unchecked")
		List<Object>resultAnnouncedList = jdbcTemplate.query(resultAnnounchedquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("result", rs.getString("result"));
				return map;
			}
		});
		
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		detailsmap.put("cancelltestList", cancelltestList);
		detailsmap.put("completedtestList", completedtestList);
		detailsmap.put("scheduledcourseList", scheduledcourseList);
		detailsmap.put("newparticipantList", newparticipantList);
		detailsmap.put("newtaskList", newtaskList);
		detailsmap.put("resultannouncedList", resultAnnouncedList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


	@Override
	public oep_ResponseInfo multipleSave(String formData,String id,String form,String type,
			String sessionname,String foldername,CommonsMultipartFile file,HttpServletRequest httpRequest) throws ClassNotFoundException {
		
		
		String resultfilename = "";		
		List<Object> outputList = new ArrayList<Object>(); 
		int inttype = 0,intid = 0;
		
		String filename=file.getOriginalFilename();
		 
		if(type != null)
       {
     	  inttype = Integer.parseInt(type);		
       }
       if(id != null)
       {
       	intid = Integer.parseInt(id);
       }
       
       	int extensionIndex = filename.lastIndexOf(".");      	
       	if(extensionIndex == -1  ){ 
       		
     			return (oep_ResponseInfo) outputList;
       	}
       	else{
       		
       	String fileextension = filename.substring(extensionIndex,filename.length());
       	
			String path=oep_UTIL.getuploadfilepath(httpRequest, form, inttype,intid); 
			filename = oep_UTIL.getuploadfilename(filename, intid,fileextension);	
			/*resultfilename = filename;*/
	        
	        try{
	        	
	        byte barr[]=file.getBytes();  
	        File uploadPath = new File(path);
	      
	        if(!uploadPath.exists())
	        {
	        	uploadPath.mkdirs();
	        }
	       
	        filename = path+File.separator+filename;
	        resultfilename = filename;
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filename));  
	        
	        HttpSession session = httpRequest.getSession();
	        session.setAttribute(sessionname, filename);
	        
	        log.info("filename"+filename);
	        log.info("sessionname"+sessionname);
	        
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	          
	        }
	        catch(Exception e)
	        {
	        	
	        }  
	      
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("id", id);
	        map.put("type", type);
	        map.put("form", form);
	        map.put("filename", resultfilename);
	        map.put("foldername", foldername);
	        map.put("color","green");
	        map.put("out_result_msg","File Uploaded Successfully");
	        map.put("out_result_type","S");
	        map.put("update-count-1", inttype);
	       
	        outputList.add(map);
	      
       	 response.setResponseObj(map);
	     response.setResponseType("S");
		 responseInfo.setInventoryResponse(response);
	     return responseInfo;
		
	}
	}

	@Override
	public oep_ResponseInfo checkcourseregisterparticipants(String scheduleid,
			String userid) {
		// TODO Auto-generated method stub
		String checkquery="SELECT COUNT(*)checkcount FROM `participants_registration_course_details` a JOIN `participants` b "
				+ " ON a.`participant_id`=b.`participant_id` JOIN `users` c ON c.`userid`=b.`user_id` WHERE `course_id`='"+scheduleid+"' AND c.`userid`="+userid;
		@SuppressWarnings("unchecked")
		List<Object>checkparticipantList = jdbcTemplate.query(checkquery, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("checkcount", rs.getString("checkcount"));
				return map;
			}
		});
		response.setResponseType("S");
		response.setResponseObj(checkparticipantList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
		
	}
	
}

