package com.oep.controller.view.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
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
import org.springframework.web.servlet.ModelAndView;

import com.hero.heroutils.HERO_UTILS;
import com.oep.controller.view.payment.services.HERO_PAYMENT_VIEW_SERVICES;
import com.oep.controller.view.payment.services.HERO_PAY_SERVC_IPAYMENTDAO;
import com.oep.controller.view.payment.services.HERO_PAY_SERVC_PAYMENT_HELPER;
import com.oep.services.admin.util.oep_IDAO;
import com.oep.services.admin.util.oep_UTIL;
import com.oep.services.forms.faculty.oep_FacultyHelper;
import com.oep.services.forms.faculty.oep_IFacultyDAO;


@Controller
@SessionAttributes( {"uid"})
public class FacultyServiceCtrl extends oep_IDAO {

private static Logger log = Logger.getLogger(FacultyServiceCtrl.class);
	
	@Autowired
	private oep_FacultyHelper facultyHelperOBJ;
	@Autowired
	private oep_IFacultyDAO facultyDAOOBJ;

	@RequestMapping(value = "/savefacultydetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savefacultydetails(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = facultyHelperOBJ.savefacultydetails(uploadData, facultyDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value="/getFacultydetails/{facultyid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getFacultydetails(@PathVariable("facultyid")String facultyid)
	{
		inventoryResponseInfoOBJ = facultyHelperOBJ.getFacultydetails(facultyDAOOBJ,facultyid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFacultyList/{courseid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getFacultyList(@PathVariable("courseid")String courseid)
	{
		inventoryResponseInfoOBJ = facultyHelperOBJ.getFacultyList(facultyDAOOBJ,courseid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFacultyprofiledetails/{facultyprofileid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getFacultyprofiledetails(@PathVariable("facultyprofileid")String facultyprofileid)
	{
		inventoryResponseInfoOBJ = facultyHelperOBJ.getFacultyprofiledetails(facultyDAOOBJ,facultyprofileid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getFacultyList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getFacultyList()
	{
		inventoryResponseInfoOBJ = facultyHelperOBJ.getFacultyList(facultyDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
}

