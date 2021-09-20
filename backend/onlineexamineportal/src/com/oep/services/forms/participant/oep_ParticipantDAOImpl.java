package com.oep.services.forms.participant;
import java.sql.PreparedStatement;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.json.JSONException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.admin.security.emailsender;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.course.oep_CourseRequest;



public class oep_ParticipantDAOImpl implements oep_IParticipantDAO {

	
	private static Logger log = Logger.getLogger(oep_ParticipantDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private static emailsender mailMail;
	
	@Autowired
	private oep_Response response;
	
	@Autowired
	private oep_ResponseInfo responseInfo;
	
	
	@Override
	public oep_ResponseInfo saveparticipantdetails(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			
			oep_ParticipantRequest request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
			List<oep_ParticipantRequest> educationitemList =  oep_UTIL.convertJSONArraytoList(request.getEducationarray(),
					"com.oep.services.forms.participant.oep_ParticipantRequest");	
			List<oep_ParticipantRequest> skillsitemList =  oep_UTIL.convertJSONArraytoList(request.getSkillsarray(),
					"com.oep.services.forms.participant.oep_ParticipantRequest");	
			HttpSession session = httpRequest.getSession();
						
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_FIRSTNAME",request.getFirstname());
			inParamMap.put("P_LASTNAME",request.getLastname());
			inParamMap.put("P_USERNAME",request.getUsername());
			inParamMap.put("P_PASSWORD",request.getPassword());
			inParamMap.put("P_APPLICABLE_IC",request.getApplicableic());
			inParamMap.put("P_DEPARTMENT",request.getDepartment());
			inParamMap.put("P_MIDDLE_NAME",request.getMiddlename());
			inParamMap.put("P_EMPLOYEE_ID",request.getEmployeeid());
			inParamMap.put("P_DESCRIPTION",request.getDescription());			
			inParamMap.put("P_EMAIL",request.getEmail());			
			inParamMap.put("P_COURSE_ID",request.getCourseid());
			inParamMap.put("P_GENDER",request.getGender());
			inParamMap.put("P_DOB",request.getDob());
			inParamMap.put("P_YEAR_ID","1");
			inParamMap.put("P_REG_NO",request.getRegno());	
			inParamMap.put("P_REG_TYPE",request.getRegtype());		
			/*inParamMap.put("P_CREATED_DATE",request.getRegno());
			inParamMap.put("P_MODIFIED_DATE",request.getRegno());*/
			inParamMap.put("P_USERID",request.getUserid());
			inParamMap.put("P_OPRN","UPD");
			inParamMap.put("P_REFNO",request.getRefno());
			inParamMap.put("P_IMAGEPATH",request.getRefno());
			inParamMap.put("P_CITY",request.getCity());	
			inParamMap.put("P_ADDRESS",request.getAddress());
			inParamMap.put("P_PINCODE",request.getPincode());
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_PARTICIPANT_MODULE", inParamMap);
			log.info(request.getFilename());
			String participantid= (String) resultMap.get("out_genrate_id");
			savepartcipanteducation(educationitemList,participantid);
			saveparticipantskills(skillsitemList,participantid);
			if(resultMap.get("out_result_type") != null && resultMap.get("out_result_type").equals("S") && request.getFilename() != null)
			{
				if(request.getFilename() !=null){
				String oldimagefilepath = request.getFilename(); 
				
				log.info("proddescpath    "+ oldimagefilepath);				
				int partid = Integer.parseInt((String) resultMap.get("out_genrate_id"));
				log.info(partid);

				if(partid > 0)
				{
									
					String newpath = oep_UTIL.getserverfilepath(httpRequest);
					log.info(newpath);
					String modulename = oep_UTIL.getmodulename("product", 2);
					log.info(modulename);
					newpath = newpath.concat(File.separator).concat(modulename);								
					log.info(newpath);
					
					
					File uploadPath = new File(newpath);
					if(!uploadPath.exists())
					{
						uploadPath.mkdirs();
					}
					newpath = newpath.concat(File.separator).concat(partid+".jpg");
					log.info(newpath);
					
					if(oldimagefilepath!=null){
					/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
						  File oldfile = new File(oldimagefilepath);
						  if(oldfile.exists()){
	        	  		File file = new File(newpath);
						if(file.exists())
						{
							java.nio.file.Path fileToDeletePath = Paths.get(newpath);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						  }
					/*}*/
					
					new File(oldimagefilepath).renameTo(new File(newpath));
					}
					
					String imagePath = oep_UTIL.filepath(newpath);
					log.info(imagePath);
					/*End*/
					
					SimpleJdbcCall jdbcCALLAaftersave = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_PARTICIPANT_MODULE");
					Map<String, Object> inParamMap2 = new HashMap<String, Object>();
						
					inParamMap2.put("P_FIRSTNAME",request.getFirstname());
					inParamMap2.put("P_EMPLOYEE_ID",request.getEmployeeid());
					inParamMap2.put("P_LASTNAME",request.getLastname());
					inParamMap2.put("P_USERNAME",request.getUsername());
					inParamMap2.put("P_PASSWORD",request.getPassword());
					inParamMap2.put("P_APPLICABLE_IC",request.getApplicableic());
					inParamMap2.put("P_DEPARTMENT",request.getDepartment());
					inParamMap2.put("P_MIDDLE_NAME",request.getMiddlename());
					inParamMap2.put("P_DESCRIPTION",request.getDescription());	
					inParamMap2.put("P_PASSWORD",request.getPassword());
					inParamMap2.put("P_EMAIL",request.getEmail());			
					inParamMap2.put("P_COURSE_ID",request.getCourseid());
					inParamMap2.put("P_GENDER",request.getGender());
					inParamMap2.put("P_DOB",request.getDob());
					inParamMap2.put("P_YEAR_ID","1");
					inParamMap2.put("P_REG_NO",request.getRegno());	
					inParamMap2.put("P_REG_TYPE",request.getRegtype());		
					inParamMap2.put("P_USERID",request.getUserid());
					inParamMap2.put("P_OPRN","UPD");
					inParamMap2.put("P_REFNO",request.getRefno());
					inParamMap2.put("P_CITY",request.getCity());	
					inParamMap2.put("P_ADDRESS",request.getAddress());
					inParamMap2.put("P_PINCODE",request.getPincode());
					inParamMap2.put("P_IMAGEPATH",imagePath);
					
					log.info("inParamMap2         "+inParamMap2);
					
					SqlParameterSource in2 = new MapSqlParameterSource(inParamMap2);
					Map<String, Object> resultMap2 = jdbcCALLAaftersave.execute(in2);
				}
			response.setResponseObj(resultMap);

			
		}
			}
		}
		
				catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	@Transactional
	public void savepartcipanteducation( final List<oep_ParticipantRequest> list,final String participantid) 
	{
		jdbcTemplate.execute("delete  from partcipant_master_education_details where participant_id ="+participantid);
		try
		{
			   log.info("HELLO");
			   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_PARTCIPANT_EDUCATION_DETAILS( ?,?,?,?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
				public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
					try {
						oep_ParticipantRequest item = list.get(i);
				
				ps.setString(1, participantid);
				ps.setString(2,item.getQualification());	
				ps.setString(3,item.getCourse());	
				ps.setString(4,item.getSpecification());
				ps.setString(5,item.getUniversity());
				if(item.getEducationstartdate() !=null){
					ps.setDate(6,(Date)oep_UTIL.convertToNewSQLDate(item.getEducationstartdate()));
				}
				else{
					ps.setString(6,"");
				}
				if(item.getEducationtodate() != null){
					ps.setDate(7,(Date)oep_UTIL.convertToNewSQLDate(item.getEducationtodate()));
				}else{
					ps.setString(7,"");
				}
			
				ps.setString(8,"INS");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		}
		catch( Exception e )
		{
			
			throw e;
		}
	}
	
	@Transactional
	public void saveparticipantskills( final List<oep_ParticipantRequest> list,final String participantid) 
	{
		jdbcTemplate.execute("delete  from partcipant_master_skills_details where participant_id ="+participantid);
		try
		{
			   log.info("HELLO");
			   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_PARTCIPANT_SKILLS_DETAILS( ?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
				public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
					oep_ParticipantRequest item = list.get(i);
				
				ps.setString(1, participantid);
				ps.setString(2,item.getSkill());	
				ps.setString(3,"INS");	
				
			}
		});
		}
		catch( Exception e )
		{
			
			throw e;
		}
	}
	
	
	
	@Override
	public oep_ResponseInfo getParticipantdetails(String userid) {
		
		String query = "SELECT `username`,`password`,`first_name`,`employee_id`,`description`,a.`applicable_ic`, `imagepath`, `last_name`,`contact_no`,`gender`,DATE_FORMAT(`dob`, '%Y/%m/%d') dob,DATE_FORMAT(`dob`, '%d/%m/%Y') fielddob,a.`created_date`,`ref_no`,"
				+ "`user_id`,`department`,`reg_no`,`course_name`,`email_id`,`year_id`,`middle_name`,b.`course_id`, `address`,`city`,`pincode` FROM `participants`a "
				+ " LEFT JOIN `participants_registration_course_details`b ON a.`participant_id`= b.`participant_id`"
				+ " LEFT JOIN `course_master`c ON c.`course_id`=b.`course_id` WHERE `user_id` ="+userid+" GROUP BY `user_id`";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> participantdetailsList = jdbcTemplate.query(query, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("coursename", rs.getString("course_name"));
				map.put("department", rs.getString("department"));
				map.put("employeeid", rs.getString("employee_id"));
				map.put("imagepath", rs.getString("imagepath"));
				map.put("username", rs.getString("username"));				
				map.put("middlename", rs.getString("middle_name"));
				map.put("applicableic", rs.getString("applicable_ic"));
				map.put("description", rs.getString("description"));				
				map.put("password", rs.getString("password"));
				map.put("firstname", rs.getString("first_name"));
				map.put("lastname", rs.getString("last_name"));
				map.put("courseid", rs.getString("course_id"));
				map.put("emailid", rs.getString("email_id"));
				map.put("userid", rs.getString("user_id"));
				map.put("gender", rs.getString("gender"));
				map.put("dob", rs.getString("dob"));
				map.put("refno", rs.getString("ref_no"));
				map.put("regno", rs.getString("reg_no"));
				map.put("contactno", rs.getString("contact_no"));
				map.put("address", rs.getString("address"));
				map.put("city", rs.getString("city"));
				map.put("pincode", rs.getString("pincode"));
				map.put("fielddob", rs.getString("fielddob"));
				
				
				return map;
				
			}
		});
		
		String skillquery="SELECT * FROM `partcipant_master_skills_details` a JOIN `participants` b ON a.`participant_id`=b.`participant_id` "
				+ " JOIN `users` c ON b.`user_id` = c.`userid` WHERE c.`userid` = "+userid;
		@SuppressWarnings("unchecked")
		List<Object> skilldetailsList = jdbcTemplate.query(skillquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("skill", rs.getString("skill"));
				return map;
				
			}
		});
		
		
		String educationquery="SELECT `qualification`,`course`,`specification`,`university`,`start_date`,`end_date`,DATE_FORMAT(start_date,'%Y/%m/%d')field_start_date,DATE_FORMAT(end_date,'%Y/%m/%d')field_end_date FROM `partcipant_master_education_details` a JOIN `participants` b ON a.`participant_id`=b.`participant_id` "
				+ " JOIN `users` c ON b.`user_id` = c.`userid` WHERE c.`userid` = "+userid;
		@SuppressWarnings("unchecked")
		List<Object> educationdetailsList = jdbcTemplate.query(educationquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("qualification", rs.getString("qualification"));
				map.put("course", rs.getString("course"));
				map.put("specification", rs.getString("specification"));
				map.put("university", rs.getString("university"));
				map.put("educationstartdate", rs.getString("start_date"));
				map.put("educationsfieldtartdate", rs.getString("field_start_date"));
				map.put("educationsfieldenddate", rs.getString("field_end_date"));
				map.put("educationtodate", rs.getString("end_date"));
				return map;
				
			}
		});
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		detailsmap.put("participantdetailsList", participantdetailsList.get(0));
		detailsmap.put("skilldetailsList", skilldetailsList);
		detailsmap.put("educationdetailsList", educationdetailsList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getParticipantTestList(String userid) {
		
		String query = "SELECT  `ques_master_id`,`test_id`,`test_name`,`sub_id`,`is_test_started`,a.`batch`,"
				+ " DATE_FORMAT(a.`testdate`,'%e/%c/%Y')testdate, a.`start_time`,a.`end_time`,"
				+ " CASE WHEN  SUBSTRING_INDEX(`start_time`,' ' , -1) = 'pm' THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1), ' ', 1) "
				+ " + 12 WHEN SUBSTRING_INDEX(`start_time`,' ' , -1) = 'am' THEN  SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', 1),' ',1)END AS starthours,"
				+ " CASE WHEN  SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'pm' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), "
				+ " ' ', 1) + 12 WHEN SUBSTRING_INDEX(a.`end_time`, ' ', -1) = 'am' THEN SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', 1), ' ', 1)"
				+ "  END AS endhours,SUBSTRING_INDEX(SUBSTRING_INDEX(`start_time`, ':', -1), ' ', 1)startminutetime,"
				+ " SUBSTRING_INDEX(SUBSTRING_INDEX(a.`end_time`, ':', -1), ' ', 1)endminutetime FROM `test_schedule`a "
				+ " JOIN `test_participants`b ON a.`id`= b.`ts_id` JOIN `question_master` c ON c.`id` = a.`ques_master_id` "
				+ " WHERE `userid` ="+userid+" AND `testdate` = CURRENT_DATE  AND `is_test_started`= 1 ";
			log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> TestList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("questionmasterid", rs.getString("ques_master_id"));
				map.put("testid", rs.getString("test_id"));
				map.put("testname", rs.getString("test_name"));
				map.put("subjectid", rs.getString("sub_id"));
				map.put("starthours", rs.getString("starthours"));
				map.put("endhours", rs.getString("endhours"));
				map.put("startminutetime", rs.getString("startminutetime"));
				map.put("endminutetime", rs.getString("endminutetime"));
				map.put("batch", rs.getString("batch"));
				map.put("testdate", rs.getString("testdate"));
				map.put("starttime", rs.getString("start_time"));
				map.put("endtime", rs.getString("end_time"));	
				map.put("index", count);
				
				if(rs.getString("is_test_started")!= null && rs.getString("is_test_started").equals("1")){
					map.put("teststarted", "Start test");
				}else{
					map.put("teststarted", "Not started");
				}
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(TestList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	public oep_ResponseInfo getParticipantList(String roleid, String userid) {
		
		String query = "";
		
		if( Integer.parseInt(roleid) > 0){
			
		if(Integer.parseInt(roleid) == 4){
			query = "SELECT `username`,a.`participant_id`,`password`,`first_name`,`last_name`,`gender`,`dob`,a.`created_date`,`ref_no`,`course_name`,"
					+ " `user_id`,`reg_no`,`email_id`,`year_id`,b.`course_id`,`is_approved`,`prcd_id` FROM `participants`a"
					+ "  JOIN `participants_registration_course_details`b ON a.`participant_id`= b.`participant_id` "
					/*+ " LEFT JOIN `course_master`c ON c.`course_id`=b.`course_id` GROUP BY a.`participant_id` ";*/
					/*+ "  JOIN `course_master`c ON c.`course_id`=b.`course_id` AND a.`user_id` = '"+userid+"' "*/
					+ "LEFT JOIN `course_scheduling` c ON c.`cs_id`= b.`course_id` JOIN `course_master` d ON d.`course_id` = c.`program_name`"
					+ " WHERE a.`user_id` = '"+userid+"' ";
		}else{
			query = "SELECT `username`,a.`participant_id`,`password`,`first_name`,`last_name`,`gender`,`dob`,a.`created_date`,`ref_no`,`course_name`,"
					+ " `user_id`,`reg_no`,`email_id`,`year_id`,b.`course_id`,`is_approved`,`prcd_id` FROM `participants`a"
					+ " LEFT JOIN `participants_registration_course_details`b ON a.`participant_id`= b.`participant_id` "
					/*+ " LEFT JOIN `course_master`c ON c.`course_id`=b.`course_id` GROUP BY a.`participant_id` ";*/
//					+ " LEFT JOIN `course_master`c ON c.`course_id`=b.`course_id` "
					+ " LEFT JOIN `course_scheduling` c ON c.`cs_id`= b.`course_id` JOIN `course_master` d ON d.`course_id` = c.`program_name`";
		}
			 
		 
		log.info(query);
			
			@SuppressWarnings("unchecked")
			List<Object> participantList = jdbcTemplate.query(query, new RowMapper() {
				int count = 1;
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("username", rs.getString("username"));
					map.put("partid", rs.getString("participant_id"));
					map.put("coursename", rs.getString("course_name"));
					map.put("password", rs.getString("password"));
					map.put("first_name", rs.getString("first_name"));
					map.put("last_name", rs.getString("last_name"));
					map.put("gender", rs.getString("gender"));
					map.put("courseid", rs.getString("course_id"));
					map.put("dob", rs.getString("dob"));
					map.put("ref_no", rs.getString("ref_no"));
					map.put("userid", rs.getString("user_id"));
					map.put("reg_no", rs.getString("reg_no"));
					map.put("email_id", rs.getString("email_id"));
					map.put("year_id", rs.getString("year_id"));
					map.put("createddate", rs.getString("created_date"));
					map.put("isapproved", rs.getString("is_approved"));
					map.put("coursedetailid", rs.getString("prcd_id"));
					map.put("index", count);			
					count++;
					return map;
					
				}
			});
			response.setResponseType("S");
			response.setResponseObj(participantList);
			responseInfo.setInventoryResponse(response);
			
		}else{
			response.setResponseType("F");
			responseInfo.setInventoryResponse(response);
		}
			return responseInfo;
		}

	
	
public oep_ResponseInfo getParticipantList() {
		
	String query = "SELECT `username`,a.`participant_id`,`password`,`first_name`,`last_name`,`gender`,`dob`,a.`created_date`,`ref_no`,`course_name`,"
			+ " `user_id`,`reg_no`,`email_id`,`year_id`,b.`course_id`,`is_approved`,`prcd_id` FROM `participants`a"
			+ " LEFT JOIN `participants_registration_course_details`b ON a.`participant_id`= b.`participant_id` "
			+ " LEFT JOIN `course_master`c ON c.`course_id`=b.`course_id` GROUP BY a.`participant_id` ";
	log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> participantList = jdbcTemplate.query(query, new RowMapper() {
			int count = 1;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", rs.getString("username"));
				map.put("partid", rs.getString("participant_id"));
				map.put("coursename", rs.getString("course_name"));
				map.put("password", rs.getString("password"));
				map.put("first_name", rs.getString("first_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("gender", rs.getString("gender"));
				map.put("courseid", rs.getString("course_id"));
				map.put("dob", rs.getString("dob"));
				map.put("ref_no", rs.getString("ref_no"));
				map.put("userid", rs.getString("user_id"));
				map.put("reg_no", rs.getString("reg_no"));
				map.put("email_id", rs.getString("email_id"));
				map.put("year_id", rs.getString("year_id"));
				map.put("createddate", rs.getString("created_date"));
				map.put("isapproved", rs.getString("is_approved"));
				map.put("coursedetailid", rs.getString("prcd_id"));
				map.put("index", count);			
				count++;
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(participantList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}

@Override
public oep_ResponseInfo loadquestions(String questionid,String userid)  {
	
	
	String testdetailquery="  SELECT a.`id`,a.`batch`batchid,DATE_FORMAT(`testdate`, '%d/%m/%Y')testdate,a.`start_time`,a.`end_time`,c.`userid`,"
			+ " COALESCE(`test_schedule_id`,'')batch,COALESCE(CONCAT(`course_name`,' ',`schedule_name`),'') coursebatch,`username` partcipantname,"
			+ " FN_OEP_TIME(a.`start_time`,a.`end_time`) duration   FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id` JOIN `test_participants` c ON c.`ts_id` = a.`id`"
			+ " JOIN `course_scheduling`d ON d.`cs_id` = a.`batch` JOIN `course_master`e ON e.`course_id` = d.`program_name`  JOIN `participants` f ON f.`participant_id`=c.`part_id` "
			+ "  WHERE a.`id` = "+questionid+" AND c.`userid`= "+userid+" AND `is_test_started` = 1  AND `is_participant_start` = 0 ";
		log.info("testdetailquery "+testdetailquery);
		
		@SuppressWarnings("unchecked")
		List<Object> testdetailList = jdbcTemplate.query(testdetailquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("batchid", rs.getString("batchid"));
				map.put("testdate", rs.getString("testdate"));
				map.put("starttime", rs.getString("start_time"));
				map.put("endtime", rs.getString("end_time"));
				map.put("batch", rs.getString("batch"));
				map.put("coursebatch", rs.getString("coursebatch"));
				map.put("partcipantname", rs.getString("partcipantname"));
				map.put("duration",  rs.getString("duration"));
				return map;
				
			}
		});
	
	if(testdetailList!=null && testdetailList.size()>0){
		
	String query = "SELECT * FROM `question_details` a JOIN `question_master` b ON a.`ques_master_id`=b.`id` "
			+ " JOIN `test_schedule` c ON c.`ques_master_id`=b.`id` join `test_participants` d on d.`ts_id`=c.`id`"
			+ " AND `userid`='"+userid+"' "
			+ "WHERE c.`id`="+questionid+" AND `is_test_started` = 1  ORDER BY RAND()  ";

	log.info(query);
	
	
	@SuppressWarnings("unchecked")
	List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionid", rs.getString("question_id"));
			map.put("question", rs.getString("question"));
			map.put("option1", rs.getString("option_1"));
			map.put("option2", rs.getString("option_2"));
			map.put("option3", rs.getString("option_3"));
			map.put("option4", rs.getString("option_4"));
			map.put("image", rs.getString("image"));
			map.put("questiontype", rs.getString("question_type"));
			//map.put("starttime", rs.getString("started_at"));
			
			return map;
			
		}
	});
	
	
	
	String participantquery="SELECT b.start_time,b.end_time FROM `test_participants` a "
			+ "JOIN `test_schedule` b ON a.`ts_id`=b.`id` WHERE `userid` = "+userid;
	
	@SuppressWarnings("unchecked")
	List<Object> participantdetailsList = jdbcTemplate.query(participantquery, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("starttime", rs.getString("start_time"));
			map.put("endtime", rs.getString("end_time"));
			
			return map;
			
		}
	});
	
	
	Map<String, Object> detailsmap = new LinkedHashMap<String, Object>();
	detailsmap.put("questiondetailsList", questiondetailsList);
	detailsmap.put("particpantdetailslist", participantdetailsList);
	detailsmap.put("particpanttestdetailList", testdetailList);
	
	if(questiondetailsList != null && questiondetailsList.size() > 0){
		
		Map<String, Object> firstQeustion = (Map<String, Object>) questiondetailsList.get(0);

		try {
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_OEP_SAVE_QUESTION_HISTORY");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			
		inParamMap.put("P_TEST_ID",questionid);
		inParamMap.put("P_USER_ID",userid);
		inParamMap.put("P_QUESTION_ID","0");
		inParamMap.put("P_QUESTION_ANSWER","");
		inParamMap.put("P_DATE",oep_UTIL.convertToNewSQLDate("12/12/2018"));
		inParamMap.put("P_TIME","");
		inParamMap.put("P_QUESTION_STATUS","-1");
		inParamMap.put("P_NEXT_QUESTION_ID",firstQeustion.get("questionid"));
		inParamMap.put("P_OPRN","FIRST_QUESTION");
		
		log.info("inParamMap2         "+inParamMap);
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info("resultMap         "+resultMap); 
		/*String updatequery="UPDATE `test_participants` SET `started_at` = NOW(),`is_participant_start`=1 WHERE `ts_id`='"+questionid+" ' AND `userid`="+userid;
		jdbcTemplate.execute(updatequery);*/
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	response.setResponseType("S");
	response.setResponseObj(detailsmap);
	responseInfo.setInventoryResponse(response);
	}
	else{
		response.setResponseType("F");
		response.setResponseObj(testdetailList);
		responseInfo.setInventoryResponse(response);
	}
	
	
	return responseInfo;
}

@Override
public oep_ResponseInfo getParticipants(String csid) {
	
	String query = "SELECT `username`,a.`participant_id`,`user_id` FROM `participants` a"
			+ " JOIN `participants_registration_course_details`b ON b.`participant_id`= a.`participant_id`"
			+ " JOIN `course_scheduling`c ON b.`course_id` = c.`cs_id` WHERE `cs_id` ="+csid+" AND b.`is_approved` = 1 ";
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> participantsList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", rs.getString("username"));
			map.put("partid", rs.getString("participant_id"));
			map.put("userid", rs.getString("user_id"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(participantsList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo savetestschedulequestion(String uploadData,
		HttpServletRequest httpRequest) {
	// TODO Auto-generated method stub
	
	try {
		String scheduleid = "0", participantid = "0";
		oep_ParticipantRequest request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
		List<oep_ParticipantRequest> itemList = oep_UTIL.convertJSONArraytoList(request.getQuestionlist(),"com.oep.services.forms.participant.oep_ParticipantRequest");
		
		String endtime = request.getEndtime();
	    savetestquestiondetails(itemList,endtime);
	    
	    Iterator< oep_ParticipantRequest> itr = itemList.iterator();
	    while(itr.hasNext()){
	    	oep_ParticipantRequest pr = itr.next();
	    	scheduleid = pr.getScheduleid();
	    	participantid = pr.getPartid();
	    }
	      
	    SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_OEP_SCHEDULED_TEST_PARTICIPANTS");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			
		inParamMap.put("P_TEST_ID",scheduleid);
		inParamMap.put("P_PART_ID",participantid);
		inParamMap.put("P_USER_ID",participantid);
		inParamMap.put("P_URL","");
		inParamMap.put("P_OPRN","UPD_MARK");
		
		log.info("inParamMap2         "+inParamMap);
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info("resultMap         "+resultMap); 
	
		//need to change
		String query = "SELECT `flag`, `response_msg`, `part_mark`,`full_mark`,`is_pass`,"
				+ " COALESCE(CONCAT(FORMAT(((`part_mark`/`full_mark`)*100),2),' %'),0)mark  FROM "
				+ " `part_final_mark_response` WHERE `part_id` = '"+participantid+"' AND `schedule_id` = '"+scheduleid+"'"
				+ " ORDER BY `id` DESC LIMIT 1";
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> outputList = jdbcTemplate.query(query, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("flag", rs.getString("flag"));
				map.put("msg", rs.getString("response_msg"));
				map.put("partmark", rs.getString("part_mark"));
				map.put("fullmark", rs.getString("full_mark"));
				map.put("mark", rs.getString("mark"));
				map.put("ispass", rs.getString("is_pass"));
				
				return map;
				
			}
		});
		
		
		response.setResponseType("S");
		response.setResponseObj(outputList);
		responseInfo.setInventoryResponse(response);
		log.info(outputList);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return responseInfo;
}

@Transactional
public void savetestquestiondetails( final List<oep_ParticipantRequest> list, final String endtime) 
{
	try
	{
		
		jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_SAVE_TEST_PARTICIPANT_QUESTION_DETAILS( ?,?,?,?,?,? )}",
			new BatchPreparedStatementSetter() {					
			public int getBatchSize()
			{
				return list.size();
			}

		public void setValues(PreparedStatement ps, int i) throws SQLException 
		{
			
			oep_ParticipantRequest item = list.get(i);
			
	        ps.setString(1, item.getScheduleid());
	        ps.setString(2, item.getPartid());
			ps.setString(3, item.getQuestionid());
			ps.setString(4, item.getQuestionanswer());
			ps.setString(5, endtime);
			ps.setString(6, "INS");
			log.info(ps);
		}
	});
	}
	catch( Exception e )
	{
		e.printStackTrace();
		throw e;
	}
}

@Override
public oep_ResponseInfo participantcertificatedetails(String uploadData, HttpServletRequest httpRequest) {
			
	try{
		
		int digits = 0;
		Random s = new Random( System.currentTimeMillis() );
		  digits = 10000 + s.nextInt(20000);
		int fileSeq = digits;
		
		oep_ParticipantRequest request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
		HttpSession session = httpRequest.getSession();
		
		List<oep_ParticipantRequest> itemList = oep_UTIL.convertJSONArraytoList(request.getPartid(),
				"com.oep.services.forms.participant.oep_ParticipantRequest");
		log.info(itemList);	
					
		/*LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		//inParamMap.put("P_PARTICIPANT_ID",request.getPartid());
		//inParamMap.put("P_TEST_ID",request.getTestid());
		inParamMap.put("P_BATCH",request.getBatch());
		//inParamMap.put("P_TEST_SCHEDULE_ID",request.getTsid());
		//inParamMap.put("P_QUESTION_ID",request.getQuestionid());
		inParamMap.put("P_RANDOM_NO",fileSeq);
		inParamMap.put("P_OPRN",request.getOprn());
		
		log.info(inParamMap);

		Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_PARTICIPANT_CERTIFICATE_DETAILS", inParamMap);
		log.info(resultMap);*/
		
		String partid = "";	
		Iterator<oep_ParticipantRequest> itr = itemList.iterator();
		while(itr.hasNext())
		{
			oep_ParticipantRequest obj = itr.next();
			//partid = obj.getPartid();
			obj.setBatch(request.getBatch());	
			obj.setTsid(request.getTsid());	
			obj.setRandomno(fileSeq);
			log.info(obj.getPartid()+"");
			
		}
		savetestparticipants(itemList, partid);
		//response.setResponseObj(resultMap);
		
		response.setResponseType("S");
		response.setResponseObj(fileSeq);
	}	
	catch(Exception e)
	{
		response.setResponseType("F");
		e.printStackTrace();
	}
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Transactional
public void savetestparticipants( final List<oep_ParticipantRequest> list,final String partid) 
{
	try
	{
		   log.info("HELLO");
		   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_PARTICIPANT_CERTIFICATE_DETAILS( ?,?,?,?,?)}",
			new BatchPreparedStatementSetter() {					
			public int getBatchSize()
			{
				return list.size();
			}
			String id = partid;
			public void setValues(PreparedStatement ps, int i) throws SQLException 
		{
				oep_ParticipantRequest item = list.get(i);
			
			ps.setString(1,item.getPartid());
			ps.setString(2,item.getBatch());
			ps.setLong(3,item.getRandomno());
			ps.setString(4,item.getTsid());
			ps.setString(5,"INS");
			
			
			log.info(item.getPartid());
			log.info(item.getBatch());
			log.info(item.getRandomno());
			log.info(item.getTsid());
			
		}
	});
	}
	catch( Exception e )
	{
		
		throw e;
	}
}


@Override
public oep_ResponseInfo getsinglecertificate(String uploadData, HttpServletRequest httpRequest) {
			
	try{
		
		int digits = 0;
		Random s = new Random( System.currentTimeMillis() );
		  digits = 10000 + s.nextInt(20000);
		int fileSeq = digits;
		
		oep_ParticipantRequest request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
		HttpSession session = httpRequest.getSession();	
		
					
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_PARTICIPANT_ID",request.getPartid());
		inParamMap.put("P_TS_ID",request.getTsid());
		inParamMap.put("P_BATCH",request.getBatch());
		inParamMap.put("P_RANDOM_NO",fileSeq);
		inParamMap.put("P_OPRN",request.getOprn());
		
		log.info(inParamMap);

		Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SINGLE_PARTICIPANT_CERTIFICATE", inParamMap);
		log.info(resultMap);
		
		response.setResponseType("S");
		response.setResponseObj(resultMap);	
		
	}	
	catch(Exception e)
	{
		response.setResponseType("F");
		e.printStackTrace();
	}
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo getParticipantList(String id) {
	
	String query = "SELECT `part_id` ,`username`,b.`batch`,`total_mark`,`full_mark`,`is_pass`,`ts_id`,"
			+ " COALESCE(CONCAT(FORMAT(((`total_mark`/`full_mark`)*100),2),' %'),0)mark FROM `test_participants`a "
			+ " JOIN `test_schedule`b ON a.`ts_id`= b.`id` JOIN `participants` c ON c.`participant_id` = a. `part_id` "
			+ " JOIN `course_scheduling` f ON f.`cs_id`= b.`batch`  WHERE b.`id` ="+id+" AND `is_participant_start` = 2 ";	
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		int count = 1;
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("partid", rs.getString("part_id"));
			map.put("username", rs.getString("username"));
			map.put("batch", rs.getString("batch"));
			map.put("index", count);
			map.put("totalmark", rs.getString("total_mark"));
			map.put("mark", rs.getString("mark"));
			map.put("fullmark", rs.getString("full_mark"));
			map.put("tsid", rs.getString("ts_id"));
			
			if(rs.getString("is_pass")!= null && rs.getString("is_pass").equals("1")){
				map.put("result", "PASS");
			}else{
				map.put("result", "FAIL");
			}
			
			count++;
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo getBatchList() {
	
	
	/*String query = "SELECT a.`batch`,`testdate`,`test_schedule_id`,`test_id`,`schedule_name`,`test_name`,`course_name`,`sub_name`,"
			+ "COALESCE(CONCAT(`course_name`,' ',`schedule_name`),' ')batchname FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`"
			+ "   JOIN `subject_master`e ON e.`sub_id`= b.`sub_id`"
			+ "  JOIN `course_scheduling` f ON f.`cs_id`= a.`batch` JOIN `course_master`d ON d.`course_id`= f.`program_name`"
			+ "  WHERE `is_test_started` = 1";*/
	
	String query = "SELECT a.`batch`,`testdate`,`test_schedule_id`,`test_id`,`schedule_name`,`test_name`,`course_name`,"
			+ "COALESCE(CONCAT(`course_name`,' ',`schedule_name`),' ')batchname FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`"
			+ "  JOIN `course_scheduling` f ON f.`cs_id`= a.`batch` "
			+ " JOIN `course_master`d ON d.`course_id`= b.`sub_id`"			
			+ "  WHERE `is_test_started` = 1  GROUP BY `schedule_name`,`course_name`";
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("batch", rs.getString("batch"));
			map.put("batchname", rs.getString("batchname"));
			map.put("testdate", rs.getString("testdate"));
			map.put("testid", rs.getString("test_id"));
			map.put("testname", rs.getString("test_name"));
			map.put("coursename", rs.getString("course_name"));
			map.put("testscheduleid", rs.getString("test_schedule_id"));
			map.put("schedulename", rs.getString("schedule_name"));
			//map.put("subjectname", rs.getString("sub_name"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo getBatchList(String partid) {
	
/*	String query = "SELECT a.`batch`,`testdate`,`test_id`,`test_name`,`course_name`,`sub_name` FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`  JOIN `test_participants`c ON c.`ts_id`= a.`id`"
			+ " JOIN `course_master`d ON d.`course_id`= a.`batch` JOIN `subject_master`e ON e.`sub_id`= b.`sub_id` "
			+ " WHERE `part_id` = "+ partid+"  ";*/
	
	String query = "SELECT a.`batch`,`testdate`,`test_id`,`test_name`,`course_name`,`sub_name` FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`  JOIN `test_participants`c ON c.`ts_id`= a.`id`"
			+ " JOIN `course_master`d ON d.`course_id`= b.`sub_id`  "
			+ " WHERE `part_id` = "+ partid+"  ";
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("batch", rs.getString("batch"));
			map.put("testdate", rs.getString("testdate"));
			map.put("testid", rs.getString("test_id"));
			map.put("testname", rs.getString("test_name"));
			map.put("coursename", rs.getString("course_name"));
			//map.put("subjectname", rs.getString("sub_name"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo getBatchdetails(String batch,String partid) {
		
	String query = "SELECT a.`batch`,`testdate`,`test_id`,`test_name`,`course_name`,b.`id`questionmaster,a.`id`tsid,"
			+ "COALESCE(FORMAT(((`total_mark`/`full_mark`)*100),2),'')percentage FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`  JOIN `test_participants`c ON c.`ts_id`= a.`id`"
			+ " JOIN `course_master`d ON d.`course_id`= b.`sub_id`  "
			+ " WHERE a.`batch` = "+ batch+" AND c.`part_id`="+partid+" ";
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("batch", rs.getString("batch"));
			map.put("testdate", rs.getString("testdate"));
			map.put("questionid", rs.getString("questionmaster"));
			map.put("tsid", rs.getString("tsid"));
			map.put("testid", rs.getString("test_id"));
			map.put("testname", rs.getString("test_name"));
			map.put("coursename", rs.getString("course_name"));
			//map.put("subjectname", rs.getString("sub_name"));
			map.put("grade", "A+");
			map.put("marks",  rs.getString("percentage"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}



@Override
public oep_ResponseInfo getBatchdetails(String batch,String partid,String tsid) {
		
	String query = "SELECT a.`batch`,`testdate`,`test_id`,`test_name`,`course_name`,b.`id`questionmaster,a.`id`tsid,"
			+ "COALESCE(FORMAT(((`total_mark`/`full_mark`)*100),2),'')percentage FROM `test_schedule`a "
			+ " JOIN `question_master`b ON a.`ques_master_id`= b.`id`  JOIN `test_participants`c ON c.`ts_id`= a.`id`"
			+ " JOIN `course_master`d ON d.`course_id`= b.`sub_id`  "
			+ " WHERE a.`batch` = "+ batch+" AND c.`part_id`="+partid+" ";
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("batch", rs.getString("batch"));
			map.put("testdate", rs.getString("testdate"));
			map.put("questionid", rs.getString("questionmaster"));
			map.put("tsid", rs.getString("tsid"));
			map.put("testid", rs.getString("test_id"));
			map.put("testname", rs.getString("test_name"));
			map.put("coursename", rs.getString("course_name"));
			//map.put("subjectname", rs.getString("sub_name"));
			map.put("grade", "A+");
			map.put("marks",  rs.getString("percentage"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo getTestscheduleList(String batch) {
	
	String query = "SELECT `id`,`test_schedule_id`,`batch` FROM `test_schedule` WHERE `batch` = "+batch+" AND `is_test_started` = 1  ";	
	
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> batchList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tsid", rs.getString("id"));
			map.put("batch", rs.getString("batch"));
			map.put("testscheduleid", rs.getString("test_schedule_id"));
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(batchList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}

@Override
public oep_ResponseInfo getParticipantdetails(String userid,String courseid) {
	
	String query = "SELECT `username`,`password`,`first_name`, `imagepath`, `last_name`,`contact_no`,`gender`,`dob`,a.`created_date`,`ref_no`,"
			+ "`user_id`,`reg_no`,`course_name`,`email_id`,`year_id`,b.`course_id`, `address`,`city`,`pincode` FROM `participants`a "
			+ "  JOIN `participants_registration_course_details`b ON a.`participant_id`= b.`participant_id`"
			+ "  JOIN `course_scheduling`c ON c.`cs_id`= b.`course_id` JOIN `course_master`d ON d.`course_id`= c.`program_name`"
			+ "  WHERE `user_id` ="+userid+" AND b.`course_id` = "+courseid+"  ";
	log.info(query);
	
	@SuppressWarnings("unchecked")
	List<Object> questiondetailsList = jdbcTemplate.query(query, new RowMapper() {
		
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int count = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("coursename", rs.getString("course_name"));
			map.put("imagepath", rs.getString("imagepath"));
			map.put("username", rs.getString("username"));
			map.put("password", rs.getString("password"));
			map.put("firstname", rs.getString("first_name"));
			map.put("lastname", rs.getString("last_name"));
			map.put("courseid", rs.getString("course_id"));
			map.put("emailid", rs.getString("email_id"));
			map.put("userid", rs.getString("user_id"));
			map.put("gender", rs.getString("gender"));
			map.put("dob", rs.getString("dob"));
			map.put("refno", rs.getString("ref_no"));
			map.put("regno", rs.getString("reg_no"));
			map.put("contactno", rs.getString("reg_no"));
			map.put("contactno", rs.getString("contact_no"));
			map.put("address", rs.getString("address"));
			map.put("city", rs.getString("city"));
			map.put("pincode", rs.getString("pincode"));
			
			return map;
			
		}
	});
	response.setResponseType("S");
	response.setResponseObj(questiondetailsList);
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}


@Override
public oep_ResponseInfo sendmailtoparticipant(String uploadData, HttpServletRequest httpRequest) {
			
	try{
		
		log.info(uploadData);
		oep_ParticipantRequest request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
		
		HttpSession session = httpRequest.getSession();
					
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_DOWNLOAD_LINK",request.getDownloadlink());
		inParamMap.put("P_PARTICIPANT_ID",request.getPartid());
		inParamMap.put("P_TS_ID",request.getTsid());
		inParamMap.put("P_OPRN",request.getOprn());
	
		log.info(inParamMap);

		Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_SEND_PARTICIPANT_COURSE_CERTIFICATE", inParamMap);
		
		log.info(resultMap);
		response.setResponseObj(resultMap);
		String imh_id = (String) resultMap.get("out_genrate_id");
		String imh_facid = (String) resultMap.get("out_genrate_facultyid");
		String imh_adminid = (String) resultMap.get("out_genrate_adminid");
		
        log.info("imh_id value is "+  imh_id);		
		
		String link = request.getDownloadlink();
		String tsid = request.getTsid();
		int randomno = request.getRandomno();		
		
		Connection conn=jdbcTemplate.getDataSource().getConnection();
		String path=httpRequest.getSession().getServletContext().getRealPath("reports").concat(File.separator).concat("certificate.jasper");  
		  System.out.println("path "+path);
		  String printFileName = null;
		  String imageURL = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=0";
	      String imageURL1 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=1";
	      String imageURL2 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=2";
	      String imageURL3 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=3";
		  HashMap<String, Object> map = new HashMap<String, Object>();
		  map.put("rand_no",request.getRandomno());
		  map.put("logo",imageURL);
		  map.put("president_sign",imageURL1);
		  map.put("organiser_sign",imageURL2);
		  map.put("comp_logo", imageURL3);
		/*  map.put("datasource", jdbcTemplate.getDataSource());*/
		  try {
			  JasperPrint  jasperPrint  = JasperFillManager.fillReport(path,
					  map,conn);
			
			  String downloadpath=httpRequest.getSession().getServletContext().getRealPath("reports").concat(File.separator).concat("Course completion.pdf");
			  JasperExportManager.exportReportToPdfFile(jasperPrint,
					  downloadpath);
			    System.out.println("Completed");
			    oep_UTIL.sendattachedmailfromSql(imh_id,downloadpath);
			    oep_UTIL.sendattachedmailfromSql(imh_facid,downloadpath);
			    //oep_UTIL.sendattachedmailfromSql(imh_adminid,downloadpath);
			    sendmailtoAlladmin(link,tsid,randomno, httpRequest);
		  }
		  catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	catch(Exception e)
	{
		e.printStackTrace();		
	}
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}

@Transactional
public void sendmailtoAlladmin(String link, String tsid, int randomno, HttpServletRequest httpRequest) throws Exception 
{
	try
	{
		   
		   String query = "SELECT `email` FROM `users` WHERE `role` = 1 ";
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
				
			   String mailcontent = "Please click on the Link  " +link+ "  to download your course certificate.";
			   String subject = "course certificate";
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
				
				Connection conn=jdbcTemplate.getDataSource().getConnection();
				String path=httpRequest.getSession().getServletContext().getRealPath("reports").concat(File.separator).concat("certificate.jasper");  

				  String printFileName = null;
				  String imageURL = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=0";
			      String imageURL1 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=1";
			      String imageURL2 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=2";
			      String imageURL3 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=3";
			      
				  HashMap<String, Object> map1 = new HashMap<String, Object>();
				  map1.put("rand_no",randomno);
				  map1.put("logo",imageURL);
				  map1.put("president_sign",imageURL1);
				  map1.put("organiser_sign",imageURL2);
				  map1.put("comp_logo", imageURL3);
				  
				  try {
					  JasperPrint  jasperPrint  = JasperFillManager.fillReport(path,
							  map1,conn);
					
					  String downloadpath=httpRequest.getSession().getServletContext().getRealPath("reports").concat(File.separator).concat("Course completion.pdf");
					  JasperExportManager.exportReportToPdfFile(jasperPrint,
							  downloadpath);
					    System.out.println("Course completion Completed");					  
					    oep_UTIL.sendattachedmailfromSql(imh_id,downloadpath);
					    
				  }
				  catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		   }
		   
		   
	}
	catch( Exception e )
	{
		
		throw e;
	}
}
@Override
public oep_ResponseInfo savetestquestiontime(String uploadData,
		HttpServletRequest httpRequest) {
	
	oep_ParticipantRequest request;
	try {
		request = (oep_ParticipantRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_ParticipantRequest.class);
	
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_OEP_SAVE_QUESTION_HISTORY");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			
		inParamMap.put("P_TEST_ID",request.getTsid());
		inParamMap.put("P_USER_ID",request.getUserid());
		inParamMap.put("P_QUESTION_ID",request.getQuestionid());
		inParamMap.put("P_QUESTION_ANSWER",request.getQuestionanswer());
		inParamMap.put("P_DATE",oep_UTIL.convertToNewSQLDate(request.getDate()));
		inParamMap.put("P_TIME",request.getTime());
		inParamMap.put("P_QUESTION_STATUS",request.getQuestion_status());
		inParamMap.put("P_NEXT_QUESTION_ID",request.getNextquestionid());
		inParamMap.put("P_OPRN","CONTINUOUS_QUESTION");
		
		log.info("inParamMap2         "+inParamMap);
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info("resultMap         "+resultMap); 
		response.setResponseObj(resultMap);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	responseInfo.setInventoryResponse(response);
	return responseInfo;
}

}