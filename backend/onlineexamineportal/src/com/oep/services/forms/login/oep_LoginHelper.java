package com.oep.services.forms.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.forms.participant.oep_IParticipantDAO;



public class oep_LoginHelper {

	public oep_ResponseInfo validLogin(oep_ILoginDAO loginDAOOBJ,String formData,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.validLogin(formData,httpRequest);
	}
	
	public oep_ResponseInfo saveuser(oep_ILoginDAO loginDAOOBJ,String formData,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.saveuser(formData,httpRequest);
	}
	
	public oep_ResponseInfo savemailtemplate(oep_ILoginDAO loginDAOOBJ,String formData,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.savemailtemplate(formData,httpRequest);
	}
	
	
	
	public oep_ResponseInfo signout(String tokenkey, oep_ILoginDAO loginDAOOBJ) throws ClassNotFoundException
	{
		return loginDAOOBJ.signout(tokenkey);
	}
	
	public oep_ResponseInfo getMenuList(oep_ILoginDAO loginDAO,String userid)
	{
		return loginDAO.getMenuList(userid);
	}
	
	public oep_ResponseInfo getuserdetails(oep_ILoginDAO loginDAO,String userid)
	{
		return loginDAO.getuserdetails(userid);
	}
	
	
	public oep_ResponseInfo getloadscheduleevents(int roleid,int userid,oep_ILoginDAO loginDAO)
	{
		return loginDAO.getloadscheduleevents(roleid,userid);
	}

	
	public oep_ResponseInfo checkcourseregisterparticipants(String scheduleid,String userid, oep_ILoginDAO loginDAO)
	{
		return loginDAO.checkcourseregisterparticipants(scheduleid,userid);
	}
	
	public oep_ResponseInfo getdashboarddetails(oep_ILoginDAO loginDAO)
	{
		return loginDAO.getdashboarddetails();
	}

	
	public oep_ResponseInfo getroleList(oep_ILoginDAO loginDAO)
	{
		return loginDAO.getroleList();
	}
	
	public oep_ResponseInfo gettemplatetails(oep_ILoginDAO loginDAO,String id)
	{
		return loginDAO.gettemplatetails(id);
	}
	
	
	
	public oep_ResponseInfo gettemplateList(oep_ILoginDAO loginDAO)
	{
		return loginDAO.gettemplateList();
	}
	
	public oep_ResponseInfo getuserList(oep_ILoginDAO loginDAO)
	{
		return loginDAO.getuserList();
	}
	
	public oep_ResponseInfo registerforgotpassword(String formData,oep_ILoginDAO loginDAOOBJ,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.registerforgotpassword(formData,httpRequest);
	}
	
	public oep_ResponseInfo getdecrypturl(String formData,oep_ILoginDAO loginDAOOBJ) 
	{
		return loginDAOOBJ.getdecrypturl(formData);
	}
	
	public oep_ResponseInfo changepassword(String formData,oep_ILoginDAO loginDAOOBJ,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.changepassword(formData,httpRequest);
	}
	
	public oep_ResponseInfo savefile(String formData,String id,String type, String form,String sessionname,String foldername,
			CommonsMultipartFile file,oep_ILoginDAO loginDAOOBJ,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.savefile(formData,id,type,form,sessionname,foldername,file,httpRequest);
	}
	
	public oep_ResponseInfo multipleSave(String formData,String id,String type, String form,String sessionname,String foldername,
			CommonsMultipartFile file,oep_ILoginDAO loginDAOOBJ,HttpServletRequest httpRequest) throws ClassNotFoundException
	{
		return loginDAOOBJ.multipleSave(formData,id,type,form,sessionname,foldername,file,httpRequest);
	}
	
}
