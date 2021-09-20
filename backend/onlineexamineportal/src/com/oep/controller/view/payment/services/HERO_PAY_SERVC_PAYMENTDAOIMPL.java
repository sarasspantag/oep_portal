package com.oep.controller.view.payment.services;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.oep.services.admin.response.oep_ResponseInfo;


public class HERO_PAY_SERVC_PAYMENTDAOIMPL implements HERO_PAY_SERVC_IPAYMENTDAO{
	private static Logger log = Logger.getLogger(HERO_PAY_SERVC_PAYMENTDAOIMPL.class);
	@Override
	public oep_ResponseInfo getaddcartprocutlist1(
			String customerid, HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		log.info("payment "+customerid);
		return null;
	}

}
