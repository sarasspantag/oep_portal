package com.oep.controller.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.oep.services.admin.util.ConnectionUtil;
import com.oep.services.admin.util.oep_UTIL;


@WebServlet("/forms/OEP_ImageView")
public class OEP_ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	private static JdbcTemplate jdbcTemplate = ConnectionUtil.getSpringMYSQLDAO().getJdbcTemplate();   
	private static Logger log = Logger.getLogger(OEP_ImageView.class);

    public OEP_ImageView() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String applntype = request.getParameter("applntype");
		String requesttype = request.getParameter("requesttype");	
		String requesttype1 = request.getParameter("index");
		
		log.info(requesttype1);
		
		int intApplntype = 0, intRequesttype = 0;
		if(applntype != null)
		{
			intApplntype = Integer.parseInt(applntype);
		}
		if(requesttype != null)
		{
			intRequesttype = Integer.parseInt(requesttype);
		}	
		
		if(intApplntype > 0)
		{
		byte[] imageData;
		File file = null;	
		String contentType = this.getServletContext().getMimeType("logo.jpg");
  
        if(intApplntype == 2){
        	String imgname = request.getParameter("imgname");
        	
        	String selectQuery = " SELECT `hau_path` FROM `hero_admin_upload` WHERE `hau_appln_type` = "
					+ " '"+intApplntype+"' AND `hau_req_type` = '"+intRequesttype+"'";
			
			@SuppressWarnings("unchecked")
			List<Object> pathList = jdbcTemplate.query(selectQuery, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {					
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("path", rs.getString("hau_path"));					
					return map;
				}
			});
			
			String path ="";
			if(pathList != null && pathList.size() > 0){
				Map<String, Object> map = (Map<String, Object>) pathList.get(0);
				path = (String) map.get("path");				
			}
        	
			
			filePath = request.getServletContext().getRealPath("");
			File dir = new File(filePath);
			dir = dir.getParentFile();
			filePath = dir.getAbsolutePath()+File.separator+path+File.separator+imgname+".jpg";
			file = new File(filePath);
			
			if (!file.exists()) {				
				
				if(intRequesttype == 1)	{	
	        	filePath = request.getServletContext().getRealPath("")+File.separator+"resources"+File.separator+"images"+File.separator+"logos"+File.separator+"defaultpatient.png";
	        	file = new File(filePath);	
				}else if(intRequesttype == 2){
					filePath = request.getServletContext().getRealPath("")+File.separator+"resources"+File.separator+"images"+File.separator+"logos"+File.separator+"defaultphysician.png";
		        	file = new File(filePath);	
				}	        	
	        }		
        }
     
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    	byte[] bytes = new byte[in.available()];
    	in.read(bytes);
    	in.close();
    	response.getOutputStream().write(bytes);
		}
		else
		{
			int intindex = 0;
			String index = request.getParameter("index");
			if(index != null)
			{
				intindex = Integer.parseInt(index);
			}
			
			filePath = request.getServletContext().getRealPath("");
			
			File dir = new File(filePath);		
			
			if(intindex == 0){
				filePath = dir.getAbsolutePath()+File.separator+"resources"+File.separator+"images"+File.separator+"certificate_bg.png";
			}else if(intindex == 1) {
				filePath = dir.getAbsolutePath()+File.separator+"resources"+File.separator+"images"+File.separator+"presign.png";
			}else if(intindex == 2) {
				filePath = dir.getAbsolutePath()+File.separator+"resources"+File.separator+"images"+File.separator+"orgsign.png";
			}else{
				filePath = dir.getAbsolutePath()+File.separator+"resources"+File.separator+"images"+File.separator+"lt.png";
			}		
			
			File file = new File(filePath);			
			
			if (!file.exists()) {  
				
				log.info("inside");
				filePath = filePath;			
				file = new File(filePath);
				
	        }
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        	byte[] bytes = new byte[in.available()];
        	in.read(bytes);
        	in.close();
        	response.getOutputStream().write(bytes);
        
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
