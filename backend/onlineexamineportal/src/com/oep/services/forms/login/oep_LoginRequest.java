package com.oep.services.forms.login;

public class oep_LoginRequest {

	private String username;
	private String password;
	private String oprn;
	private String appic;
	private String name;
	private String tempid;
	private String url; 
	
	
	public String getTempid() {
		return tempid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTempid(String tempid) {
		this.tempid = tempid;
	}
	private String content;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	private String subject;
	
	public String getAppic() {
		return appic;
	}
	public void setAppic(String appic) {
		this.appic = appic;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTotalparticipants() {
		return totalparticipants;
	}
	public void setTotalparticipants(String totalparticipants) {
		this.totalparticipants = totalparticipants;
	}
	private String userid;
	private String confirmpassword;
	private String role;
	private String email;
	private String status;
	
	private String foldername;
	private String sessionname;
	private String id;
	private String form;
	private String type;
	private String file;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getSessionname() {
		return sessionname;
	}
	public void setSessionname(String sessionname) {
		this.sessionname = sessionname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String totalparticipants;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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
	public String getOprn() {
		return oprn;
	}
	public void setOprn(String oprn) {
		this.oprn = oprn;
	}
}
