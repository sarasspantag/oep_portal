package com.oep.controller.view.services;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.oep.services.admin.util.oep_IDAO;
import com.oep.services.forms.website.oep_IWebsiteDAO;
import com.oep.services.forms.website.oep_WebsiteHelper;

@Controller
@SessionAttributes( {"uid"})
public class WebsiteServiceCtrl extends oep_IDAO {

private static Logger log = Logger.getLogger(WebsiteServiceCtrl.class);
	@Autowired
	private oep_WebsiteHelper websiteHelperOBJ;
	@Autowired
	private oep_IWebsiteDAO websiteDAOOBJ;
	
	
	@RequestMapping(value = "/savealumniblogdetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savealumniblogdetails(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savealumniblogdetails(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value = "/savecontactus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savecontactus(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savecontactus(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	
	@RequestMapping(value = "/saveNewsandeventdetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveNewsandeventdetails(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.saveNewsandeventdetails(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> signup(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.signup(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	
	@RequestMapping(value="/getalumniblogdetails/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getalumniblogdetails(@PathVariable("id")String id)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getalumniblogdetails(websiteDAOOBJ,id);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getalumniblogList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getalumniblogList()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getalumniblogList(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getNewsandeventdetails/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getNewsandeventdetails(@PathVariable("id")String id)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getNewsandeventdetails(websiteDAOOBJ,id);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMonthlyNewsdetails/{month}/{year}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getMonthlyNewsdetails(@PathVariable("month")String month,@PathVariable("year")String year)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getMonthlyNewsdetails(websiteDAOOBJ,month,year);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getNewsandeventList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getNewsandeventList()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getNewsandeventList(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getNewsandeventListiwithStatus",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getNewsandeventListiwithStatus()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getNewsandeventListiwithStatus(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRecentBlogPost",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getRecentBlogPost()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getRecentBlogPost(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRecentNewsPost",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getRecentNewsPost()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getRecentNewsPost(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getblogcomments/{blogid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getblogcomments(@PathVariable("blogid")String blogid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getblogcomments(blogid,websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getnewsandeventsalldetails/{newsblogid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getnewsandeventsalldetails(@PathVariable("newsblogid")String newsblogid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getnewsandeventsalldetails(newsblogid,websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getimagecategorydetails",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getimagecategorydetails()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getimagecategorydetails(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/saveblogcomments", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveblogcomments(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.saveblogcomments(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value = "/savenewseventscomments", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savenewseventscomments(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savenewseventscomments(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	

	@RequestMapping(value = "/saveblogreply", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveblogreply(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.saveblogreply(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	

	@RequestMapping(value = "/savebloglike", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savebloglike(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savebloglike(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value = "/savecategoryimage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savecategoryimage(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savecategoryimage(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value="/getgalleryimage/{categoryid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getgalleryimage(@PathVariable("categoryid")String categoryid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getgalleryimage(websiteDAOOBJ,categoryid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getAllgalleryimage",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllgalleryimage()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getAllgalleryimage(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getHomegalleryimage/{categoryid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getHomegalleryimage(@PathVariable("categoryid")String categoryid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getHomegalleryimage(websiteDAOOBJ,categoryid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savetodolist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savetodolist(@RequestBody String uploadData,HttpServletRequest httpRequest) 
	{
		 inventoryResponseInfoOBJ = websiteHelperOBJ.savetodolist(uploadData, websiteDAOOBJ,httpRequest);
		 return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(),HttpStatus.OK);
     }
	
	@RequestMapping(value="/getTodoList/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTodoList(@PathVariable("userid")String userid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getTodoList(websiteDAOOBJ,userid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTodoListdetails/{categoryid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTodoListdetails(@PathVariable("categoryid")String categoryid)
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getTodoListdetails(websiteDAOOBJ,categoryid);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRecentReviewList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getRecentReviewList()
	{
		inventoryResponseInfoOBJ = websiteHelperOBJ.getRecentReviewList(websiteDAOOBJ);
		return new ResponseEntity<Object>(inventoryResponseInfoOBJ, new HttpHeaders(), HttpStatus.OK);
	}
	
}
