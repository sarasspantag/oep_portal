package com.oep.controller.view.services;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oep.services.admin.util.oep_IDAO;
import com.oep.services.forms.faculty.oep_FacultyHelper;
import com.oep.services.forms.faculty.oep_IFacultyDAO;
import com.oep.services.forms.participant.oep_IParticipantDAO;
import com.oep.services.forms.participant.oep_ParticipantHelper;


@Controller
@SessionAttributes( {"uid"})
public class ParticipantServiceCtrl extends oep_IDAO {

		private static Logger log = Logger.getLogger(ParticipantServiceCtrl.class);
		
		@Autowired
		private oep_ParticipantHelper participantHelperOBJ;
		@Autowired
		private oep_IParticipantDAO participantDAOOBJ;

		@RequestMapping(value = "/saveparticipantdetails", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> saveparticipantdetails(@RequestBody String uploadData,HttpServletRequest httpRequest) 
		{
			 inventoryResponseInfoOBJ = participantHelperOBJ.saveparticipantdetails(uploadData, participantDAOOBJ,httpRequest);
			 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
	     }
		
		@RequestMapping(value = "/sendmailtoparticipant", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> sendmailtoparticipant(@RequestBody String uploadData,HttpServletRequest httpRequest) 
		{
			 inventoryResponseInfoOBJ = participantHelperOBJ.sendmailtoparticipant(uploadData, participantDAOOBJ,httpRequest);
			 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
	     }
		
		@RequestMapping(value="/getParticipantdetails/{userid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipantdetails(@PathVariable("userid")String userid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipantdetails(participantDAOOBJ,userid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getParticipantdetails/{userid}/{courseid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipantdetails(@PathVariable("userid")String userid,@PathVariable("courseid")String courseid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipantdetails(participantDAOOBJ,userid,courseid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
	
		
		@RequestMapping(value="/getParticipantTestList/{userid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipantTestList(@PathVariable("userid")String userid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipantTestList(participantDAOOBJ,userid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/loadquestions/{questionid}/{userid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> loadquestions(@PathVariable("questionid")String questionid,@PathVariable("userid")String userid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.loadquestions(participantDAOOBJ,questionid,userid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/savetestschedulequestion",method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> savetestschedulequestion(@RequestBody String uploadData,HttpServletRequest httpRequest)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.savetestschedulequestion(participantDAOOBJ,uploadData,httpRequest);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/savetestquestiontime",method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> savetestquestiontime(@RequestBody String uploadData,HttpServletRequest httpRequest)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.savetestquestiontime(participantDAOOBJ,uploadData,httpRequest);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getParticipantList/{roleid}/{userid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipantList(@PathVariable("roleid")String roleid, @PathVariable("userid")String userid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipantList(participantDAOOBJ,roleid,userid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getParticipants/{userid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipants(@PathVariable("userid")String userid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipants(participantDAOOBJ,userid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}

		@RequestMapping(value = "/participantcertificatedetails", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> participantcertificatedetails(@RequestBody String uploadData,HttpServletRequest httpRequest) 
		{
			 inventoryResponseInfoOBJ = participantHelperOBJ.participantcertificatedetails(uploadData, participantDAOOBJ,httpRequest);
			 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
	     }
		
		@RequestMapping(value = "/reports/getsinglecertificate", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> getsinglecertificate_reports(@RequestBody String uploadData,HttpServletRequest httpRequest) 
		{
			 inventoryResponseInfoOBJ = participantHelperOBJ.getsinglecertificate(uploadData, participantDAOOBJ,httpRequest);
			 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
	     }
		

		@RequestMapping(value = "/getsinglecertificate", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Object> getsinglecertificate(@RequestBody String uploadData,HttpServletRequest httpRequest) 
		{
			 inventoryResponseInfoOBJ = participantHelperOBJ.getsinglecertificate(uploadData, participantDAOOBJ,httpRequest);
			 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
	     }
		
		@RequestMapping(value="/getBatchList/{partid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getBatchList(@PathVariable("partid")String partid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getBatchList(participantDAOOBJ,partid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getParticipantList/{partid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getParticipantList(@PathVariable("partid")String partid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getParticipantList(participantDAOOBJ,partid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/getBatchList",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getBatchList()
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getBatchList(participantDAOOBJ);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/getBatchdetails/{batchid}/{partid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getBatchdetails(@PathVariable("batchid")String batchid,@PathVariable("partid")String partid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getBatchdetails(participantDAOOBJ,batchid,partid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/reports/getBatchdetails/{batchid}/{partid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getBatchdetailsReports(@PathVariable("batchid")String batchid,@PathVariable("partid")String partid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getBatchdetails(participantDAOOBJ,batchid,partid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/getBatchdetails/{batchid}/{partid}/{tsid}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getBatchdetails(@PathVariable("batchid")String batchid,@PathVariable("partid")String partid,@PathVariable("tsid")String tsid)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getBatchdetails(participantDAOOBJ,batchid,partid,tsid);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}
		
		@RequestMapping(value="/getTestscheduleList/{batch}",method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<Object> getTestscheduleList(@PathVariable("batch")String batch)
		{
			inventoryResponseInfoOBJ = participantHelperOBJ.getTestscheduleList(participantDAOOBJ,batch);
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
		}

}
