package com.oep.services.admin.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.google.gson.Gson;
import com.oep.services.admin.response.oep_Response;
/*import com.oep.services.forms.course.IOException;*/
import com.oep.services.admin.security.emailsender;


public class oep_UTIL{

	public static String DATATABLE_ACTION = "<button class=\"edit myBtnTab\"> <i class=\"fa fa-edit\"></i> </button><button class=\"delete myBtnTab\""
			+ " data-toggle=\"modal\" data-target=\"#modal-delet\"> <i class=\"fa fa-trash-o\"></i> </button>";
	
	public static List<Object> smsTemplateList = new ArrayList<Object>();
	public static List<Object> smsSettingsList = new ArrayList<Object>();
	public static List<Object> emailSettingsList = new ArrayList<Object>();
	private static Logger log = Logger.getLogger(oep_UTIL.class);
	private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    public static HashMap<String, OepUserDetail> HERO_TOKENS = new HashMap<String, OepUserDetail>();
    
	@Value("${PROD.INS}")
	private static String message;

	
	@Autowired
	private static JdbcTemplate jdbcTemplate;

	@Autowired
	private static emailsender mailMail;

	public oep_UTIL() throws Exception {
	    myEncryptionKey = "ThisIsSpartaThisIsSparta";
	    myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
	    arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
	    ks = new DESedeKeySpec(arrayBytes);
	    skf = SecretKeyFactory.getInstance(myEncryptionScheme);
	    cipher = Cipher.getInstance(myEncryptionScheme);
	    key = skf.generateSecret(ks);
	}
	
	
	 public String urlencrypt(String unencryptedString) {
	        String encryptedString = null;
	        try {
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
	            byte[] encryptedText = cipher.doFinal(plainText);
	            encryptedString = new String(Base64.encodeBase64(encryptedText));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return encryptedString;
	    }

	 public String urldecrypt(String encryptedString) {
	        String decryptedText=null;
	        try {
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] encryptedText = Base64.decodeBase64(encryptedString);
	            byte[] plainText = cipher.doFinal(encryptedText);
	            decryptedText= new String(plainText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return decryptedText;
	    }


	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static Object convertJSONtooOBJECT(String formData, String className)
			throws ClassNotFoundException {
		Class myClass = Class.forName(className);
		Gson gson = new Gson();
		Object request = gson.fromJson(formData, myClass);
		return request;
	}
	
	public static Object convertJSONtooOBJECT(String formData, Class className)
			throws ClassNotFoundException {
		Gson gson = new Gson();
		Object request = gson.fromJson(formData, className);
		return request;
	}

	public static ResponseEntity<Object> returnExceptionFormat(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	public static String returnMessage()
	{
		return message;
	}
	
	public static String returnJSONobject(List<Object> list)
	{
		String JSON = new Gson().toJson(list);
		return JSON;
	}
	
	public static String returnJSONobject(Object obj)
	{
		String JSON = new Gson().toJson(obj);
		return JSON;
	}
	
	public static oep_Response returnResponse(Map<String, Object> resultMap,oep_Response inventoryResponseOBJ)
	{
		log.info(resultMap);
		if(resultMap != null)
		{
			if(resultMap.get("#update-count-1") != null && ((int)resultMap.get("#update-count-1")) > 0)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", resultMap.get("out_genrate_id"));
				map.put("msg", resultMap.get("out_result_msg"));
				log.info(map);
				inventoryResponseOBJ.setResponseType((String)resultMap.get("out_result_type"));
				inventoryResponseOBJ.setResponseObj(map);
			}
			else
			{
				inventoryResponseOBJ.setResponseType("F");
				inventoryResponseOBJ.setResponseObj((String)resultMap.get("out_result_msg"));
			}
		}
		return inventoryResponseOBJ;
	}
	
	public static oep_Response returnExceptionResponse(oep_Response inventoryResponseOBJ,Exception e)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", "0");
		
		if(e instanceof org.springframework.dao.DataIntegrityViolationException)
		{
			map.put("msg", "Dependent Data Exists. You cannot delete this data");	
		}
		else
		{
			map.put("msg", "System Error Occured. Please Contact System Administrator");
		}
		
		log.info(map);
		inventoryResponseOBJ.setResponseType("F");
		inventoryResponseOBJ.setResponseObj(map);
		
		/*inventoryResponseOBJ.setResponseType("F");
		inventoryResponseOBJ.setResponseObj("System Error Occured. Please Contact System Administrator");*/
		
		return inventoryResponseOBJ;
	}
	
	public static String getDataTableActionString(String editMethodName,String deleteMethodName)
	{
		editMethodName = "(\""+editMethodName+"\")";
		deleteMethodName = "(\""+deleteMethodName+"\")";
		
		String action = "<button class=\"edit myBtnTab\" onclick="+editMethodName+"> <i class=\"fa fa-edit\"></i> </button><button class=\"delete myBtnTab\" data-toggle=\"modal\" data-target=\"#modal-delet\" onclick="+deleteMethodName+"> <i class=\"fa fa-trash-o\"></i> </button>";
		
		log.info("MethodNames "+editMethodName+"   "+deleteMethodName+"     "+action);
		
		return action;
	}
	
	public static <T> List<T> convertJSONArraytoList(String jsonArrayString,String className) throws JSONException, ClassNotFoundException
	{
		List<T> list = new ArrayList<T>();
		JSONArray jsonArr = new JSONArray(jsonArrayString);
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			T item = (T) oep_UTIL.convertJSONtooOBJECT(jsonObj.toString(), className);
			list.add(item);
		}
		return list;
	}
	
	public static Date convertToSQLDate(String stringDate) throws ParseException
	{
		Date parsedstringDate = null;
		java.sql.Date sqlDate = null;
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		parsedstringDate = format.parse(stringDate);
		sqlDate = new java.sql.Date(parsedstringDate.getTime());

		
		log.info("Dates        "+parsedstringDate+"   "+sqlDate);			
		
		return sqlDate;
		}
	
	public static Date convertToNewSQLDate(String stringDate) throws ParseException
	{
		Date parsedstringDate = null;
		java.sql.Date sqlDate = null;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		parsedstringDate = format.parse(stringDate);
		sqlDate = new java.sql.Date(parsedstringDate.getTime());

		
		log.info("Dates        "+parsedstringDate+"   "+sqlDate);			
		
		return sqlDate;
		}
	
	
public static String inventoryformactionscript(String visibleaction1,String visibleaction2)
{
	return "<button class=\"edit myBtnTab\"style=\"display:"+visibleaction1+";\"> <i class=\"fa fa-edit\"></i> </button><button style=\"display:"+visibleaction2+";\" class=\"delete myBtnTab\" data-toggle=\"modal\" data-target=\"#modal-delet\"> <i class=\"fa fa-trash-o\"></i> </button>";
	
}

public static String encrypt(String key, String initVector, String value) {
    try {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes());
        log.info("ERP Encrypt Value: "
                + Base64.encodeBase64String(encrypted));

        return Base64.encodeBase64String(encrypted);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return null;
}

public static String decrypt(String key, String initVector, String encrypted) {
    try {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
       
        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

        return new String(original);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return null;
}

public static int startIndex(int pageNo)
{
	int start = ((pageNo - 1) * 10);
	log.info("Start   "+start);
	return start;
}

public static int endIndex(int pageNo)
{
	int end = ((pageNo) * 10);
	log.info("   End   "+end);
	return end;
}

public static Object getValueFromList(Map<String, Object> map,String mapKey)
{
	Object value = 0;
	List<Object> List = (List<Object>) map.get(mapKey);
	
	if(List != null && List.size() > 0)
	{
		value = List.get(0);
	}
	else
	{
		value = 0;
	}
	
	return value;
}

public static Object executeQuery(String query,JdbcTemplate jdbcTemplateObj)
{
	Object value = 0;
	try
	{
		@SuppressWarnings("unchecked")
		List<Object> outputList = jdbcTemplateObj.query(query, new RowMapper() {
			 
			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {

				  String value = rs.getString(1);
				  
				  return value;
			}
		});
		
		value = outputList; 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return value;
}

public static Object executeQueryWithList(String query,JdbcTemplate jdbcTemplateObj)
{
	Object value = 0;
	try
	{
		@SuppressWarnings("unchecked")
		List<Object> outputList = jdbcTemplateObj.query(query, new RowMapper() {
			 
			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				for(int columnLoop = 1;columnLoop <= columnsNumber;columnLoop++)
				{
					String columnName = rsmd.getColumnName(columnLoop);
					String value = rs.getString(columnLoop);
					map.put(columnName, value);
				}
				
				return map;
			}
		});
		
		value = outputList; 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return value;
}

public static String convertDecimalFormat(int convertRange, double value){

	BigDecimal bd = new BigDecimal(value);
	bd = bd.setScale(convertRange,BigDecimal.ROUND_HALF_UP);
	return bd.toString();
}


public static String IndianFormat(BigDecimal n) {
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    //we never reach double digit grouping so return
    if (n.doubleValue() < 100000) {
        return formatter.format(n.setScale(2, 1).doubleValue());
    }
    StringBuffer returnValue = new StringBuffer();
    //Spliting integer part and decimal part
    String value = n.setScale(2, 1).toString();
    String intpart = value.substring(0, value.indexOf("."));
    String decimalpart = value.substring(value.indexOf("."), value.length());
    //switch to double digit grouping
    formatter.applyPattern("#,##");
    returnValue.append(formatter.format(new BigDecimal(intpart).doubleValue() / 1000)).append(",");
    //appending last 3 digits and decimal part
    returnValue.append(intpart.substring(intpart.length() - 3, intpart.length())).append(decimalpart);
    //returning complete string
    return returnValue.toString();
}
public static int sendSMS(JdbcTemplate jdbcTemplate,String mobieno, String messageContent){
	   
	int sendstatus = 0;
	try{
	
	SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_HERO_INSERT_SMS_HISTORY");
	Map<String, Object> inParamMap = new HashMap<String, Object>();
	inParamMap.put("P_ISH_ID", 0);
	inParamMap.put("P_ISH_MOB_NO", mobieno);
	inParamMap.put("P_ISH_SMS_CONTENT", messageContent);
	inParamMap.put("P_ISH_STATUS", 0);
	inParamMap.put("P_SMS_RESPONSE", "");
	inParamMap.put("P_ACTION", "INS");
	
	//log.info("sendSMSNotification inParamMap         "+inParamMap);
	SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	Map<String, Object> resultMap = jdbcCALL.execute(in);
	log.info(resultMap);
	String status = (String)resultMap.get("out_genrate_id");
	if(status == "1"){
		sendstatus = 1;
	}
	
	}catch (Exception e) {
		sendstatus = 0;
		log.info("Error SMS "+e);
	}
   
   return sendstatus;
}
public static int sendMail(JdbcTemplate jdbcTemplate,String to,String msg, String subject,String fromSqlstatus){
	int sendstatus = 0;
	try{
	
		SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_INSERT_EMAIL_HISTORY");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_IMH_ID", 0);
		inParamMap.put("P_IMH_MAIL_ID", to);
		inParamMap.put("P_IMH_MAIL_SUBJECT", subject);
		inParamMap.put("P_IMH_MAIL_CONTENT", msg);
		inParamMap.put("P_IMH_STATUS", 0);
		inParamMap.put("P_IMH_RESPONSE", "");
		inParamMap.put("P_OPRN", "INS");
		
		log.info("sendEmailNotification inParamMap         "+inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> resultMap = jdbcCALL.execute(in);
		log.info(resultMap);
		
		int id = 0;
		if(resultMap.get("out_genrate_id").getClass().getName().equals("java.lang.Integer")){
			id = (int) resultMap.get("out_genrate_id");
		}else if(resultMap.get("out_genrate_id").getClass().getName().equals("java.lang.String")){
			id = Integer.parseInt((String) resultMap.get("out_genrate_id"));
		}
		log.info(resultMap.get("out_genrate_id").getClass().getName());

		sendMailfromSql(String.valueOf(id));

		String status = null;
		if(resultMap.get("out_genrate_id").getClass().getName().equals("java.lang.Integer")){
			status = String.valueOf(resultMap.get("out_genrate_id"));
		}else if(resultMap.get("out_genrate_id").getClass().getName().equals("java.lang.String")){
			status = (String)resultMap.get("out_genrate_id");
		}
		if(status == "1"){
			sendstatus = 1;
		}
		
		}catch (Exception e) {
			sendstatus = 0;
			log.info("Error SMS "+e);
		}
   
   return sendstatus;
}

	
	public static void sendMailfromSql(String mailid){
		
		log.info("query   "+"SELECT `imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content` FROM `email_history` "
					+ "WHERE `imh_id` = "+mailid+" "
					+ " AND `imh_email_content` IS NOT NULL  ");
		try{
			@SuppressWarnings("unchecked")
			/*List<Object> emailTemplatelist = jdbcTemplate.query("SELECT `imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content` FROM `email_history` "
					+ "WHERE `imh_staus` = 0 AND ((SELECT COUNT(*) FROM `email_history` WHERE `imh_staus` = -1) = 0) "
					+ " AND `imh_email_content` IS NOT NULL  ", new RowMapper() {*/
						
			List<Object> emailTemplatelist = jdbcTemplate.query("SELECT `imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content` FROM `email_history` "
					+ "WHERE `imh_id` = "+mailid+" "
					+ " AND `imh_email_content` IS NOT NULL  ", new RowMapper() {			
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("imhid", rs.getString("imh_id"));
					map.put("emailid", rs.getString("imh_email_id"));
					map.put("emailsubject", rs.getString("imh_email_subject"));
					map.put("emailcontent", rs.getString("imh_email_content"));
					
					return map;
				}
			});
			
			
			final StringBuffer stringBuffer = new StringBuffer();
			String EMAILresult = "";
			log.info("emailTemplatelist "+emailTemplatelist.size()+"  emailSettingsList  "+emailSettingsList.size());
			if(emailTemplatelist.size() > 0 && emailSettingsList.size() > 0)
			{
				int status = 0;
				Iterator<Object> emailTemplateITR = emailTemplatelist.iterator();
				while(emailTemplateITR.hasNext())
				{
				
				Map<String, Object> templateMap = (Map<String, Object>) emailTemplateITR.next();
				String templateMessage = "",emailid="",imhid="0",templateSubject="";
				 
				if(templateMap != null)
				{
					imhid = (String) templateMap.get("imhid");
					templateSubject = (String) templateMap.get("emailsubject");
					templateMessage = (String) templateMap.get("emailcontent");
					emailid = (String) templateMap.get("emailid");
				}
				
				status = -1;
				SimpleJdbcCall jdbcCALL_before = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_INSERT_EMAIL_HISTORY");
				Map<String, Object> inParamMap_before = new HashMap<String, Object>();
				inParamMap_before.put("P_IMH_ID", imhid);
				inParamMap_before.put("P_IMH_MAIL_ID", "");
				inParamMap_before.put("P_IMH_MAIL_SUBJECT", "");
				inParamMap_before.put("P_IMH_MAIL_CONTENT", "");
				inParamMap_before.put("P_IMH_STATUS", status);
				inParamMap_before.put("P_IMH_RESPONSE", EMAILresult);
				inParamMap_before.put("P_OPRN", "UPD_EMAIL_STATUS");
				
				SqlParameterSource in_before = new MapSqlParameterSource(inParamMap_before);
				Map<String, Object> resultMap_before = jdbcCALL_before.execute(in_before);
				
				log.info("resultMap_before   "+inParamMap_before+resultMap_before);
				
				Map<String, Object> settingsMap = (Map<String, Object>) emailSettingsList.get(0);
				
				final String from_mail = (String) settingsMap.get("emailid");
				final String from_mail_password = (String) settingsMap.get("emailpassword");
				
				log.info("Email Settings Values are   "+from_mail+"   "+from_mail_password +"  "+emailid);
				try{
					
					ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
					emailsender mm = (emailsender) context.getBean("mailMail");
                    mm.sendattaMail(templateSubject, emailid, templateMessage);
                    status = 1;
                    log.info("mm"+mm);
				}catch(Exception e){
					e.printStackTrace();
				}
/*					log.info("Email Settings Values are   ");
			
			      Properties props = new Properties();    
			      props.put("mail.smtp.host", "smtp.gmail.com");    
			      props.put("mail.smtp.socketFactory.port", "465");    
			      props.put("mail.smtp.socketFactory.class",    
			                "javax.net.ssl.SSLSocketFactory");    
			      props.put("mail.smtp.auth", "true");    
			      props.put("mail.smtp.port", "465");  
			    
			      Session session = Session.getDefaultInstance(props,    
			       new javax.mail.Authenticator() {    
			       protected  PasswordAuthentication getPasswordAuthentication() {    
			       return new PasswordAuthentication(from_mail,from_mail_password);  
			       }    
			      });    
			    
			      
			      try {    
			       MimeMessage message = new MimeMessage(session);    
			       message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailid));    
			       message.setSubject(templateSubject);    
			      
			       message.setContent(templateMessage, "text/html; charset=utf-8");
			     
			       Transport.send(message);    
			       status = 1; 
			      } catch (MessagingException e) {
			    	  status = 0;
			      }*/  
				log.info("status"+status);
				
				
				SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_INSERT_EMAIL_HISTORY");
				Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("P_IMH_ID", imhid);
				inParamMap.put("P_IMH_MAIL_ID", "");
				inParamMap.put("P_IMH_MAIL_SUBJECT", "");
				inParamMap.put("P_IMH_MAIL_CONTENT", "");
				inParamMap.put("P_IMH_STATUS", status);
				inParamMap.put("P_IMH_RESPONSE", EMAILresult);
				inParamMap.put("P_OPRN", "UPD_EMAIL_STATUS");
				
				log.info("EMINotification inParamMap         "+inParamMap);
				SqlParameterSource in = new MapSqlParameterSource(inParamMap);
				Map<String, Object> resultMap = jdbcCALL.execute(in);
				log.info(resultMap);
				
				}
			}
			
			
		}catch (Exception e) {
			log.info("Error MAIL "+e);
			
		}
	}

public static boolean contains(int[] arr, int item) {
    for (int n : arr) {
       if (item == n) {
          return true;
       }
    }
    return false;
 }

public static  Map<String, Object> executeProcedure(JdbcTemplate jdbcTemplate,String procedureName,
		LinkedHashMap inParamMap) {
	// TODO Auto-generated method stub
	
	SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName);
	
	SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	Map<String, Object> resultMap = jdbcCALL.execute(in);
	
	return resultMap;
}

public static List<String> getStringList(JdbcTemplate jdbcTemplate,String query)
{
	
	List<String> list = jdbcTemplate.query(query, new RowMapper() {
		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			return rs.getString(1);
		}
	});
	return list;
}

public static DataValidation getCellList(DataValidationHelper validationHelper,String[] cellArray,XSSFSheet sheet,boolean dropDownArrow,int EndRow,int Column)
{
	DataValidation dataValidation = null;
	DataValidationConstraint constraint = null;
	
	
	CellRangeAddressList brandCellList = new  CellRangeAddressList(1,EndRow,Column,Column);
	constraint =validationHelper.createExplicitListConstraint(cellArray);
	dataValidation = validationHelper.createValidation(constraint, brandCellList);
	dataValidation.setSuppressDropDownArrow(dropDownArrow);      
	sheet.addValidationData(dataValidation);
	
	return dataValidation;
}


public static String[] convertListtoStringArray(List<String> list)
{
	String[] listArr = list.toArray(new String[0]);
	return listArr;
}

public static String getserverfilepath(HttpServletRequest httpRequest)
{
	String path=httpRequest.getRealPath("/");
	File fserverpath = new File(path);
	String rootpath = (fserverpath.getParent());
	path=rootpath.concat(File.separator).concat("documents");
	return path;
}
public static void sendattachedmailfromSql(String mailid,String filepath){
	
	log.info("query   "+"SELECT `imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content` FROM `email_history` "
				+ "WHERE `imh_id` = "+mailid+" "
				+ " AND `imh_email_content` IS NOT NULL  ");
	try{
		@SuppressWarnings("unchecked")
		
					
		List<Object> emailTemplatelist = jdbcTemplate.query("SELECT `imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content` FROM `email_history` "
				+ "WHERE `imh_id` = "+mailid+" "
				+ " AND `imh_email_content` IS NOT NULL  ", new RowMapper() {			
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("imhid", rs.getString("imh_id"));
				map.put("emailid", rs.getString("imh_email_id"));
				map.put("emailsubject", rs.getString("imh_email_subject"));
				map.put("emailcontent", rs.getString("imh_email_content"));
				
				return map;
			}
		});
		
		
		final StringBuffer stringBuffer = new StringBuffer();
		String EMAILresult = "";
		log.info("emailTemplatelist "+emailTemplatelist.size()+"  emailSettingsList  "+emailSettingsList.size());
		if(emailTemplatelist.size() > 0 && emailSettingsList.size() > 0)
		{
			int status = 0;
			Iterator<Object> emailTemplateITR = emailTemplatelist.iterator();
			while(emailTemplateITR.hasNext())
			{
			
			Map<String, Object> templateMap = (Map<String, Object>) emailTemplateITR.next();
			String templateMessage = "",emailid="",imhid="0",templateSubject="";
			 
			if(templateMap != null)
			{
				imhid = (String) templateMap.get("imhid");
				templateSubject = (String) templateMap.get("emailsubject");
				templateMessage = (String) templateMap.get("emailcontent");
				emailid = (String) templateMap.get("emailid");
			}
			
			status = -1;
			SimpleJdbcCall jdbcCALL_before = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_INSERT_EMAIL_HISTORY");
			Map<String, Object> inParamMap_before = new HashMap<String, Object>();
			inParamMap_before.put("P_IMH_ID", imhid);
			inParamMap_before.put("P_IMH_MAIL_ID", "");
			inParamMap_before.put("P_IMH_MAIL_SUBJECT", "");
			inParamMap_before.put("P_IMH_MAIL_CONTENT", "");
			inParamMap_before.put("P_IMH_STATUS", status);
			inParamMap_before.put("P_IMH_RESPONSE", EMAILresult);
			inParamMap_before.put("P_OPRN", "UPD_EMAIL_STATUS");
			
			SqlParameterSource in_before = new MapSqlParameterSource(inParamMap_before);
			Map<String, Object> resultMap_before = jdbcCALL_before.execute(in_before);
			
			log.info("resultMap_before   "+inParamMap_before+resultMap_before);
			
			Map<String, Object> settingsMap = (Map<String, Object>) emailSettingsList.get(0);
			
			final String from_mail = (String) settingsMap.get("emailid");
			final String from_mail_password = (String) settingsMap.get("emailpassword");
			
			log.info("Email Settings Values are   "+from_mail+"   "+from_mail_password +"  "+emailid);
			try{
				
				ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
				emailsender mm = (emailsender) context.getBean("mailMail");
                mm.sendattachpdfMail(templateSubject, emailid, templateMessage,filepath);
                status = 1;
                log.info("mm"+mm);
			}catch(Exception e){
				e.printStackTrace();
			}

			log.info("status"+status);
			
			
			SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_EXAMPORTAL_INSERT_EMAIL_HISTORY");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_IMH_ID", imhid);
			inParamMap.put("P_IMH_MAIL_ID", "");
			inParamMap.put("P_IMH_MAIL_SUBJECT", "");
			inParamMap.put("P_IMH_MAIL_CONTENT", "");
			inParamMap.put("P_IMH_STATUS", status);
			inParamMap.put("P_IMH_RESPONSE", EMAILresult);
			inParamMap.put("P_OPRN", "UPD_EMAIL_STATUS");
			
			log.info("EMINotification inParamMap         "+inParamMap);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> resultMap = jdbcCALL.execute(in);
			log.info(resultMap);
			
			}
		}
		
		
	}catch (Exception e) {
		log.info("Error MAIL "+e);
		
	}
}

public static String getmodulename(String form,int type)
{
	String modulename = null;
	if(form != null && form.equalsIgnoreCase("course"))
	{
		if(type == 1)
		{
			modulename = "course".concat(File.separator).concat("details");
		}
		else if(type == 2)
		{
			modulename = "course".concat(File.separator).concat("thumbnails");
		}	
	}
	else if(form != null && form.equalsIgnoreCase("user")){
		

		if(type == 1)
		{
			modulename = "user".concat(File.separator).concat("image");
			
		}
		
	}
	
else if(form != null && form.equalsIgnoreCase("schedule")){
		

		if(type == 1)
		{
			modulename = "schedule".concat(File.separator).concat("scheduleimport");
			
		}
		
	}else if(form != null && form.equalsIgnoreCase("faculty")){
		

		if(type == 1)
		{
			modulename = "faculty".concat(File.separator).concat("facultyimage");
			
		}
		
	}
     else if(form != null && form.equalsIgnoreCase("gallery")){
		

		if(type == 1)
		{
			modulename = "gallery".concat(File.separator).concat("galleryimage");
			
		}
		
	}
     else if(form != null && form.equalsIgnoreCase("alumni")){
 		

 		if(type == 1)
 		{
 			modulename = "alumni".concat(File.separator).concat("image");
 			
 		}
 		
 	}
     else if(form != null && form.equalsIgnoreCase("newsform")){
  		

  		if(type == 1)
  		{
  			modulename = "newsform".concat(File.separator).concat("image");
  			
  		}
  		
  	}
	
	else if(form != null && form.equalsIgnoreCase("product"))
	{
		if(type == 1)
		{
			modulename = "product".concat(File.separator).concat("image");
		}
		else if(type == 2)
		{
			modulename = "product".concat(File.separator).concat("participantimage");
		}	
		else if(type == 3)
		{
			modulename = "product".concat(File.separator).concat("blogimage");
		}
		else if(type == 4)
		{
			modulename = "product".concat(File.separator).concat("thumbnail3");
		}
		else if(type == 5)
		{
			modulename = "product".concat(File.separator).concat("thumbnail4");
		}
		else if(type == 6)
		{
			modulename = "product".concat(File.separator).concat("smallimage");
		}
		else if(type == 7)
		{
			modulename = "product".concat(File.separator).concat("description");
		}
		else if(type == 8)
		{
			modulename = "product".concat(File.separator).concat("video");
		}
	}
	else if(form != null && form.equalsIgnoreCase("productimport"))
	{
		if(type == 1)
		{
			modulename = "productimport".concat(File.separator).concat("import");
		}
	}
	
	else if(form != null && form.equalsIgnoreCase("bulkupload"))
	{
		if(type == 0)
		{
			modulename = "bulkupload".concat(File.separator).concat("importbulkupload");
		}
	}
	
	else if(form != null && form.equalsIgnoreCase("productdesc"))
	{
		if(type == 1)
		{
			modulename = "product".concat(File.separator).concat("description");
		}
	}
	
	else if(form != null && form.equalsIgnoreCase("productvideo"))
	{
		if(type == 1)
		{
			modulename = "product".concat(File.separator).concat("video");
		}
	}
else if(form != null && form.equalsIgnoreCase("news")){
		

		if(type == 3)
		{
			modulename = "news".concat(File.separator).concat("newsimage");
			
		}
		
		if(type == 7)
		{
			modulename = "news".concat(File.separator).concat("newsdescription");
			
		}
		
	}
	return modulename;
}

public static String filepath(String path) {
	int documentsIndex = path.indexOf("documents");
	String filePath = (path.substring(documentsIndex,path.length()));
	filePath = "herofiles".concat(File.separator).concat(filePath);
	log.info("filePath   "+filePath);
	return filePath;
}

public static String getuploadfilepath(HttpServletRequest httpRequest,String form,int type,int id)
{
	String modulename = form;
	String path=httpRequest.getRealPath("/");
	File fserverpath = new File(path);
	String rootpath = (fserverpath.getParent());
	path=rootpath.concat(File.separator).concat("documents").concat(File.separator).concat(modulename);
	path = path.concat(File.separator).concat("temp");
	//log.info("path   "+path);
	return path;
}
public static String getuploadfilename(String filename,int id,String fileextension)
{
/*if(id == 0)
  {*/
	final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	StringBuilder builder = new StringBuilder();
	int count = 15;
	while (count-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	filename = builder.toString()+fileextension;
  /*}*/
return filename;
}

public static String getApplicationSontext(HttpServletRequest httpRequest)
{
	String applncontect_URL = httpRequest.getScheme() + "://" +   
            httpRequest.getServerName() +      
            ":" +                           
            httpRequest.getServerPort();      
	
	log.info("applncontect_URL is   "+applncontect_URL);
	return applncontect_URL;
}



public static String HTTPgetMethod(URL url) {
		String output = null;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			//log.info(conn.getResponseCode());
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((output = br.readLine()) != null) 
			{
				responseBuilder.append(output);
			}
			output = responseBuilder.toString();
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return output;
	}
	







public static JSONObject HTTPpostMethod(String input,URL url) throws org.json.simple.parser.ParseException {
	StringBuilder responseBuilder = new StringBuilder();
	String response = null;
	JSONObject json = null;
	try {
		/*String getTokenKeyURL = res.getString("generatetokenkeyurl");
	URL url = new URL(getTokenKeyURL);*/
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");

	OutputStream os = conn.getOutputStream();
	os.write(input.getBytes());
	os.flush();

	if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	}

	BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	while ((response = br.readLine()) != null) {
		responseBuilder.append(response);
	}
	
	response = responseBuilder.toString();
	//json = converttoJSON(response);
	conn.disconnect();
  } catch (MalformedURLException e) {

	e.printStackTrace();

  } catch (IOException e) {

	e.printStackTrace();

 }
	 
	return json;
}


public static OepUserDetail generatetokenkey(String formdata,JdbcTemplate jdbcTemplate) {

	//HashMap<String, OepUserDetail> HERO_TOKENS = new HashMap<String, OepUserDetail>();
	OepUserDetail userDetail = new OepUserDetail();
	long tokenkey = 0;
	String formData = formdata;
	JSONObject jsonObj = null;
	
	try
	{
		OEP_ADM_SERVC_REQUEST loginrequest = (OEP_ADM_SERVC_REQUEST)
				convertJSONtooOBJECT(formData,
						"com.oep.services.admin.util.OEP_ADM_SERVC_REQUEST");
		
		int applnType = 1;
		
		String selectQuery = "SELECT `userid`,`role`,`email`,`username`,`phone_no`,`role_name` FROM `users` a JOIN `roles` b ON a.`role` = b.`id`"
				+ " WHERE `email` = ? AND `password`= ? ";
		
						
	String invUserPW = encrypt("Herbzaliveerpapp", "ppapreevilazbreH", loginrequest.getPassword());
	log.info("invUserPW"+invUserPW);
		
			
	@SuppressWarnings("unchecked")
	List<Object> userDetails = jdbcTemplate.query(selectQuery,new Object[]{loginrequest.getUsername(),invUserPW},
			new RowMapper() {

				@Override
				public Object mapRow(ResultSet rs, int arg1)
						throws SQLException {
				
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("userid", rs.getString("userid"));
					map.put("roleid", rs.getString("role"));
					map.put("email", rs.getString("email"));
					map.put("phone_no", rs.getString("phone_no"));
					map.put("role", rs.getString("role_name"));
					map.put("username", rs.getString("username"));
					return map;
				}
			});
	log.info("userDetails        "+userDetails);
	if(userDetails != null && userDetails.size() > 0)
	{
		userDetail.setUserDetails(userDetails);
		
		Random randomno = new Random();
		tokenkey = randomno.nextLong();
		if(tokenkey < 1)
		{
			tokenkey = tokenkey * -1;
		}
		
		userDetail.setTokenkey(String.valueOf(tokenkey));
		
		log.info("String.valueOf(tokenkey)  "+String.valueOf(tokenkey));
		
		
		String tokenid="0",username = "",userid="",role="",currencyid="",usertypedesc="",currencydecimal="",currencysymbol="",applntype="",currencycode="";
		int storeid = 0;
			Map<String, Object> userMap = (Map<String, Object>) userDetails.get(0);
			log.info("userMap"+userMap);
			userid = (String)userMap.get("userid"); 
			role = (String)userMap.get("roleid"); 
			
		
		
		try {
		
			SimpleJdbcCall jdbcCALL = new SimpleJdbcCall(jdbcTemplate).withProcedureName("P_OEP_TOKEN_MASTER");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_TOKEN_ID", tokenid);
			inParamMap.put("P_TOKEN_KEY", String.valueOf(tokenkey));
			inParamMap.put("P_TOKEN_LOGINOUT_STATUS", "0");
			inParamMap.put("P_TOKEN_UD_USERID", userid);
			inParamMap.put("P_TOKEN_UD_USERNAME", username);
			inParamMap.put("P_TOKEN_UD_ROLE", role);
			inParamMap.put("P_OPRN", "INS");
			log.info("inparammap    "+ inParamMap);
			
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> resultMap = jdbcCALL.execute(in);
			log.info("resultMap    "+resultMap);
		} catch (Exception e) {
		
		e.printStackTrace();
		}
	}
	
	HERO_TOKENS.put(String.valueOf(tokenkey), userDetail);
	log.info("userDetails"+userDetail);
	
	
	//jsonObj.put("response", userDetail);
	//responseObj.setResponse(userDetail);
	//log.info("json"+jsonObj.get("response"));

	}
	catch(Exception e)
	{
		e.printStackTrace();
//		responseBeanObj.setResponseType("F");
//		responseBeanObj.setResponseObj(e.getMessage());
//		responseObj.setResponse(responseBeanObj);
	}
	
	return userDetail;

}


public static OepUserDetail gettokenkey(String tokenkey) {
	OepUserDetail userDetailsOBJ = new OepUserDetail();
	try{
		
		try{
		//logger.info("HERO_TOKENS   "+HERO_TOKENS);
		log.info("userDetailsOBJ"+userDetailsOBJ);
		
		
		String emailSettingsQuery = "select token_ud_userid from token where token_key = '"+tokenkey+"'";
		log.info("emailSettingsQuery"+emailSettingsQuery);
		@SuppressWarnings("unchecked")
		List<Object> SettingsList = jdbcTemplate.query(emailSettingsQuery,
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
					
						Map<String, Object> emailMap = new HashMap<String, Object>();
						
						emailMap.put("userid", rs.getString("token_ud_userid"));
						
						
						return emailMap;
					}
				});
		
		log.info("SettingsList"+SettingsList);
		
		if(SettingsList != null && SettingsList.size() > 0){
			Map<String, String> map = (Map<String, String>) SettingsList.get(0);
			OEP_ADM_SERVC_REQUEST  userdetails = new OEP_ADM_SERVC_REQUEST();
			log.info("map"+map.get("userid"));
			userdetails.setUserid(map.get("userid"));
			userDetailsOBJ.setUserDetails(SettingsList);
			userDetailsOBJ.setTokenkey(tokenkey);
			log.info("userDetailsOBJ"+userDetailsOBJ);
		}
		
		//userDetails
		
		
		//userDetailsOBJ = HERO_TOKENS.get(tokenkey);
		//log.info("HERO_TOKENS.get(tokenkey)"+HERO_TOKENS.get(tokenkey));
		//List<Object> userDetails = userDetailsOBJ.getUserDetails() ;
		//logger.info("userdetails   "+userDetails);
		//responseObj.setResponse(userDetailsOBJ);
		
		log.info("userDetailsOBJ"+userDetailsOBJ.getTokenkey());
		}catch(NullPointerException e){
			userDetailsOBJ.setTokenkey(null);
			//responseObj.setResponse(userDetailsOBJ);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.error(e);
	}
	return userDetailsOBJ;
}


}
