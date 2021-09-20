package com.oep.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.hero.heroutils.HERO_TOKEN_UTIL;
import com.oep.services.admin.util.oep_UTIL;



@WebServlet("/OEP_ADM_SERVC_TOKEN_VALIDATOR")
public class OEP_ADM_SERVC_TOKEN_VALIDATOR extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(OEP_ADM_SERVC_TOKEN_VALIDATOR.class); 
	private static Logger error_log = Logger.getLogger("requestAppender");

   
    public OEP_ADM_SERVC_TOKEN_VALIDATOR() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try
		{
			
			 String previousURL = "";

		        if (request != null) {
		        	previousURL = request.getHeader("referer");
		        }
		        log.info("previousURL   "+previousURL);
		
		    if(previousURL != null && previousURL.contains("oep"))
		        
		        {

			String tokenKey = request.getParameter("tokenkey");
			String applncontect_URL = oep_UTIL.getApplicationSontext(request);
			HttpSession session = request.getSession();
			session.setAttribute("tokenkey", tokenKey);
			
			
			String tokenkeyURL = oep_UTIL.getApplicationSontext(request)+"/tokencentre/gettokenkey/"+tokenKey;
			log.info("tokenkeyURL   "+tokenkeyURL);
			URL url = new URL(tokenkeyURL);
			String outputJSON = oep_UTIL.HTTPgetMethod(url);
			log.info("outputJSON   "+outputJSON);
			
			String ssologinURL = oep_UTIL.getApplicationSontext(request)+"/onlineexamine/forms/validLogin";
			URL SSOurl = new URL(ssologinURL);
			JSONObject ssoJSON = HERO_TOKEN_UTIL.HTTPpostMethod(outputJSON, SSOurl);
			
			JSONObject inventoryResponseJSON = (JSONObject) ssoJSON.get("inventoryResponse");
			JSONObject responseObjJSON = (JSONObject) inventoryResponseJSON.get("responseObj");
			
			JSONArray userDetailsArr = (JSONArray) responseObjJSON.get("userDetails");
			JSONArray usermenuListArr = (JSONArray) responseObjJSON.get("usermenuList");
			
			
			if(userDetailsArr != null && userDetailsArr.size() > 0)
			{
				log.info("responseObjJSON   "+userDetailsArr.get(0));
				JSONObject responseObj = (JSONObject) userDetailsArr.get(0);
				String username = (String) responseObj.get("username");
				String userid = (String) responseObj.get("userid");
				log.info("usermenuListArr from responseObj   "+responseObj);
				
				session.setAttribute("usertype",Integer.parseInt((String)responseObj.get("role")));
				session.setAttribute("usertypestr",responseObj.get("role"));
				session.setAttribute("usertypedesc", responseObj.get("usertypedesc"));
				session.setAttribute("username",username.toUpperCase());
				session.setAttribute("uid",responseObj.get("userid"));
			
				session.setAttribute("modulepath",responseObj.get("modulepath"));				
				//session.setAttribute("mainmenuList", usermenuListArr);
				log.info("usermenuListArr from responseObj   "+responseObj);
				
				
								
				applncontect_URL = applncontect_URL+"/onlineexamine/forms/home?uid="+userid;
				log.info("applncontect_URL   "+applncontect_URL);
				if(applncontect_URL != null && applncontect_URL.length() > 0)
				{
					response.sendRedirect(applncontect_URL);	
				}
			}
			else
			{
				navigateToLogin(request,response);
			}
		}
		else
		{
			   navigateToLogin(request,response);
		}
		
		}
		catch(Exception e)
		{
			error_log.info(e);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void navigateToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String applncontect_URL = oep_UTIL.getApplicationSontext(request);
		applncontect_URL = applncontect_URL+"/oep";
		response.sendRedirect(applncontect_URL);
	}

}
