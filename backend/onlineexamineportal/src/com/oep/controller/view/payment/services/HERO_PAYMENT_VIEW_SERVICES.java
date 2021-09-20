package com.oep.controller.view.payment.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oep.services.admin.util.oep_IDAO;


public class HERO_PAYMENT_VIEW_SERVICES extends oep_IDAO{

	private static Logger log = Logger.getLogger(HERO_PAYMENT_VIEW_SERVICES.class);
	
	@Autowired
	private HERO_PAY_SERVC_PAYMENT_HELPER paymentMasterHelperOBJ;
	@Autowired
	private HERO_PAY_SERVC_IPAYMENTDAO paymentDAOobj;

	
	@RequestMapping(value="/getaddcartprocutlist1/{customerid}",method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Object> getaddcartprocutlist1(@PathVariable("customerid")String customerid, HttpServletRequest httpRequest)
	{
		log.info("welcome to payment");
		inventoryResponseInfoOBJ = paymentMasterHelperOBJ.getaddcartprocutlist1(paymentDAOobj,customerid, httpRequest);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
}
