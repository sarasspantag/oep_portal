package com.oep.services.forms.website;

import javax.servlet.http.HttpServletRequest;

import com.oep.services.admin.response.oep_ResponseInfo;
import com.oep.services.forms.course.oep_ICourseDAO;

public class oep_WebsiteHelper {
	
	public oep_ResponseInfo savealumniblogdetails(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savealumniblogdetails(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savecontactus(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savecontactus(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savetodolist(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savetodolist(uploadData,httpRequest);
     }
	
	
	public oep_ResponseInfo saveNewsandeventdetails(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.saveNewsandeventdetails(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo signup(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.signup(uploadData,httpRequest);
     }

	public oep_ResponseInfo getalumniblogdetails(oep_IWebsiteDAO websiteDAO,String id)
	{
		return websiteDAO.getalumniblogdetails(id);
	}
	
	public oep_ResponseInfo getalumniblogList(oep_IWebsiteDAO websiteDAO)
	{
		return websiteDAO.getalumniblogList();
	}
	
	public oep_ResponseInfo getNewsandeventdetails(oep_IWebsiteDAO websiteDAO,String id)
	{
		return websiteDAO.getNewsandeventdetails(id);
	}
	
	public oep_ResponseInfo getMonthlyNewsdetails(oep_IWebsiteDAO websiteDAO,String id,String year)
	{
		return websiteDAO.getMonthlyNewsdetails(id,year);
	}
	
	public oep_ResponseInfo getNewsandeventList(oep_IWebsiteDAO websiteDAO)
	{
		return websiteDAO.getNewsandeventList();
	}
	
	public oep_ResponseInfo getNewsandeventListiwithStatus(oep_IWebsiteDAO websiteDAO)
	{
		return websiteDAO.getNewsandeventListwithStatus();
	}
	
	public oep_ResponseInfo getRecentBlogPost(oep_IWebsiteDAO courseDAO)
	{
		return courseDAO.getRecentBlogPost();
	}
	
	public oep_ResponseInfo getRecentNewsPost(oep_IWebsiteDAO courseDAO)
	{
		return courseDAO.getRecentNewsPost();
	}
	
	public oep_ResponseInfo getblogcomments(String blogid,oep_IWebsiteDAO courseDAO)
	{
		return courseDAO.getblogcomments(blogid);
	}
	
	public oep_ResponseInfo getnewsandeventsalldetails(String newsblogid,oep_IWebsiteDAO courseDAO)
	{
		return courseDAO.getnewsandeventsalldetails(newsblogid);
	}
	
	public oep_ResponseInfo getimagecategorydetails(oep_IWebsiteDAO courseDAO)
	{
		return courseDAO.getimagecategorydetails();
	}
	
	public oep_ResponseInfo savecategoryimage(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savecategoryimage(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo saveblogcomments(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.saveblogcomments(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savenewseventscomments(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savenewseventscomments(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo saveblogreply(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.saveblogreply(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo savebloglike(String uploadData,oep_IWebsiteDAO websiteDAO, HttpServletRequest httpRequest) {
		return websiteDAO.savebloglike(uploadData,httpRequest);
     }
	
	public oep_ResponseInfo getgalleryimage(oep_IWebsiteDAO websiteDAO,String categoryid)
	{
		return websiteDAO.getgalleryimage(categoryid);
	}
	
	public oep_ResponseInfo getAllgalleryimage(oep_IWebsiteDAO websiteDAO )
	{
		return websiteDAO.getAllgalleryimage();
	}
	
	public oep_ResponseInfo getHomegalleryimage(oep_IWebsiteDAO websiteDAO,String categoryid)
	{
		return websiteDAO.getHomegalleryimage(categoryid);
	}
	
	public oep_ResponseInfo getTodoList(oep_IWebsiteDAO websiteDAO,String categoryid)
	{
		return websiteDAO.getTodoList(categoryid);
	}
	
	public oep_ResponseInfo getTodoListdetails(oep_IWebsiteDAO websiteDAO,String categoryid)
	{
		return websiteDAO.getTodoListdetails(categoryid);
	}
	
	public oep_ResponseInfo getRecentReviewList(oep_IWebsiteDAO websiteDAO)
	{
		return websiteDAO.getRecentReviewList();
	}
}
