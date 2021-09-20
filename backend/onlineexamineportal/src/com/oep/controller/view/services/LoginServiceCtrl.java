package com.oep.controller.view.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hero.heroutils.HERO_UTILS;
import com.oep.controller.view.payment.services.HERO_PAYMENT_VIEW_SERVICES;
import com.oep.controller.view.payment.services.HERO_PAY_SERVC_IPAYMENTDAO;
import com.oep.controller.view.payment.services.HERO_PAY_SERVC_PAYMENT_HELPER;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.admin.util.oep_IDAO;
import com.oep.services.forms.login.oep_ILoginDAO;
import com.oep.services.forms.login.oep_LoginHelper;

@Controller
@SessionAttributes( {"uid"})
public class LoginServiceCtrl extends oep_IDAO {

	private static Logger log = Logger.getLogger(LoginServiceCtrl.class);
	private static Logger error_log = Logger.getLogger("requestLogger");
	
	@Autowired
	private oep_LoginHelper loginHelperOBJ;
	@Autowired
	private oep_ILoginDAO loginDAOOBJ;

	
	@RequestMapping(value = "/validLogin", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> validLogin(@RequestBody String formData,HttpServletRequest httpRequest) {
		try {			
			
			inventoryResponseInfoOBJ = loginHelperOBJ.validLogin( loginDAOOBJ,formData,httpRequest);	
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		    } catch (Exception e) {
			   e.printStackTrace();
			   error_log.info(e);
			   return oep_UTIL.returnExceptionFormat(e);
		    }
	}	
	
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> saveuser(@RequestBody String formData,HttpServletRequest httpRequest) {
		try {			
			
			inventoryResponseInfoOBJ = loginHelperOBJ.saveuser( loginDAOOBJ,formData,httpRequest);	
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		    } catch (Exception e) {
			   e.printStackTrace();
			   error_log.info(e);
			   return oep_UTIL.returnExceptionFormat(e);
		    }
	}	
	
	@RequestMapping(value = "/savemailtemplate", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> savemailtemplate(@RequestBody String formData,HttpServletRequest httpRequest) {
		try {			
			
			inventoryResponseInfoOBJ = loginHelperOBJ.savemailtemplate( loginDAOOBJ,formData,httpRequest);	
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		    } catch (Exception e) {
			   e.printStackTrace();
			   error_log.info(e);
			   return oep_UTIL.returnExceptionFormat(e);
		    }
	}	
	
	@RequestMapping(value = "/signout/{tokenkey}", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> signout(@PathVariable("tokenkey")String tokenkey) {
		
		try {
			inventoryResponseInfoOBJ = loginHelperOBJ.signout(tokenkey,loginDAOOBJ);				
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
			
		    } catch (Exception e) {			
			  log.error(e);
			  return oep_UTIL.returnExceptionFormat(e);
		}

	}
	
	
	@RequestMapping(value="/getMenuList/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getMenuList(@PathVariable("userid")String userid)
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.getMenuList(loginDAOOBJ,userid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getuserdetails/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getuserdetails(@PathVariable("userid")String userid)
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.getuserdetails(loginDAOOBJ,userid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getroleList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getroleList()
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.getroleList(loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/gettemplateList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> gettemplateList()
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.gettemplateList(loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/gettemplatetails/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> gettemplatetails(@PathVariable("id")String id)
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.gettemplatetails(loginDAOOBJ,id);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getuserList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getuserList()
	{
		inventoryResponseInfoOBJ = loginHelperOBJ.getuserList(loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/registerforgotpassword", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> registerforgotpassword(@RequestBody String formData,HttpServletRequest httpRequest) {
		try {		

			inventoryResponseInfoOBJ = loginHelperOBJ.registerforgotpassword(formData, loginDAOOBJ,httpRequest);			
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
			
			log.error(e);
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	@RequestMapping(value="/getdecrypturl",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getdecrypturl(@RequestBody String formData)
	{
		log.info("welcome to loadevents");
		inventoryResponseInfoOBJ = loginHelperOBJ.getdecrypturl( formData,loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> changepassword(@RequestBody String formData,HttpServletRequest httpRequest) {
		try {
			
			inventoryResponseInfoOBJ = loginHelperOBJ.changepassword(formData, loginDAOOBJ,httpRequest);			
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
			log.error(e);
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	@RequestMapping(value="/getloadscheduleevents/{roleid}/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getloadscheduleevents(@PathVariable("roleid") int roleid,@PathVariable("userid") int userid)
	{
		log.info("welcome to loadevents");
		inventoryResponseInfoOBJ = loginHelperOBJ.getloadscheduleevents(roleid,userid,loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getdashboarddetails",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getdashboarddetails()
	{
		log.info("welcome to loadevents");
		inventoryResponseInfoOBJ = loginHelperOBJ.getdashboarddetails(loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/checkcourseregisterparticipants/{scheduleid}/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> checkcourseregisterparticipants(@PathVariable("scheduleid")String scheduleid,@PathVariable("userid")String userid)
	{
		log.info("welcome to loadevents");
		inventoryResponseInfoOBJ = loginHelperOBJ.checkcourseregisterparticipants(scheduleid,userid,loginDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/savefile/{id}/{type}/{form}/{sessionname}/{foldername}",method=RequestMethod.POST)  
	public ResponseEntity<Object> savefile(@RequestBody String formData,
			@PathVariable("id") String id,@PathVariable("type") String type,@PathVariable("form") String form,
			@PathVariable("sessionname") String sessionname,@PathVariable("foldername") String foldername,
			@RequestParam CommonsMultipartFile file,HttpServletRequest httpRequest) {
		try {
			
			inventoryResponseInfoOBJ = loginHelperOBJ.savefile(formData,id,type,form,sessionname,foldername,file, loginDAOOBJ,httpRequest);			
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
			log.error(e);
			return oep_UTIL.returnExceptionFormat(e);
		}
	}
	
	
	@RequestMapping(value="/multipleSave/{id}/{type}/{form}/{sessionname}/{foldername}",method=RequestMethod.POST)  
	public  ResponseEntity<Object> multipleSave(@RequestBody String formData,
			@PathVariable("id") String id,@PathVariable("type") String type,@PathVariable("form") String form,
			@PathVariable("sessionname") String sessionname,@PathVariable("foldername") String foldername,
			@RequestParam CommonsMultipartFile file,HttpServletRequest httpRequest) {
		log.info("multipleSave");
		try {
			
			inventoryResponseInfoOBJ = loginHelperOBJ.multipleSave(formData,id,type,form,sessionname,foldername,file, loginDAOOBJ,httpRequest);			
			return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);

		} catch (Exception e) {
			log.error(e);
			return oep_UTIL.returnExceptionFormat(e);
		}
			
	}
}