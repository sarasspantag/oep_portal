package com.oep.services.admin.util;

public class OEP_ADM_SERVC_REQUEST {

	private String userid;
	private String username;
	private String password;
	private int Applntype;
	private String confirmPassword;
	private String oprn;
	

	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getOprn() {
		return oprn;
	}
	public void setOprn(String oprn) {
		this.oprn = oprn;
	}
	public int getApplntype() {
		return Applntype;
	}
	public void setApplntype(int applntype) {
		Applntype = applntype;
	}
	
}
