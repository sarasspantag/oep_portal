package com.oep.services.forms.participant;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.forms.faculty.oep_IFacultyDAO;

public class oep_ParticipantHelper {

	public oep_ResponseInfo saveparticipantdetails(String uploadData,oep_IParticipantDAO participantDAO, HttpServletRequest httpRequest) 
	{
		return participantDAO.saveparticipantdetails(uploadData,httpRequest);
    }
	
	public oep_ResponseInfo sendmailtoparticipant(String uploadData,oep_IParticipantDAO participantDAO, HttpServletRequest httpRequest) 
	{
		return participantDAO.sendmailtoparticipant(uploadData,httpRequest);
    }
	
	
	public oep_ResponseInfo loadquestions(oep_IParticipantDAO participantDAO,String questionid,String userid)
	{
		return participantDAO.loadquestions(questionid,userid);
	}
	
	public oep_ResponseInfo savetestschedulequestion(oep_IParticipantDAO participantDAO,String uploadData,HttpServletRequest httpRequest)
	{
		return participantDAO.savetestschedulequestion(uploadData,httpRequest);
	}
	
	public oep_ResponseInfo savetestquestiontime(oep_IParticipantDAO participantDAO,String uploadData,HttpServletRequest httpRequest)
	{
		return participantDAO.savetestquestiontime(uploadData,httpRequest);
	}
	
	public oep_ResponseInfo getParticipantdetails(oep_IParticipantDAO participantDAO,String userid)
	{
		return participantDAO.getParticipantdetails(userid);
	}
	
	public oep_ResponseInfo getParticipantdetails(oep_IParticipantDAO participantDAO,String userid,String courseid)
	{
		return participantDAO.getParticipantdetails(userid,courseid);
	}
	
	public oep_ResponseInfo getParticipantTestList(oep_IParticipantDAO participantDAO,String userid)
	{
		return participantDAO.getParticipantTestList(userid);
	}
	
	public oep_ResponseInfo getParticipantList(oep_IParticipantDAO participantDAO)
	{
		return participantDAO.getParticipantList();
	}
	
	public oep_ResponseInfo getParticipantList(oep_IParticipantDAO participantDAO, String roleid, String userid)
	{
		return participantDAO.getParticipantList(roleid,userid);
	}
	
	public oep_ResponseInfo getParticipants(oep_IParticipantDAO participantDAO,String userid)
	{
		return participantDAO.getParticipants(userid);
	}
	
	public oep_ResponseInfo participantcertificatedetails(String uploadData,oep_IParticipantDAO participantDAO, HttpServletRequest httpRequest) 
	{
		return participantDAO.participantcertificatedetails(uploadData,httpRequest);
    }
	
	public oep_ResponseInfo getsinglecertificate(String uploadData,oep_IParticipantDAO participantDAO, HttpServletRequest httpRequest) 
	{
		return participantDAO.getsinglecertificate(uploadData,httpRequest);
    }
	
	public oep_ResponseInfo getParticipantList(oep_IParticipantDAO participantDAO,String userid)
	{
		return participantDAO.getParticipantList(userid);
	}
	
	public oep_ResponseInfo getBatchList(oep_IParticipantDAO participantDAO )
	{
		return participantDAO.getBatchList();
	}
	
	public oep_ResponseInfo getBatchList(oep_IParticipantDAO participantDAO,String userid)
	{
		return participantDAO.getBatchList(userid);
	}
	
	public oep_ResponseInfo getBatchdetails(oep_IParticipantDAO participantDAO,String userid,String partid)
	{
		return participantDAO.getBatchdetails(userid,partid);
	}
	
	public oep_ResponseInfo getBatchdetails(oep_IParticipantDAO participantDAO,String userid,String partid,String tsid)
	{
		return participantDAO.getBatchdetails(userid,partid,tsid);
	}
	
	public oep_ResponseInfo getTestscheduleList(oep_IParticipantDAO participantDAO,String batch)
	{
		return participantDAO.getTestscheduleList(batch);
	}
}
