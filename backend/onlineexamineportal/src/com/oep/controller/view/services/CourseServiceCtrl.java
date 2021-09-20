package com.oep.controller.view.services;

import java.awt.PageAttributes.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
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



/*import com.oep.controller.view.payment.services.HERO_PAYMENT_VIEW_SERVICES;*/
import com.oep.services.admin.util.oep_IDAO;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.course.oep_CourseHelper;
import com.oep.services.forms.course.oep_ICourseDAO;


@Controller
@SessionAttributes( {"uid"})
public class CourseServiceCtrl extends oep_IDAO {
	
/*private static Logger log = Logger.getLogger(HERO_PAYMENT_VIEW_SERVICES.class);*/
private static Logger log = Logger.getLogger(CourseServiceCtrl.class);
	@Autowired
	private oep_CourseHelper courseHelperOBJ;
	@Autowired
	private oep_ICourseDAO courseDAOOBJ;
	
	
									/*POST services starts here*/
	
	@RequestMapping(value = "/savecoursemastermgmt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savecoursemastermgmt(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = courseHelperOBJ.savecoursemastermgmt(uploadData, courseDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value = "/savecourseschedule", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savecourseschedule(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.savecourseschedule(uploadData, courseDAOOBJ,httpRequest);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
    }
	
	
	/*@RequestMapping(value = "/saveexammaster", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveexammaster(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.saveexammaster(uploadData, courseDAOOBJ,httpRequest);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/savecourseyear", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savecourseyear(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.savecourseyear(uploadData, courseDAOOBJ,httpRequest);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
    }

	@RequestMapping(value = "/savesubject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savesubject(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = courseHelperOBJ.savesubject(uploadData, courseDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
		
	@RequestMapping(value="downloadsampleformat")
    public void downloadSampleFormat( HttpServletRequest httpRequest,HttpServletResponse httpResponse)
    {
		String fileName = "questionformat.xlsx";
		courseHelperOBJ.downloadSampleFormat(fileName, courseDAOOBJ, httpRequest, httpResponse);
		
    }*/
	
	
	@RequestMapping(value = "/savequestion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> importtoproduct(@RequestBody String uploadData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.savequestion(uploadData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	
	@RequestMapping(value = "/updatequestiondetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> updatequestiondetails(@RequestBody String uploadData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.updatequestiondetails(uploadData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	
	@RequestMapping(value = "/savetestschedule", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savetestschedule(@RequestBody String uploadData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.savetestschedule(uploadData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	
	@RequestMapping(value = "/savetestadministrator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savetestadministrator(@RequestBody String uploadData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.savetestadministrator(uploadData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	@RequestMapping(value = "/registerparticipantcourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> registerparticipantcourse(@RequestBody String registerData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.registerparticipantcourse(registerData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	@RequestMapping(value = "/saveparticpantsimport", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveparticpantsimport(@RequestBody String registerData,HttpServletRequest httpRequest) {
		
		try {
			  inventoryResponseInfoOBJ = courseHelperOBJ.saveparticpantsimport(registerData, courseDAOOBJ,httpRequest);			
			  return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
					
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	
									/*GET services starts here*/
	
	
	@RequestMapping(value="/getCoursedetails/{courseid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getCoursedetails(@PathVariable("courseid")String courseid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getCoursedetails(courseDAOOBJ,courseid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getcourseparticipantdetails/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getcourseparticipantdetails(@PathVariable("userid")String userid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getcourseparticipantdetails(courseDAOOBJ,userid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getCourseList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getCourseList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getCourseList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getallCourseList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getallCourseList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getallCourseList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value="/getSubjectList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getSubjectList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getSubjectList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}*/
	

	/*@RequestMapping(value="/getcheckedSubjectList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getcheckedSubjectList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getcheckedSubjectList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value="/getFacultyTestscheduleList/{subjectid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getFacultyTestscheduleList(@PathVariable("subjectid")String subjectid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getFacultyTestscheduleList(courseDAOOBJ,subjectid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQuestiondetails/{questionid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getQuestiondetails(@PathVariable("questionid")String questionid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getQuestiondetails(courseDAOOBJ,questionid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getQuestionbankdetails/{questionid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getQuestionbankdetails(@PathVariable("questionid")String questionid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getQuestionbankdetails(courseDAOOBJ,questionid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getScheduledetails/{scheduleid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getScheduledetails(@PathVariable("scheduleid")String scheduleid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getScheduledetails(courseDAOOBJ,scheduleid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getScheduleList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getScheduleList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getScheduleList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	

	@RequestMapping(value="/getTestscheduledetails/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTestscheduledetails(@PathVariable("id")String id)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getTestscheduledetails(courseDAOOBJ,id);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTestscheduleList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTestscheduleList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getTestscheduleList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getallTestscheduleList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getallTestscheduleList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getallTestscheduleList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getTestadministratordetails/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTestadministratordetails(@PathVariable("id")String id)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getTestadministratordetails(courseDAOOBJ,id);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTestadministratorList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTestadministratorList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getTestadministratorList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQuestionBankList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getQuestionBankList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getQuestionBankList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getIdformat/{id}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getIdformat(@PathVariable("id")String id,@PathVariable("type")String type)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getIdformat(courseDAOOBJ,id,type);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getfacultylistforreport",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getfacultylistforreport()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getfacultylistforreport(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getfacultyidforreport/{userid}/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getfacultyidforreport(@PathVariable("userid")String userid,@PathVariable("roleid")String roleid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getfacultyidforreport(courseDAOOBJ,userid,roleid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value="/getgetcoursedetailsforreport/{facultyid}/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getgetcoursedetailsforreport(@PathVariable("facultyid")String facultyid,@PathVariable("roleid")String roleid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getgetcoursedetailsforreport(courseDAOOBJ,facultyid,roleid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/gettestdetails/{courseid}/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> gettestdetails(@PathVariable("courseid")String courseid,@PathVariable("roleid")String roleid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.gettestdetails(courseDAOOBJ,courseid,roleid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/generatebatchreport/{courseid}/{roleid}/{userid}/{testid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> generatebatchreport(@PathVariable("courseid")String courseid,
							@PathVariable("roleid")String roleid, @PathVariable("userid")String userid, 
							@PathVariable("testid")String testid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.generatebatchreport(courseDAOOBJ,courseid,roleid,userid,testid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/generatefacultyreport/{courseid}/{roleid}/{userid}/{testid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> generatefacultyreport(@PathVariable("courseid")String courseid,
							@PathVariable("roleid")String roleid, @PathVariable("userid")String userid, 
							@PathVariable("testid")String testid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.generatefacultyreport(courseDAOOBJ,courseid,roleid,userid,testid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}	
	
	
	@RequestMapping(value="/getSchListforTestSchedule/{roleid}/{userid}/{qnid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getSchListforTestSchedule(@PathVariable("roleid")String roleid,@PathVariable("userid")String userid,@PathVariable("qnid")String qnid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getSchListforTestSchedule(courseDAOOBJ,roleid, userid,qnid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getQBMListforSchedule/{userid}/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getQBMListforSchedule(@PathVariable("userid")String userid,@PathVariable("roleid")String roleid)
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getQBMListforSchedule(courseDAOOBJ,userid,roleid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTrainingcourseList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTrainingcourseList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getTrainingcourseList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDepartmentList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getDepartmentList()
	{
		inventoryResponseInfoOBJ = courseHelperOBJ.getDepartmentList(courseDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
}
