import { Component, OnInit ,TemplateRef} from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MatDialog } from '@angular/material'; 
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { ViewChild, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-newsevent-details',
  templateUrl: './newsevent-details.component.html',
  styleUrls: ['./newsevent-details.component.scss']
})
export class NewseventDetailsComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  //yt = '<iframe class="w-100" src="https://www.youtube.com/embed/KS76EghdCcY?rel=0&amp;controls=0" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>';

  editable = true;

  @ViewChild('myDiv') myDiv: ElementRef;

  // content = `<button  (click)="onClick()">  This is a Clickable span  </button>`;

  recentpost: {};
  newpost: any;
  headline: any;
  content1: any;
  status: any;
  date: any;
  blogid: any;
  image: any;
  public shouldShow : boolean;
  public loading:boolean;
  public divshow:boolean;

  constructor(private server: HyperService, private masterservice:MasterService,
   private router: Router,private dialog: MatDialog,private renderer: Renderer2){
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
   }

   public List: any; 
   public myList: any;
   public responseType : string;
   public responseValue : {};
   public image_URL: string = CONFIG._imageurl;

   msgcolor: string;
   public modaltitle: string;
   public modalmsg: string;
   likescount=[];
   replylist=[];
   newsblogid: any;
  comment:any;
  blogdata:any;
  allcomments=[];
  username:any;
  userimagepath:any;
  showreply=-1;
  replycontents:any;
   ngOnInit() {     
     
    this.loading =true;
    this.divshow = true;
    this.getRecentBlogPost();  
    this.getblogdtls();
  this.getnewsandeventsalldetails();
    this.renderer.setAttribute(this.myDiv.nativeElement, 'innerHTML', this.content1);
  } 

  getnewsandeventsalldetails(){
    this.server.get("onlineexamine/forms/getnewsandeventsalldetails/"+ LocalStorage.getValue("newsblogid"))
    .then((data) => {
      if (data.status == 200) {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
     console.log(this.responseValue);
     if(this.responseValue["commentsList"].length==0){
        this.shouldShow=false;
      }
      else{
        this.shouldShow=true;
      }
     this.allcomments = this.responseValue["commentsList"];
     this.likescount =this.responseValue["likecountList"];
     this.replylist =this.responseValue["replyList"];
      }
    });
  }
  public getRecentBlogPost(){ 

    this.server.get("onlineexamine/forms/getRecentNewsPost")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.recentpost = this.responseValue;
      this.newpost =  this.recentpost[0];  
      this.loading =false;
      this.divshow = false; 
    });
  }  

  getdetails(blogid){
    LocalStorage.setValue("newsblogid",blogid);
    this.server.get("onlineexamine/forms/getNewsandeventdetails/"+blogid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];      
      this.headline =  this.List["headline"];
      this.content1 =  this.List["content"];
      this.status =  this.List["status"]; 
      this.date =  this.List["date"]; 
      this.blogid =  this.List["blogid"];   
      this.image =  this.List["image"];
      document.getElementById("login_ctr").scrollIntoView(); 
      // this.yt = '<iframe class="w-100" src="'+this.image_URL+this.List["description"]+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>'; 
     //this.yt = '<iframe class="w-100" src="'+this.image_URL+this.List["description"]+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>';
    });
    this.getnewsandeventsalldetails();
    this.getRecentBlogPost();
  }

  getblogdtls(){
    this.server.get("onlineexamine/forms/getNewsandeventdetails/"+LocalStorage.getValue("newsblogid"))
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];      
      this.headline =  this.List["headline"];
      this.content1 =  this.List["content"];
      this.status =  this.List["status"]; 
      this.date =  this.List["date"]; 
      this.blogid =  this.List["blogid"];   
      this.image =  this.List["image"];
      document.getElementById("login_ctr").scrollIntoView(); 
      // this.yt = '<iframe class="w-100" src="'+this.image_URL+this.List["description"]+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>'; 
    // this.yt = '<iframe class="w-100" src="'+this.image_URL+this.List["description"]+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>';
   
    });
  }

  savenewseventscomments(){
    if(LocalStorage.getValue("userid") > 0){
      this.blogdata={"blogid":LocalStorage.getValue("newsblogid"),"comments":this.comment,"oprn":"NEWS_INS","userid":LocalStorage.getValue("userid")}
      console.log(this.blogdata)
      this.server.post("onlineexamine/forms/savenewseventscomments",this.blogdata,false)
      .then((data) => {
        if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);    
        console.log(this.responseValue)
        if (this.responseType == 'S') {
          this.msgcolor = "green"
          this.modaltitle = "Success";
          this.modalmsg = this.responseValue["out_result_msg"];
          const dialogManualLogin = this.dialog.open(this.alertDialog);
          this.comment = "";
          this.getnewsandeventsalldetails();
        }
        }
     
      });
    }else{
      this.msgcolor = "red"
      this.modaltitle = "Fail";
      this.modalmsg = "Please login";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      this.router.navigateByUrl('/login');
    }
  }


  savebloglike(commentid){
    console.log(commentid)
    
    if(LocalStorage.getValue("userid") > 0){
      this.blogdata={"commentid":commentid,"oprn":"NEWS_INS","userid":LocalStorage.getValue("userid")}
      this.server.post("onlineexamine/forms/savebloglike",this.blogdata,false)
      .then((data) => {
        if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);    
        console.log(this.responseValue)
        if (this.responseType == 'S') {
          this.msgcolor = "green"
          this.modaltitle = "Success";
          this.modalmsg = this.responseValue["out_result_msg"];
          //this.likescount =this.responseValue["out_genrate_id"];
          const dialogManualLogin = this.dialog.open(this.alertDialog);
          this.comment = "";
          this.getnewsandeventsalldetails();
        }
        }
     
      });

    }else{
      this.msgcolor = "red"
      this.modaltitle = "Fail";
      this.modalmsg = "Please login";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      this.router.navigateByUrl('/oeep/login');
    }
  }
  showblogreply(index,commentid){
    this.showreply=index;
  }
  saveblogreply(index,commentid){
  
    if(LocalStorage.getValue("userid") > 0){
      this.blogdata={"commentid":commentid,"oprn":"NEWS_INS","userid":LocalStorage.getValue("userid"),"reply":this.replycontents}
      this.server.post("onlineexamine/forms/saveblogreply",this.blogdata,false)
      .then((data) => {
        if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);    
        console.log(this.responseValue)
        if (this.responseType == 'S') {
          this.msgcolor = "green"
          this.modaltitle = "Success";
          this.modalmsg = this.responseValue["out_result_msg"];
        
          const dialogManualLogin = this.dialog.open(this.alertDialog);
          this.replycontents = "";
          this.getnewsandeventsalldetails();
        }
        }
     
      });

    }else{
      this.msgcolor = "red"
      this.modaltitle = "Fail";
      this.modalmsg = "Please login";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      this.router.navigateByUrl('/oeep/login');
    }
  }
  onClick() {
     alert("clicked!!!!");
  }  

  handleButtonClick() {
    console.log('Button Cliked!');
  }
  closemodal() {
    this.dialog.closeAll();
  }

}
