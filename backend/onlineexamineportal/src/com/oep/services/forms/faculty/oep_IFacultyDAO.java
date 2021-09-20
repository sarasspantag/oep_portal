package com.oep.services.forms.faculty;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;

public interface oep_IFacultyDAO {

	
		public oep_ResponseInfo savefacultydetails(String uploadData,HttpServletRequest httpRequest);
		public oep_ResponseInfo getFacultydetails(String facultyid);
		public oep_ResponseInfo getFacultyList(String facultyid);
		public oep_ResponseInfo getFacultyprofiledetails(String facultyprofileid);
		public oep_ResponseInfo getFacultyList();
	}

