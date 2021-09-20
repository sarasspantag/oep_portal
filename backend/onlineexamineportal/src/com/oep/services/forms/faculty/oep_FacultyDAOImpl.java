package com.oep.services.forms.faculty;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.course.oep_CourseRequest;


public class oep_FacultyDAOImpl implements oep_IFacultyDAO{

	private static Logger log = Logger.getLogger(oep_FacultyDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private oep_Response response;
	
	@Autowired
	private oep_ResponseInfo responseInfo;
	
	

	
	
	@Override
	public oep_ResponseInfo savefacultydetails(String uploadData, HttpServletRequest httpRequest) {
				
		try{
			log.info(uploadData);
			oep_FacultyRequest request = (oep_FacultyRequest) oep_UTIL.convertJSONtooOBJECT(uploadData, oep_FacultyRequest.class);
			HttpSession session = httpRequest.getSession();
			List<oep_FacultyRequest> experienceitemList =  oep_UTIL.convertJSONArraytoList(request.getExperiencearray(),
					"com.oep.services.forms.faculty.oep_FacultyRequest");	
			List<oep_FacultyRequest> educationitemList =  oep_UTIL.convertJSONArraytoList(request.getEducationarray(),
					"com.oep.services.forms.faculty.oep_FacultyRequest");	
			List<oep_FacultyRequest> skillsitemList =  oep_UTIL.convertJSONArraytoList(request.getSkillsarray(),
					"com.oep.services.forms.faculty.oep_FacultyRequest");	
			
			LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
			inParamMap.put("P_FACULTY_ID",request.getFacultyid());
			inParamMap.put("P_FIRSTNAME",request.getFirstname());
			inParamMap.put("P_LASTNAME",request.getLastname());			
			inParamMap.put("P_APPLICABLE_IC",request.getApplicableic());
			inParamMap.put("P_MIDDDLE_NAME",request.getMiddlename());
			inParamMap.put("P_DESCRIPTION",request.getDescription());						
			inParamMap.put("P_USERNAME",request.getUsername());
			inParamMap.put("P_PASSWORD",request.getPassword());
			inParamMap.put("P_EMAIL",request.getEmail());			
			inParamMap.put("P_CONTACT_NO",request.getContactno());
			inParamMap.put("P_GENDER",request.getGender());
			inParamMap.put("P_DOB",request.getDob());
			inParamMap.put("P_QUALIFICATION",request.getQualification());
			inParamMap.put("P_OCCUPATION",request.getOccupation());			
			inParamMap.put("P_EXPERIENCE",request.getExperience());
			inParamMap.put("P_MAIN_SUBJECT",request.getMainsubject());
			inParamMap.put("P_STATUS","1");
			inParamMap.put("P_OPRN",request.getOprn());
			inParamMap.put("P_REFNO",request.getRefno());
			inParamMap.put("P_FACULTY_PROFILE","");
			
			log.info(inParamMap);

			Map<String, Object> resultMap = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_FACULTY_MODULE", inParamMap);
			log.info(resultMap);
			String facultyid= (String) resultMap.get("out_genrate_id");
			savefacultyexperience(experienceitemList,facultyid);
			savefacultyeducation(educationitemList,facultyid);
			savefacultyskills(skillsitemList,facultyid);
			
			int facultyprofileid = Integer.parseInt((String) resultMap.get("out_genrate_id"));
			log.info(facultyprofileid);
			if(facultyprofileid > 0)
			{
				String oldimagefilepath = request.getFilename();		
				String newpath1 = oep_UTIL.getserverfilepath(httpRequest);
				String modulename1 = oep_UTIL.getmodulename("faculty", 1);	
				newpath1 = newpath1.concat(File.separator).concat(modulename1);
				
				log.info(newpath1);
				log.info(modulename1);
				log.info(newpath1);
								
				File uploadPath1 = new File(newpath1);
				if(!uploadPath1.exists())
				{
					uploadPath1.mkdirs();
				}
				newpath1 = newpath1.concat(File.separator).concat(facultyprofileid+".jpg");
				log.info(newpath1);
				
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
				String desriptionPath = oep_UTIL.filepath(newpath1);
				
				
				log.info("last path"+desriptionPath);
			
		
			//Map<String, Object> inParamMap2 = new HashMap<String, Object>();
			LinkedHashMap<String, Object> inParamMap2 = new LinkedHashMap<String, Object>();
			
			inParamMap2.put("P_FACULTY_ID",facultyid);
			inParamMap2.put("P_FIRSTNAME",request.getFirstname());
			inParamMap2.put("P_LASTNAME",request.getLastname());
			inParamMap2.put("P_APPLICABLE_IC",request.getApplicableic());
			inParamMap2.put("P_USERNAME",request.getUsername());
			inParamMap2.put("P_PASSWORD",oep_UTIL.encrypt("Herbzaliveerpapp", "ppapreevilazbreH", request.getPassword()));
			inParamMap2.put("P_EMAIL",request.getEmail());			
			inParamMap2.put("P_CONTACT_NO",request.getContactno());
			inParamMap2.put("P_GENDER",request.getGender());
			//inParamMap2.put("P_DOB",oep_UTIL.convertToNewSQLDate(request.getDob()));
			inParamMap2.put("P_DOB",request.getDob());
			inParamMap2.put("P_DESCRIPTION",request.getDescription());		
			inParamMap2.put("P_QUALIFICATION",request.getQualification());
			inParamMap2.put("P_OCCUPATION",request.getOccupation());			
			inParamMap2.put("P_EXPERIENCE",request.getExperience());
			inParamMap2.put("P_MAIN_SUBJECT",request.getMainsubject());
			inParamMap2.put("P_STATUS","1");		
			inParamMap2.put("P_OPRN","IMGUPD");
			inParamMap2.put("P_REFNO",request.getRefno());
			inParamMap2.put("P_FACULTY_PROFILE",desriptionPath);
			inParamMap2.put("P_MIDDDLE_NAME",request.getMiddlename());			
			
			log.info("inParamMap2         "+inParamMap2);
			Map<String, Object> resultMap2 = oep_UTIL.executeProcedure(jdbcTemplate, "P_EXAMPORTAL_FACULTY_MODULE", inParamMap2);
			}
			response.setResponseObj(resultMap);

			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	@Transactional
	public void savefacultyexperience( final List<oep_FacultyRequest> list,final String facultyid) 
	{
		jdbcTemplate.execute("delete  from faculty_master_experience_details where faculty_id ="+facultyid);
		try
		{
			   log.info("HELLO");
			   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_FACULTY_EXPERIENCE_DETAILS( ?,?,?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
				public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
					try {
					oep_FacultyRequest item = list.get(i);
					
				ps.setString(1, facultyid);
				ps.setString(2,item.getDesignation());	
				ps.setString(3,item.getCompanyname());	
				ps.setString(4,item.getJob());
				if(item.getStartdate() != null){
				ps.setDate(5,(Date) oep_UTIL.convertToNewSQLDate(item.getStartdate()));
				}else{
					ps.setString(5,"");
				}if(item.getEnddate() !=null){
					ps.setDate(6,(Date) oep_UTIL.convertToNewSQLDate(item.getEnddate()));
				}else{
					ps.setString(6,"");
				}
				
				ps.setString(7,"INS");
				 log.info(item.getStartdate());
				 log.info(item.getEnddate());
				
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
	public void savefacultyeducation( final List<oep_FacultyRequest> list,final String facultyid) 
	{
		jdbcTemplate.execute("delete from faculty_master_education_details where faculty_id ="+facultyid);
		try
		{
			   log.info("HELLO");
			   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_FACULTY_EDUCATION_DETAILS( ?,?,?,?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
				public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
					try {
				
					oep_FacultyRequest item = list.get(i);
				
				ps.setString(1, facultyid);
				ps.setString(2,item.getQualification());	
				ps.setString(3,item.getCourse());	
				ps.setString(4,item.getSpecification());
				ps.setString(5,item.getUniversity());
				if(item.getEducationstartdate() != null){
						ps.setDate(6,(Date) oep_UTIL.convertToNewSQLDate(item.getEducationstartdate()));
					
					}else{
						ps.setString(6,"");
					}if(item.getEducationtodate() !=null){
						ps.setDate(7,(Date) oep_UTIL.convertToNewSQLDate(item.getEducationtodate()));
					}else{
						ps.setString(7,"");
					}
					ps.setString(8,"INS");
					 log.info(item.getEducationstartdate());
					 log.info(item.getEducationtodate());
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
	public void savefacultyskills( final List<oep_FacultyRequest> list,final String facultyid) 
	{
		jdbcTemplate.execute("delete  from faculty_master_skills_details where faculty_id ="+facultyid);
		try
		{
			   log.info("HELLO");
			   jdbcTemplate.batchUpdate("{call P_EXAMPORTAL_FACULTY_SKILLS_DETAILS( ?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
				public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
					oep_FacultyRequest item = list.get(i);
				
				ps.setString(1, facultyid);
				ps.setString(2,item.getSkill());
				ps.setString(3,item.getEfficiency());	
				ps.setString(4,"INS");	
				
			}
		});
		}
		catch( Exception e )
		{
			
			throw e;
		}
	}
	
	@Override
	public oep_ResponseInfo getFacultydetails(String facultyid) {
		
		
		String query = "SELECT a.`username`,a.`password`,`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`contact_no`,`gender`,"
				+ "  DATE_FORMAT(a.`dob`, '%a %b %e %Y')dob,DATE_FORMAT(a.`dob`, '%d/%m/%Y')date_dob,DATE_FORMAT(a.`dob`, '%Y/%m/%d')dateofbirth,`occupation`, `description`,a.`applicable_ic`,"
				+ " `experience`,`main_subject`,DATE_FORMAT(a.`dob`,'%e/%c/%Y')testdate,  a.`created_date`,a.`status`,a.`email`,`phone_no`,`ref_no`,"
				+ " `course_name`,`faculty_profile`, d.`qualification`eduqualification,`course`,`specification`,`university`,"
				+ " DATE_FORMAT(d.`start_date`, '%d-%m-%Y')edustartdate, DATE_FORMAT(d.`end_date`, '%d-%m-%Y')eduenddate,`designation`,"
				+ " `company_name`,`job_desc`, DATE_FORMAT(e.`start_date`, '%d-%m-%Y')expstartdate, DATE_FORMAT(e.`end_date`, '%d-%m-%Y')expenddate,"
				+ " `skill`,`efficiency` FROM `faculty_master` a  JOIN `users` b ON a.userid = b.`userid`"
				+ " LEFT JOIN `course_master`c ON c.`course_id` = a.`main_subject` LEFT  JOIN `faculty_master_education_details`d ON d.`faculty_id` = a.`userid` "
				+ " LEFT JOIN `faculty_master_experience_details`e ON e.`faculty_id` = a.`userid` "
				+ " LEFT JOIN `faculty_master_skills_details`f ON f.`faculty_id`= a.`userid` WHERE b.`userid` =  "+facultyid+"  ";
		
	/*	String query = " SELECT a.`username`,a.`password`,`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`contact_no`,`gender`,"
				+ " DATE_FORMAT(a.`dob`, '%a %b %e %Y')dob,DATE_FORMAT(a.`dob`, '%d-%m-%Y')date_dob,`qualification`,`occupation`,`description`,a.`applicable_ic`,`experience`,`main_subject`,"
				+ "DATE_FORMAT(a.`dob`,'%e/%c/%Y')testdate, a.`created_date`,a.`status`,a.`email`,`phone_no`,`ref_no`,`course_name`,`faculty_profile` FROM `faculty_master` a "
				+ " JOIN `users` b ON a.userid = b.`userid` LEFT  JOIN `course_master`c ON c.`course_id` = a.`main_subject` WHERE b.`userid` =  "+facultyid+"  ";*/
		log.info(query);
		
///*		String query = "SELECT a.`username`,a.`password`,`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`contact_no`,`gender`,"
//				+ "  DATE_FORMAT(a.`dob`, '%a %b %e %Y')dob,DATE_FORMAT(a.`dob`, '%d-%m-%Y')date_dob,`occupation`, `description`,a.`applicable_ic`,"
//				+ " `experience`,`main_subject`,DATE_FORMAT(a.`dob`,'%e/%c/%Y')testdate,  a.`created_date`,a.`status`,a.`email`,`phone_no`,`ref_no`,"
//				+ " `course_name`,`faculty_profile`, d.`qualification`eduqualification,`course`,`specification`,`university`,"
//				+ " DATE_FORMAT(d.`start_date`, '%d-%m-%Y')edustartdate, DATE_FORMAT(d.`end_date`, '%d-%m-%Y')eduenddate,`designation`,"
//				+ " `company_name`,`job_desc`, DATE_FORMAT(e.`start_date`, '%d-%m-%Y')expstartdate, DATE_FORMAT(e.`end_date`, '%d-%m-%Y')expenddate,"
//				+ " `skill`,`efficiency` FROM `faculty_master` a  JOIN `users` b ON a.userid = b.`userid`"
//				+ " LEFT JOIN `course_master`c ON c.`course_id` = a.`main_subject`  LEFT JOIN `faculty_master_education_details`d ON d.`faculty_id` = a.`userid` "
//				+ " LEFT JOIN `faculty_master_experience_details`e ON e.`faculty_id` = a.`userid` "
//				+ " LEFT JOIN `faculty_master_skills_details`f ON f.`faculty_id`= a.`userid` WHERE b.`userid` =  "+facultyid+"  ";*/
//		
//	String query = " SELECT a.`username`,a.`password`,`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`contact_no`,`gender`,"
//				+ " DATE_FORMAT(a.`dob`, '%a %b %e %Y')dob,DATE_FORMAT(a.`dob`, '%d-%m-%Y')date_dob,`qualification`,`occupation`,`description`,a.`applicable_ic`,`experience`,`main_subject`,"
//				+ "DATE_FORMAT(a.`dob`,'%e/%c/%Y')testdate, a.`created_date`,a.`status`,a.`email`,`phone_no`,`ref_no`,`course_name`,`faculty_profile` FROM `faculty_master` a "
//				+ " JOIN `users` b ON a.userid = b.`userid` LEFT  JOIN `course_master`c ON c.`course_id` = a.`main_subject` WHERE b.`userid` =  "+facultyid+"  ";
//		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> facultydetailsList = jdbcTemplate.query(query, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("subjectname", rs.getString("sub_name"));
				map.put("coursename", rs.getString("course_name"));				
				map.put("username", rs.getString("username"));				
				map.put("middlename", rs.getString("faculty_middlename"));
				map.put("description", rs.getString("description"));
				map.put("imagepath", rs.getString("faculty_profile"));
				map.put("applicableic", rs.getString("applicable_ic"));				
				//map.put("password", oep_UTIL.decrypt("Herbzaliveerpapp","ppapreevilazbreH",rs.getString("password")));
				map.put("password", rs.getString("password"));
				map.put("firstname", rs.getString("faculty_firstname"));
				map.put("lastname", rs.getString("faculty_lastname"));
				map.put("contactnumber", rs.getString("contact_no"));
				map.put("gender", rs.getString("gender"));
				map.put("dob", rs.getString("dob"));
				map.put("datedob", rs.getString("date_dob"));
				map.put("testdate", rs.getString("testdate"));
				map.put("main_subject", rs.getString("main_subject"));
				map.put("employeeid", rs.getString("ref_no"));
				map.put("email", rs.getString("email"));
				map.put("profession", rs.getString("occupation"));
				map.put("experience", rs.getString("experience"));
				map.put("dateofbirth", rs.getString("dateofbirth"));
				/*map.put("profession", rs.getString("occupation"));
				map.put("experience", rs.getString("experience"));
				map.put("main_subject", rs.getString("main_subject"));
				map.put("createddate", rs.getString("created_date"));
				map.put("employeeid", rs.getString("ref_no"));
				map.put("datedob", rs.getString("date_dob"));
				map.put("email", rs.getString("email"));
				map.put("efficiency", rs.getString("efficiency"));
				map.put("educationstartdate", rs.getString("edustartdate"));
				map.put("educationtodate", rs.getString("eduenddate"));
				map.put("startdate", rs.getString("expstartdate"));
				map.put("enddate", rs.getString("expenddate"));
				map.put("course", rs.getString("course"));
				map.put("specification", rs.getString("specification"));
				map.put("university", rs.getString("university"));
				map.put("designation", rs.getString("designation"));
				map.put("job", rs.getString("job_desc"));
				map.put("companyname", rs.getString("company_name"));
				map.put("skill", rs.getString("skill"));*/
				
				if(rs.getInt("status") == 0)
				{
					map.put("statusdisp", "InActive");
				}
				else 
				{
					map.put("statusdisp", "Active");
				}
				
				return map;
				
			}
		});
		String skillquery="SELECT * FROM `faculty_master_skills_details` a JOIN `faculty_master` b ON a.`faculty_id`=b.`userid` "
                           + " JOIN `users` c ON b.userid = c.`userid` WHERE c.`userid` ="+facultyid;
		
		log.info(skillquery);
		@SuppressWarnings("unchecked")
		List<Object> skilladetailsList = jdbcTemplate.query(skillquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skill", rs.getString("skill"));
				map.put("efficiency", rs.getString("efficiency"));
				
				return map;
				
			}
		});
		
		String experiencequery="SELECT designation,company_name,job_desc,"
				+ "DATE_FORMAT(start_date, '%d/%m/%Y')start_date,DATE_FORMAT(start_date, '%Y/%m/%d')field_start_date,DATE_FORMAT(end_date, '%d/%m/%Y')end_date,"
				+ " DATE_FORMAT(end_date, '%Y/%m/%d')filed_end_date FROM `faculty_master_experience_details` a JOIN `faculty_master` b ON a.`faculty_id`=b.`userid`"
				+ " JOIN `users` c ON b.userid = c.`userid` WHERE c.`userid` = "+facultyid;
		log.info(experiencequery);
		@SuppressWarnings("unchecked")
		List<Object> experiencedetailsList = jdbcTemplate.query(experiencequery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("designation", rs.getString("designation"));				
				map.put("companyname", rs.getString("company_name"));
				map.put("job", rs.getString("job_desc"));
				map.put("startdate", rs.getString("start_date"));
				map.put("enddate", rs.getString("end_date"));
				map.put("fieldstartdate", rs.getString("field_start_date"));
				map.put("fieldenddate", rs.getString("filed_end_date"));
				return map;
				
			}
		});
		String educationquery="SELECT a.qualification,course,specification,university,"
				+ "DATE_FORMAT(start_date,'%d/%m/%Y')start_date,DATE_FORMAT(start_date,'%Y/%m/%d')field_start_date, DATE_FORMAT(end_date,'%d/%m/%Y')end_date, DATE_FORMAT(end_date,'%Y/%m/%d')field_end_date  FROM `faculty_master_education_details` a "
				+ "JOIN `faculty_master` b ON a.`faculty_id`=b.`userid`"
				+ " JOIN `users` c ON b.userid = c.`userid` WHERE c.`userid` = "+facultyid;
		log.info(educationquery);
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
				map.put("educationtodate", rs.getString("end_date"));
				map.put("educationfieldstartdate", rs.getString("field_start_date"));
				map.put("educationfieldenddate", rs.getString("field_end_date"));
				return map;
				
			}
		});
		
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		detailsmap.put("facultydetailsList", facultydetailsList.get(0));
		detailsmap.put("skilladetailsList", skilladetailsList);
		detailsmap.put("experiencedetailsList", experiencedetailsList);
		detailsmap.put("educationdetailsList", educationdetailsList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	@Override
	public oep_ResponseInfo getFacultyList(String courseid) {
		
		String query = "SELECT `faculty_id`,`username` FROM `faculty_master`a JOIN `course_master`b ON b.`course_id` = a.`main_subject`"
				+ "  WHERE b.`course_id`= "+courseid+" ";
		
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> facultydetailsList = jdbcTemplate.query(query, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", rs.getString("username"));
				map.put("facultyid", rs.getString("faculty_id"));
				
				
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(facultydetailsList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	
	
	
	@Override
	public oep_ResponseInfo getFacultyList() {
		
		String query = "SELECT `username`,`faculty_id`,`password`,`userid`,`faculty_firstname`,`faculty_lastname`,`contact_no`,`gender`,`dob`,"
				+ "`qualification`,`occupation`,`experience`,`main_subject`,`created_date`,`status`,`ref_no`,`faculty_profile`"
				+ " FROM `faculty_master`  ";
		
		log.info(query);
		
		@SuppressWarnings("unchecked")
		List<Object> facultyList = jdbcTemplate.query(query, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("facultyid", rs.getString("faculty_id"));
				map.put("userid", rs.getString("userid"));
				map.put("facultyname", rs.getString("username"));
				map.put("password", rs.getString("password"));
				map.put("firstname", rs.getString("faculty_firstname"));
				map.put("lastname", rs.getString("faculty_lastname"));
				map.put("contactno", rs.getString("contact_no"));
				map.put("gender", rs.getString("gender"));
				map.put("dob", rs.getString("dob"));
				map.put("qualification", rs.getString("qualification"));
				map.put("occupation", rs.getString("occupation"));
				map.put("experience", rs.getString("experience"));
				map.put("main_subject", rs.getString("main_subject"));
				map.put("createddate", rs.getString("created_date"));
				map.put("ref_no", rs.getString("ref_no"));
				map.put("image_url", rs.getString("faculty_profile"));
								
				/*map.put("productidhref", "<a href='productview?prodid="+rs.getString("product_id")+"' "
						+ "onclick='gotoproductview('"+rs.getString("product_id")+"')'>"+rs.getString("product_name")+"</a>");
				*/
				if(rs.getInt("status") == 0)
				{
					map.put("statusdisp", "InActive");
				}
				else 
				{
					map.put("statusdisp", "Active");
				}
				
				return map;
				
			}
		});
		response.setResponseType("S");
		response.setResponseObj(facultyList);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}
	@Override
	public oep_ResponseInfo getFacultyprofiledetails(String facultyprofileid) {
		// TODO Auto-generated method stub
		String facultyquery = "SELECT * FROM `faculty_master` WHERE `faculty_id`="+facultyprofileid;
		@SuppressWarnings("unchecked")
		List<Object> facultyList = jdbcTemplate.query(facultyquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("facultyid", rs.getString("faculty_id"));
				map.put("firstname", rs.getString("faculty_firstname"));
				map.put("lastname", rs.getString("faculty_lastname"));
				map.put("profession", rs.getString("occupation"));
				map.put("employeeid", rs.getString("ref_no"));
				map.put("dob", rs.getString("dob"));
				map.put("email", rs.getString("email"));
				map.put("contactno", rs.getString("contact_no"));
				map.put("image", rs.getString("faculty_profile"));
				
				return map;
				
			}
		});
		
		String experiencequery="SELECT CONCAT(`designation`,' at ',`company_name`)designation,CONCAT(`start_date`,' - ',COALESCE(`end_date`,'Now'))period"
				+ " FROM `faculty_master_experience_details` WHERE `faculty_id`="+facultyprofileid;
		@SuppressWarnings("unchecked")
		List<Object> experienceList = jdbcTemplate.query(experiencequery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("designation", rs.getString("designation"));
				map.put("period", rs.getString("period"));
				return map;
				
			}
		});
		
		
		String eductaionquery="SELECT CONCAT(CONCAT(`qualification`,'-',`course`),' at ',`university`)course,"
				+ "CONCAT(`start_date`,' - ',COALESCE(`end_date`,'Now'))period FROM `faculty_master_education_details` WHERE `faculty_id`="+facultyprofileid;
		@SuppressWarnings("unchecked")
		List<Object> educationList = jdbcTemplate.query(eductaionquery, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				int count = 0;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("course", rs.getString("course"));
				map.put("period", rs.getString("period"));
				return map;
				
			}
		});
		
		Map<String, Object> detailsmap = new HashMap<String, Object>();
		detailsmap.put("facultylist", facultyList);
		detailsmap.put("experienceList", experienceList);
		detailsmap.put("educationList", educationList);
		response.setResponseType("S");
		response.setResponseObj(detailsmap);
		responseInfo.setInventoryResponse(response);
		return responseInfo;
	}


}
