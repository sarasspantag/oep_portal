package com.oep.controller.view;

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

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hero.heroutils.HERO_UTILS;
import com.oep.services.admin.util.oep_UTIL;

@Controller
@SessionAttributes( {"uid"})
public class LoginCtrl {

	private static Logger log = Logger.getLogger(LoginCtrl.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	HttpSession session;

	@RequestMapping("/Login")
	public ModelAndView loginPremia() {
		return new ModelAndView("/forms/admin/login/login", "message", "");
	}
	
}
