import { Component, OnInit } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MatDialog } from '@angular/material';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-training-courses',
  templateUrl: './training-courses.component.html',
  styleUrls: ['./training-courses.component.scss']
})
export class TrainingCoursesComponent implements OnInit {
  scheduleList: any;
  logged_session: any;

  constructor(private server: HyperService, private masterservice:MasterService, private router: Router, 
    private dialog: MatDialog)  {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }

   }

   color = 'primary';
   checked = false;
   status1: any;
   coursename: any;
   duration: any;
   applicableic: any;
   courseid: any;
   id: any;
   coursedesc: any;
   msgcolor: string;
   msg: {};
   roleid: string;
   status: any; 
   userid: string;
   oprn: string;
   token: string;
   oprnstatus: boolean ; 
   public List: any; 
   public myList: any;
   public responseType : string;
   public responseValue : {};
   public modaltitle : string;
   public modalmsg : string;

  ngOnInit() {
    this.getCourseList();
  }

  public getCourseList(){

    this.server.get("onlineexamine/forms/getTrainingcourseList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      //this.List = this.responseValue;       
      this.List = this.responseValue["courseList"];
      this.scheduleList = this.responseValue["scheduleList"];  
      console.log(this.List);
    });
  }   
  
  courseregister(scheduleid,coursename){
								
    this.logged_session = LocalStorage.getValue("userid");
    if(typeof this.logged_session != 'undefined' && this.logged_session != null && this.logged_session > 0){
    if( LocalStorage.getValue("roleid") == 2){

         LocalStorage.setValue("scheduleid",scheduleid);
         LocalStorage.setValue("coursename",coursename);
         this.router.navigateByUrl('/oeep/course_registration?schid='+LocalStorage.getValue("scheduleid")+'&crsname='+LocalStorage.getValue("coursename"));

      }else if(LocalStorage.getValue("roleid") == 4){
      LocalStorage.setValue("scheduleid",scheduleid); 
        LocalStorage.setValue("coursename",coursename);
        this.router.navigateByUrl('/oeep/course_registration?schid='+LocalStorage.getValue("scheduleid")+'&crsname='+LocalStorage.getValue("coursename"));

      }else if(LocalStorage.getValue("roleid") == 1){
      LocalStorage.setValue("scheduleid",scheduleid);
        LocalStorage.setValue("coursename",coursename);
        this.router.navigateByUrl('/oeep/course_registration?schid='+LocalStorage.getValue("scheduleid")+'&crsname='+LocalStorage.getValue("coursename"));

      }else if(LocalStorage.getValue("roleid") == 3){
      LocalStorage.setValue("scheduleid",scheduleid);
        LocalStorage.setValue("coursename",coursename);
        this.router.navigateByUrl('/oeep/course_registration?schid='+LocalStorage.getValue("scheduleid")+'&crsname='+LocalStorage.getValue("coursename"));

      }else {
      this.router.navigateByUrl('/myhome');
      }
    }else{
    this.router.navigateByUrl('/login');
    }
  }

} 
