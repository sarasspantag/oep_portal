package com.oep.services.forms.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.oep.services.admin.response.oep_ResponseInfo;

public interface oep_ILoginDAO {
	public oep_ResponseInfo validLogin(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo saveuser(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo savemailtemplate(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo signout(String tokenkey)throws ClassNotFoundException;
	public oep_ResponseInfo getMenuList(String userid);
	public oep_ResponseInfo getuserdetails(String userid);
	public oep_ResponseInfo gettemplatetails(String id);
	public oep_ResponseInfo getloadscheduleevents(int roleid,int userid);
	public oep_ResponseInfo getdashboarddetails();
	public oep_ResponseInfo getroleList();
	public oep_ResponseInfo gettemplateList();
	public oep_ResponseInfo checkcourseregisterparticipants(String scheduleid,String userid);
	public oep_ResponseInfo getuserList();
	public oep_ResponseInfo getdecrypturl(String formData);
	public oep_ResponseInfo registerforgotpassword(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo changepassword(String formData,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo savefile(String formData,String id,String type,String form,
			String sessionname,String foldername,CommonsMultipartFile file,HttpServletRequest httpRequest) throws ClassNotFoundException;
	public oep_ResponseInfo multipleSave(String formData,String id,String type,String form,
			String sessionname,String foldername,CommonsMultipartFile file,HttpServletRequest httpRequest) throws ClassNotFoundException;
}
