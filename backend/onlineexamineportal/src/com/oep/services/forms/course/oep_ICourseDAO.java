package com.oep.services.forms.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oep.services.admin.response.oep_ResponseInfo;

public interface oep_ICourseDAO {
	
	public  oep_ResponseInfo savecoursemastermgmt(String uploadData,HttpServletRequest httpRequest);
	//public  oep_ResponseInfo saveexammaster(String uploadData,HttpServletRequest httpRequest);
	//public  oep_ResponseInfo savecourseyear(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savecourseschedule(String uploadData,HttpServletRequest httpRequest);
	//public  oep_ResponseInfo savesubject(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savequestion(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo updatequestiondetails(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savetestschedule(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savetestadministrator(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo registerparticipantcourse(String registerData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo saveparticpantsimport(String registerData,HttpServletRequest httpRequest);
	//public void downloadSampleFormat(String fileName,HttpServletRequest httpRequest,HttpServletResponse response);
	
	
	public oep_ResponseInfo getCoursedetails(String courseid);
	public oep_ResponseInfo getcourseparticipantdetails(String userid);
	public oep_ResponseInfo getFacultyTestscheduleList(String subjectid);
	public oep_ResponseInfo getQuestiondetails(String questionid);
	public oep_ResponseInfo getQuestionbankdetails(String questionid);
	public oep_ResponseInfo getScheduledetails(String scheduleid);
	public oep_ResponseInfo getTestscheduledetails(String id);	
	public oep_ResponseInfo getCourseList();	
	public oep_ResponseInfo getTrainingcourseList();
	public oep_ResponseInfo getDepartmentList();
	public oep_ResponseInfo getallCourseList();
	public oep_ResponseInfo getScheduleList();
	public oep_ResponseInfo getTestscheduleList();
	public oep_ResponseInfo getallTestscheduleList();
	public oep_ResponseInfo getQuestionBankList();
//	public oep_ResponseInfo getSubjectList();
	public oep_ResponseInfo getfacultylistforreport();
	public oep_ResponseInfo getfacultyidforreport(String userid,String roleid);
	public oep_ResponseInfo getcoursedetailsforreport(String facultyid,String roleid);
	public oep_ResponseInfo gettestdetails(String facultyid,String roleid);
	public oep_ResponseInfo generatebatchreport(String courseid,String roleid, String userid, String testid);
	public oep_ResponseInfo generatefacultyreport(String courseid,String roleid, String userid, String testid);
//	public oep_ResponseInfo getcheckedSubjectList();
	public oep_ResponseInfo getIdformat(String id, String type);
	public oep_ResponseInfo getTestadministratorList();
	public oep_ResponseInfo getTestadministratordetails(String id);
	
	public oep_ResponseInfo getSchListforTestSchedule(String roleid, String userid, String qnid);
	public oep_ResponseInfo getQBMListforSchedule(String userid, String roleid);


}
