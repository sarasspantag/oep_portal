import { Component, OnInit,TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MatDialog } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { interval } from 'rxjs';

@Component({
  selector: 'app-aluminiblogdetails',
  templateUrl: './aluminiblogdetails.component.html',
  styleUrls: ['./aluminiblogdetails.component.scss']
})
export class AluminiblogdetailsComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  yt = '<iframe class="w-100" src="https://www.youtube.com/embed/KS76EghdCcY?rel=0&amp;controls=0" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>';

  editable = true;

  @ViewChild('myDiv') myDiv: ElementRef;

  content = `<button  (click)="onClick()">  This is a Clickable span  </button>`;

  recentpost: {};
  newpost: any;
  headline: any;
  content1: any;
  status: any;
  date: any;
  blogid: any;
  comment:any;
  blogdata:any;
  allcomments=[];
  userlist =[];
  showreply=-1;
  replycontents:any;
  image: any;
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
   public shouldShow : boolean;

   @ViewChild("myLabel") lab;
   
   ngOnInit() {   
    this.loading =true;
    this.divshow = true;
    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
        this.getRecentBlogPost();  
        this.getblogdtls();
        this.getblogcomments();
        this.renderer.setAttribute(this.myDiv.nativeElement, 'innerHTML', this.content);
        logged.unsubscribe();
        } 
    });
  } 

  
  public getRecentBlogPost(){ 

    this.server.get("onlineexamine/forms/getRecentBlogPost")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.recentpost = this.responseValue;
      this.newpost =  this.recentpost[0]; 
      this.loading =false;
      this.divshow = false;  
    });
  }  
  public getblogcomments(){ 
   
    this.server.get("onlineexamine/forms/getblogcomments/"+ LocalStorage.getValue("blogid"))
    .then((data) => {
      if (data.status == 200) {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.allcomments = this.responseValue["commentsList"];
      if(this.allcomments["length"]==0){
        this.shouldShow=false;
      }
      else{
        this.shouldShow=true;
      }
      this.likescount =this.responseValue["likecountList"];
      this.replylist =this.responseValue["replyList"];
     console.log(this.allcomments)
      }
    });
  }  

  getblogdtls(){
    this.server.get("onlineexamine/forms/getalumniblogdetails/"+LocalStorage.getValue("blogid"))
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
  }

  onClick() {
     alert("clicked!!!!");
  }  

  handleButtonClick() {
    console.log('Button Cliked!'); 
  }
  savebloglike(commentid){
    console.log(commentid)
    
    if(LocalStorage.getValue("userid") > 0){
      this.blogdata={"commentid":commentid,"oprn":"INS","userid":LocalStorage.getValue("userid")}
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
          this.getblogcomments();
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
  saveblogcomments(){
    if(LocalStorage.getValue("userid") > 0){
    this.blogdata={"blogid":LocalStorage.getValue("blogid"),"comments":this.comment,"oprn":"INS","userid":LocalStorage.getValue("userid")}
    this.server.post("onlineexamine/forms/saveblogcomments",this.blogdata,false)
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
        this.getblogcomments();
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
  showblogreply(index,commentid){
    this.showreply=index;
  }
  saveblogreply(index,commentid){
  
    if(LocalStorage.getValue("userid") > 0){
      this.blogdata={"commentid":commentid,"oprn":"INS","userid":LocalStorage.getValue("userid"),"reply":this.replycontents}
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
          //this.likescount =this.responseValue["out_genrate_id"];
          const dialogManualLogin = this.dialog.open(this.alertDialog);
          this.replycontents = "";
          this.getblogcomments();
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
    this.showreply=-1;
  }
  getdetails(blogid){
    LocalStorage.setValue("blogid",blogid);
    this.server.get("onlineexamine/forms/getalumniblogdetails/"+blogid)
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
      this.getblogcomments();
    });

  }
  closemodal() {
    this.dialog.closeAll();
  }
  showOrHideManually() {
    this.shouldShow = !this.shouldShow;
    if(this.shouldShow) {
      this.lab.nativeElement.classList.add("show");
      this.lab.nativeElement.classList.remove("hide");
    } else {
      this.lab.nativeElement.classList.add("hide");
      this.lab.nativeElement.classList.remove("show");
    }
  }


}
