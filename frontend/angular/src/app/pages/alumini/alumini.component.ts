import { Component, OnInit ,ViewChild, TemplateRef } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { MatDialog,MatDialogRef } from "@angular/material";
import { CONFIG } from '../../config';
import { interval } from 'rxjs';
@Component({
  selector: 'app-alumini',
  templateUrl: './alumini.component.html',
  styleUrls: ['./alumini.component.scss']
})
export class AluminiComponent implements OnInit {
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  recentpost: {};
  newpost: any;
  headline: any;
  content1: any;
  image: any;
  date: any;
  blogid: any;
  public loading:boolean;
  public divshow:boolean;

  constructor(private server: HyperService, private masterservice:MasterService, private router: Router, 
    private dialog: MatDialog)  {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
   }
   data = [];
   page = 0;
   size = 6;
   public List: any; 
   public myList: any;
   public responseType : string;
   public responseValue : {};
   public image_URL: string = CONFIG._imageurl;

  ngOnInit() {
    this.loading =true;
    this.divshow = true;

    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
        this.getblogList();  
        this.getRecentBlogPost();  
        logged.unsubscribe();
        } 
    });
  }

  
  public getblogList(){

    this.server.get("onlineexamine/forms/getalumniblogList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      this.getData({pageIndex: this.page, pageSize: this.size});
      this.myList =  this.List[0];        
    });
  }   

  public getRecentBlogPost(){ 

    this.server.get("onlineexamine/forms/getRecentBlogPost")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.recentpost = this.responseValue;
      this.newpost =  this.recentpost[0];
      console.log(this.responseType)
      if( this.responseType == "S" ){
        this.headline =  this.recentpost[0]["headline"]; 
        this.content1 =  this.recentpost[0]["content"];
        this.image =  this.recentpost[0]["image"]; 
        this.date =  this.recentpost[0]["date"]; 
        this.blogid =  this.recentpost[0]["blogid"];  
      }
  
      this.loading =false;
      this.divshow = false; 
    });
  }  

  getdetails(blogid){
  LocalStorage.setValue("blogid",blogid);   
  this.router.navigateByUrl('/alumini-blog-details'); 
  }

  getData(obj) {
  
    let index=0,
        startingIndex=obj.pageIndex * obj.pageSize,
        endingIndex=startingIndex + obj.pageSize;
  
    this.data = this.List.filter(() => {
      index++;
      return (index > startingIndex && index <= endingIndex) ? true : false;
    });
  }

 
}

