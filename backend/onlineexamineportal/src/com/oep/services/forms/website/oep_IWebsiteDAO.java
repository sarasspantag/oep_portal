package com.oep.services.forms.website;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;

public interface oep_IWebsiteDAO {	

	public oep_ResponseInfo savealumniblogdetails(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo savecontactus(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo savetodolist(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo saveNewsandeventdetails(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo signup(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo getNewsandeventdetails(String id);	
	public oep_ResponseInfo getMonthlyNewsdetails(String id,String year);	
	public oep_ResponseInfo getNewsandeventList();
	public oep_ResponseInfo getNewsandeventListwithStatus();
	public oep_ResponseInfo getalumniblogdetails(String id);	
	public oep_ResponseInfo getalumniblogList();
	public oep_ResponseInfo getTodoList(String userid);
	public oep_ResponseInfo getRecentReviewList();
	public oep_ResponseInfo getRecentBlogPost();
	public oep_ResponseInfo getRecentNewsPost();
	public oep_ResponseInfo getblogcomments(String blogid);
	public oep_ResponseInfo getnewsandeventsalldetails(String newsblogid);
	public oep_ResponseInfo getimagecategorydetails();
	public oep_ResponseInfo savecategoryimage(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo saveblogcomments(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo savenewseventscomments(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo saveblogreply(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo savebloglike(String uploadData,HttpServletRequest httpRequest);
	public oep_ResponseInfo getgalleryimage(String categoryid);	
	public oep_ResponseInfo getAllgalleryimage();	
	public oep_ResponseInfo getHomegalleryimage(String categoryid);	
	public oep_ResponseInfo getTodoListdetails(String categoryid);	
}
