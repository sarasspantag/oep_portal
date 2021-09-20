import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { Router } from '@angular/router';
import { ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import { CONFIG } from 'src/app/config';
import { interval } from 'rxjs';

@Component({
  selector: 'app-newsandevents', 
  templateUrl: './newsandevents.component.html',
  styleUrls: ['./newsandevents.component.scss']
})
export class NewsandeventsComponent implements OnInit {
  data = [];
  page = 0;
  size = 7;
  multipleproductList:any;
   List: any; 
  public myList: any;
  public responseType : string;
  public responseValue : {};
  public image_URL: string = CONFIG._imageurl;
  recentList: {};
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

  ngOnInit() { 
    this.loading =true;
    this.divshow = true;

    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
          this.getnewsList();
          this.getrecentnewsList();
          logged.unsubscribe();
        } 
    });
    
  }

  public getnewsList(){

    this.server.get("onlineexamine/forms/getNewsandeventListiwithStatus")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      console.log(this.List)
      this.getData({pageIndex: this.page, pageSize: this.size});
       });
  }   

  public getrecentnewsList(){

    this.server.get("onlineexamine/forms/getRecentNewsPost")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.recentList = this.responseValue;

      this.loading =false;
      this.divshow = false;
       });
  }   

  getdetails(blogid){
 
    LocalStorage.setValue("newsblogid",blogid);
    this.router.navigateByUrl('/news-details'); 
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

  get(month,year){
    this.server.get("onlineexamine/forms/getMonthlyNewsdetails/"+month+'/'+year)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue;      
      console.log(this.List )
      this.getData({pageIndex: this.page, pageSize: this.size});
    
    });
  }

}




