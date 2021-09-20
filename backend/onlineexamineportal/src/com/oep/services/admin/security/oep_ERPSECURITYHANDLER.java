package com.oep.services.admin.security;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.List;
import com.oep.services.admin.util.OepUserDetail;
import com.oep.services.admin.util.oep_UTIL;

public class oep_ERPSECURITYHANDLER implements HandlerInterceptor {

	private static Logger log = Logger.getLogger(oep_ERPSECURITYHANDLER.class);
	
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object obj, Exception exc)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		String url = req.getRequestURI();
		
		HttpSession session = req.getSession();
		boolean requestProcess = false;
		log.info(session.getAttribute("uid"));
		if(!url.endsWith("Login") && !url.endsWith("erpschedule") && !url.endsWith("out") && !url.endsWith("heroupload") && !url.endsWith("uploadFile")&& !url.endsWith("registerforgotpassword"))
		{
			if(session.getAttribute("uid") == null)	
			{
				log.info("Session is Empty.");
				res.sendRedirect("login");
				requestProcess = false;
			}
			else
			{
				requestProcess = true;
			}
		}
		else
		{
			requestProcess = true;
		}
		return requestProcess;
	}
	*/
	
	
	
	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		
		String url = req.getRequestURI();
		boolean requestProcess = false;
		String tokenkey = req.getHeader("tokenkey");
		
		if(tokenkey == null){
		HttpSession session = req.getSession();
	
		
		if(!url.endsWith("Login") && !url.endsWith("erpschedule") && !url.endsWith("OEP_TOKEN_VALIDATOR") && !url.endsWith("registerforgotpassword")  &&
				!url.endsWith("customerService") && url.contains("/reports/")  )
		{
			
			if(session.getAttribute("uid") == null &&  !url.contains("/reports/"))	
			{
				
				//res.sendRedirect("login");
				requestProcess = false;
			}
			else
			{			
				requestProcess = true;
			}
		}
		else
		{
			
			requestProcess = true;
			
		}
		}
		else{
			if(!url.endsWith("Login") && !url.endsWith("signup") && !url.endsWith("registerforgotpassword")  && !url.endsWith("getdecrypturl") && url.contains("printcoursecertificate")){
				
				//System.out.println("inside");
				//System.out.println(url.contains("printcoursecertificate"));
				/*log.info("INSIDEEEEEEEEEE");
				requestProcess = true;*/
				
				
				//String tokenkeyURL = "http://localhost:8080/tokencentre/gettokenkey/"+tokenkey;
				
				log.info("tokenkey  "+tokenkey);
				//URL url_token = new URL(tokenkeyURL);
				//String outputJSON = oep_UTIL.HTTPgetMethod(url_token);
				
				OepUserDetail responseJSON = oep_UTIL.gettokenkey(tokenkey);
				log.info("responseJSON  "+responseJSON.getTokenkey());
				//JSONObject responseJSON = new JSONObject(outputJSON);
				Map<String, Object> responseObjJSON =   (Map<String, Object>) responseJSON.getUserDetails().get(0);	
				//log.info(""+responseObjJSON);
				boolean isvalidtoken = false;
				
				if(responseObjJSON != null && responseJSON.getTokenkey() != null){
					Map<String, Object> userList =   responseObjJSON;
					if(userList!=null && userList.size()>0){
						//JSONObject userMap = (JSONObject) userList.get(0);
						HttpSession session = req.getSession();
						session.setAttribute("userid", userList.get("userid"));
					}
					
					isvalidtoken = true;
				}
				
				requestProcess = isvalidtoken;
				if(requestProcess == false){
					res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					res.getWriter().println("NOT VALID");
				}
			}else{
				
				//System.out.println("outside");
				requestProcess = true;
				
				
				/*String tokenkeyURL = "http://localhost:8080/tokencentre/gettokenkey/"+tokenkey;
				log.info(tokenkeyURL);
				
				URL url_token = new URL(tokenkeyURL);
				String outputJSON = oep_UTIL.HTTPgetMethod(url_token);
				log.info(outputJSON);
				JSONObject responseJSON = new JSONObject(outputJSON);
				JSONObject responseObjJSON = (JSONObject) responseJSON.get("response");
				log.info(responseJSON);
				log.info("output token"+responseObjJSON.get("tokenkey"));
				
				boolean isvalidtoken = false;
				
				if(responseObjJSON.get("tokenkey") != null && !responseObjJSON.get("tokenkey").equals(null)){
					JSONArray userList =  (JSONArray) responseObjJSON.get("userDetails");
					if(userList!=null && userList.length()>0){
						JSONObject userMap = (JSONObject) userList.get(0);
						log.info(userMap.get("userid"));
						HttpSession session = req.getSession();
						session.setAttribute("userid", userMap.get("userid"));
					}
					
					isvalidtoken = true;
				}
				log.info("is valid token  "+isvalidtoken);
				
				requestProcess = isvalidtoken;
				if(requestProcess == false){
					res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					res.getWriter().println("NOT VALID");
				}*/
			}
		
		}
		return requestProcess;
		
	}
}
