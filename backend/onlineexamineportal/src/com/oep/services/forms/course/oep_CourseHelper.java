package com.oep.services.forms.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oep.services.admin.response.oep_ResponseInfo;

public class oep_CourseHelper {
	
	
	public oep_ResponseInfo savecoursemastermgmt(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savecoursemastermgmt(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savecourseschedule(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savecourseschedule(uploadData,httpRequest);
     }
	
	/*public oep_ResponseInfo saveexammaster(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.saveexammaster(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savecourseyear(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savecourseyear(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savesubject(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savesubject(uploadData,httpRequest);
     }*/

	public oep_ResponseInfo savequestion(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savequestion(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo updatequestiondetails(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.updatequestiondetails(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savetestschedule(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savetestschedule(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savetestadministrator(String uploadData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.savetestadministrator(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo registerparticipantcourse(String registerData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.registerparticipantcourse(registerData,httpRequest);
     }
	
	public oep_ResponseInfo saveparticpantsimport(String registerData,oep_ICourseDAO courseDAO, HttpServletRequest httpRequest) {
		return courseDAO.saveparticpantsimport(registerData,httpRequest);
     }
	
	
	/*public void downloadSampleFormat(String fileName,oep_ICourseDAO courseDAO,HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
		courseDAO.downloadSampleFormat(fileName,httpRequest,httpResponse);
	}
	*/
	
	public oep_ResponseInfo getCoursedetails(oep_ICourseDAO courseDAO,String courseid)
	{
		return courseDAO.getCoursedetails(courseid);
	}
	
	public oep_ResponseInfo getcourseparticipantdetails(oep_ICourseDAO courseDAO,String userid)
	{
		return courseDAO.getcourseparticipantdetails(userid);
	}
	
	public oep_ResponseInfo getCourseList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getCourseList();
	}
	
	public oep_ResponseInfo getTrainingcourseList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getTrainingcourseList();
	}
	
	public oep_ResponseInfo getDepartmentList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getDepartmentList();
	}

	public oep_ResponseInfo getallCourseList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getallCourseList();
	}
	
	/*public oep_ResponseInfo getSubjectList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getSubjectList();
	}
	
	public oep_ResponseInfo getcheckedSubjectList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getcheckedSubjectList();
	}*/
	
	public oep_ResponseInfo getFacultyTestscheduleList(oep_ICourseDAO courseDAO,String subjectid)
	{
		return courseDAO.getFacultyTestscheduleList(subjectid);
	}
	
	public oep_ResponseInfo getQuestiondetails(oep_ICourseDAO courseDAO,String questionid)
	{
		return courseDAO.getQuestiondetails(questionid);
	}
	
	public oep_ResponseInfo getQuestionbankdetails(oep_ICourseDAO courseDAO,String questionid)
	{
		return courseDAO.getQuestionbankdetails(questionid);
	}
	
	public oep_ResponseInfo getScheduledetails(oep_ICourseDAO courseDAO,String scheduleid)
	{
		return courseDAO.getScheduledetails(scheduleid);
	}
	
	
	
	public oep_ResponseInfo getScheduleList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getScheduleList();
	}
	
	public oep_ResponseInfo getTestscheduledetails(oep_ICourseDAO courseDAO,String id)
	{
		return courseDAO.getTestscheduledetails(id);
	}
	
	public oep_ResponseInfo getTestscheduleList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getTestscheduleList();
	}
	
	public oep_ResponseInfo getallTestscheduleList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getallTestscheduleList();
	}
	
	public oep_ResponseInfo getTestadministratordetails(oep_ICourseDAO courseDAO,String id)
	{
		return courseDAO.getTestadministratordetails(id);
	}
	
	public oep_ResponseInfo getTestadministratorList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getTestadministratorList();
	}
	
	public oep_ResponseInfo getQuestionBankList(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getQuestionBankList();
	}
	
	public oep_ResponseInfo getIdformat(oep_ICourseDAO courseDAO,String id,String type)
	{
		return courseDAO.getIdformat(id,type);
	}
	
	public oep_ResponseInfo getfacultylistforreport(oep_ICourseDAO courseDAO)
	{
		return courseDAO.getfacultylistforreport();
	}
	
	public oep_ResponseInfo getfacultyidforreport(oep_ICourseDAO courseDAO,String userid,String roleid)
	{
		return courseDAO.getfacultyidforreport(userid,roleid);
	}
	
	
	public oep_ResponseInfo getgetcoursedetailsforreport(oep_ICourseDAO courseDAO,String facultyid,String roleid)
	{
		return courseDAO.getcoursedetailsforreport(facultyid,roleid);
	}
	
	public oep_ResponseInfo gettestdetails(oep_ICourseDAO courseDAO,String courseid,String roleid)
	{
		return courseDAO.gettestdetails(courseid,roleid);
	}
	
	public oep_ResponseInfo generatebatchreport(oep_ICourseDAO courseDAO,String courseid,String roleid,String userid, String testid)
	{
		return courseDAO.generatebatchreport(courseid,roleid,userid,testid);
	}
	
	public oep_ResponseInfo generatefacultyreport(oep_ICourseDAO courseDAO,String courseid,String roleid,String userid, String testid)
	{
		return courseDAO.generatefacultyreport(courseid,roleid,userid,testid);
	}
	
	public oep_ResponseInfo getSchListforTestSchedule(oep_ICourseDAO courseDAO, String roleid, String userid,String qnid)
	{
		return courseDAO.getSchListforTestSchedule(roleid,userid,qnid);
	}
	public oep_ResponseInfo getQBMListforSchedule(oep_ICourseDAO courseDAO,String userid, String roleid)
	{
		return courseDAO.getQBMListforSchedule(userid, roleid);
	}
	
}
