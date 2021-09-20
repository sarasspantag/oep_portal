package com.oep.controller.view.payment.services;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;



public class HERO_PAY_SERVC_PAYMENT_HELPER {

	
	public oep_ResponseInfo getaddcartprocutlist1(HERO_PAY_SERVC_IPAYMENTDAO paymentDAOobj,String customerid, HttpServletRequest httpRequest)
	{
		return paymentDAOobj.getaddcartprocutlist1(customerid,httpRequest);
		 
	}
}
