package com.oep.controller.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.oep.services.admin.util.oep_UTIL;

@Controller
@SessionAttributes( {"uid"})
public class ReportCtrl {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static Logger log = Logger.getLogger(ReportCtrl.class);
	
	@RequestMapping(value = "/reports/printcoursecertificate/{randomno}")
    public ModelAndView print(ModelAndView modelAndView,
    		@PathVariable("randomno") String randomno,HttpServletRequest httpRequest)
 	{
        try
        {
        
        Map<String,Object> parameterMap = new HashMap<String,Object>();  
         
        String imageURL = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=0";
        String imageURL1 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=1";
        String imageURL2 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=2";
        String imageURL3 = oep_UTIL.getApplicationSontext(httpRequest)+"/onlineexamine/forms/OEP_ImageView?index=3";
        
        parameterMap.put("datasource", jdbcTemplate.getDataSource());
        parameterMap.put("rand_no", randomno);
        parameterMap.put("logo", imageURL);
        parameterMap.put("president_sign", imageURL1);
        parameterMap.put("organiser_sign", imageURL2);
        parameterMap.put("comp_logo", imageURL3);
        
        log.info("parameterMap   print  "+parameterMap);
        modelAndView = new ModelAndView("participantReport", parameterMap);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return modelAndView;
    }
	
}

