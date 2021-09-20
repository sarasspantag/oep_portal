package com.oep.services.forms.participant;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;

public interface oep_IParticipantDAO {
	public oep_ResponseInfo loadquestions(String questionid,String userid);
	public  oep_ResponseInfo saveparticipantdetails(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo sendmailtoparticipant(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savetestschedulequestion(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo savetestquestiontime(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo getParticipantdetails(String userid);
	public oep_ResponseInfo getParticipantdetails(String userid,String courseid);
	public oep_ResponseInfo getParticipantTestList(String userid);
	public oep_ResponseInfo getParticipantList();
	public oep_ResponseInfo getParticipantList(String roleid, String userid);
	public oep_ResponseInfo getParticipants(String userid);
	public  oep_ResponseInfo participantcertificatedetails(String uploadData,HttpServletRequest httpRequest);
	public  oep_ResponseInfo getsinglecertificate(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo getParticipantList(String userid);
	public oep_ResponseInfo getBatchList();
	public oep_ResponseInfo getBatchList(String userid);
	public oep_ResponseInfo getBatchdetails(String batch,String partid);
	public oep_ResponseInfo getBatchdetails(String batch,String partid,String tsid);
	public oep_ResponseInfo getTestscheduleList(String batch);
}
