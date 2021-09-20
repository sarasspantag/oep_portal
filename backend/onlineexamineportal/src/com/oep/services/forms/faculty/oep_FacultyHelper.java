package com.oep.services.forms.faculty;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.forms.course.oep_ICourseDAO;
import com.oep.services.forms.faculty.oep_IFacultyDAO;
import com.oep.services.forms.participant.oep_IParticipantDAO;


public class oep_FacultyHelper {

	
	
	public oep_ResponseInfo savefacultydetails(String uploadData,oep_IFacultyDAO facultyDAO, HttpServletRequest httpRequest) 
	{
		return facultyDAO.savefacultydetails(uploadData,httpRequest);
    }
	
	public oep_ResponseInfo getFacultydetails(oep_IFacultyDAO facultyDAO,String facultyid)
	{
		return facultyDAO.getFacultydetails(facultyid);
	}
	
	public oep_ResponseInfo getFacultyList(oep_IFacultyDAO facultyDAO,String facultyid)
	{
		return facultyDAO.getFacultyList(facultyid);
	}
	
	public oep_ResponseInfo getFacultyprofiledetails(oep_IFacultyDAO facultyDAO,String facultyprofileid)
	{
		return facultyDAO.getFacultyprofiledetails(facultyprofileid);
	}
	
	public oep_ResponseInfo getFacultyList(oep_IFacultyDAO facultyDAO)
	{
		return facultyDAO.getFacultyList();
	}
}

