import { Component, OnInit ,ViewChild, TemplateRef,ViewEncapsulation } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { Router, ActivatedRoute, UrlSegment, NavigationEnd } from "@angular/router";
import { MasterService } from 'src/app/core/master.service';
import { Internationalization } from '@syncfusion/ej2-base';
import { ScheduleComponent,EventSettingsModel,MonthService,ActionEventArgs, ToolbarActionArgs ,PopupOpenEventArgs,MonthAgendaService,ResizeService,DayService, WeekService, WorkWeekService, AgendaService , DragAndDropService,View  } from '@syncfusion/ej2-angular-schedule';
import { ThemePalette } from '@angular/material';
import { CONFIG } from 'src/app/config';
import { interval } from 'rxjs';
import { ItemModel } from '@syncfusion/ej2-navigations';

import {
  MatCarouselSlideComponent,
  Orientation
} from '@ngmodule/material-carousel';
import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
@Component({
  selector: 'app-websitehome',
  providers: [MonthAgendaService,MonthService,ResizeService,DayService, WeekService, WorkWeekService, AgendaService , DragAndDropService],
  templateUrl: './websitehome.component.html',
  styleUrls: ['./websitehome.component.scss'] 
})
export class WebsitehomeComponent implements OnInit {
  @ViewChild('secondDialog') secondDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  @ViewChild('testscheduleDialog') testscheduleDialog: TemplateRef<any>;
  @ViewChild('scheduleObj')
  public scheduleObj: ScheduleComponent;

  public responseType : any;
  public responseValue : any;
  scheduleevent:any;
  public scheduleobject:any;
  public selectedDate: Date = new Date();
  public image_URL: string = CONFIG._imageurl;
  regshow:boolean;
  description:any;
  duration:any;
  paticipants:any;
  seatsavailable:any;
  time:any;
  public userid:any;
  public data: object[] = [
];
   public loading:boolean;
   public divshow:boolean;
   public showHeaderBar: boolean;

testimonialOptions: OwlOptions = {
  loop: true,
  mouseDrag: false,
  touchDrag: false,
  pullDrag: false,
  dots: false,
  navSpeed: 700,
  navText: ['', ''],
  responsive: {
    0: {
      items: 1
    },
    400: {
      items: 1
    },
    740: {
      items: 1
    },
    940: {
      items: 1
    },
    1200: {
      items: 1
    }
  },
  nav: true
}

latestnewssliderOptions: OwlOptions = {
  loop: true,
  mouseDrag: false,
  touchDrag: false,
  pullDrag: false,
  dots: false,
  navSpeed: 700,
  navText: ['', ''],
  responsive: {
    0: {
      items: 1
    },
    400: {
      items: 1
    },
    740: {
      items: 1
    },
    940: {
      items: 1
    },
    1200: {
      items: 1
    }
  },
  nav: true
}

ourfacultysliderOptions: OwlOptions = {
  loop: true,
  mouseDrag: false,
  touchDrag: false,
  pullDrag: false,
  dots: false,
  navSpeed: 700,
  navText: ['', ''],
  responsive: {
    0: {
      items: 1
    },
    400: {
      items: 2
    },
    740: {
      items: 3
    },
    940: {
      items: 3
    },
    1200: {
      items: 3
    }
  },
  nav: true
}

testimonialsliderOptions: OwlOptions = {
  loop: true,
  mouseDrag: false,
  touchDrag: false,
  pullDrag: false,
  dots: false,
  navSpeed: 700,
  navText: ['', ''],
  responsive: {
    0: {
      items: 1
    },
    400: {
      items: 1
    },
    740: {
      items: 2
    },
    940: {
      items: 2
    },
    1200: {
      items: 2
    }
  },
  nav: true
}
  multipleproductList:any;
  public isNotLogged:boolean;
  recentList: any;
  FacultyList: any;
  GalleryList: any;
  imagepath: any;
  public ContentButton : boolean;
  public condition:boolean;
  desc: any;
  roleid: number;
  testdate: any;
  starttesttime: any;
  endtesttime: any;
  constructor(private server: HyperService, private masterservice:MasterService, private router: Router, 
    private dialog: MatDialog)  {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
      this.getloadeventsdata();
   }
 public currentView: View = 'MonthAgenda';

   ngOnInit() {
    this.showHeaderBar = true;

    this.ContentButton = false;
    this.condition = false;
    this.loading =true;
    this.divshow = true;
    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
          // token Available
          
          this.getrecentnewsList();
          this.getFacultyList();
          this.getgalleryList();
          logged.unsubscribe();
        } 
    });


    
    this.isNotLogged = true;
   
  }
  public getloadeventsdata(){
    console.log("welcome")
    
  this.server.get("onlineexamine/forms/getloadscheduleevents/0/0")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
   
 //  console.log( this.responseValue)
 for(let i=0;i<this.responseValue.length;i++){
               
  this.scheduleobject = {
       
       Id:this.responseValue[i]["eventid"],Subject:this.responseValue[i]["eventname"],
       StartTime:this.responseValue[i]["startdate"],EndTime:this.responseValue[i]["enddate"],
       description:this.responseValue[i]["eventdesc"],duration:this.responseValue[i]["duration"],
       participants:this.responseValue[i]["totalparticipants"],seatsavailable:this.responseValue[i]["seatsavailable"],
       time:this.responseValue[i]["time"],
       scheduletype:this.responseValue[i]["scheduletype"],
       starttesttime:this.responseValue[i]["starttime"],endtesttime:this.responseValue[i]["endtime"],
       testdate:this.responseValue[i]["testdate"]
   }               
  this.data.push(this.scheduleobject)            
}    
  });
}

showview(imagepath,desc){
  this.imagepath=imagepath;
  this.desc=desc;
  const dialogManualLogin = this.dialog.open(this.viewDialog);
}
navigatefacultydetails(facultyid){
  LocalStorage.setValue("facultyprofileid",facultyid);
  this.router.navigateByUrl('/faculty-details');
}

 public getrecentnewsList(){

  this.server.get("onlineexamine/forms/getRecentNewsPost")
  .then((data) => { 

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.recentList = this.responseValue;
     });
}   

public getFacultyList(){

  this.server.get("onlineexamine/forms/getFacultyList")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.FacultyList = this.responseValue;
    console.log (this.FacultyList)
     });
} 


public getgalleryList(){

  this.server.get("onlineexamine/forms/getHomegalleryimage/" + "1")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.GalleryList = this.responseValue;
    console.log (this.GalleryList)
    this.loading =false;
    this.divshow = false;
     });
} 

 closemodal(){
   
  this.dialog.closeAll();
 }
 public eventData: EventSettingsModel = { 

  dataSource: this.data
}

onPopupOpen(args: PopupOpenEventArgs): void { 

  if (args.type === 'QuickInfo' || args.type === 'ViewEventInfo') {

  if(args.data["scheduletype"] == 'test'){
      this.dialog.open(this.testscheduleDialog);
  }else{

  LocalStorage.setValue('scheduleid', args.data["Id"]);
  console.log(args.data["seatsavailable"] )
 
  console.log(this.regshow);

  if(LocalStorage.getValue('userid') != null && typeof LocalStorage.getValue('userid') != undefined){
      this.userid = LocalStorage.getValue('userid');
  }else{
    this.userid = 0;
  }

  if(args.data["Id"] > 0){
    this.server.get("onlineexamine/forms/checkcourseregisterparticipants/"+LocalStorage.getValue('scheduleid') +"/"+this.userid)
    .then((data) => { 

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data); 
      if(this.responseValue[0]["checkcount"] == '0'){
        if(this.userid > 0){
          this.regshow = true;
        }else{
          this.regshow = false;
        }
      }else{
        this.regshow = false;
      }
      console.log( this.responseValue[0]["checkcount"])
      if(args.data["seatsavailable"] == '0'){
        console.log("welcome")
        this.regshow = false;
      }else{ 
        if(this.userid > 0){
          this.regshow = true;
        }else{
          this.regshow = false;
        }
      }
    }); 
  const dialogManualLogin = this.dialog.open(this.secondDialog);
  dialogManualLogin.afterClosed().subscribe(result => {
    this.condition = false;
    this.ContentButton = false;
  });
  }
  }
  LocalStorage.setValue("coursename",args.data["Subject"]); 
  this.scheduleevent=args.data["Subject"]
  this.description = args.data["description"]
  this.duration = args.data["duration"]
  this.paticipants = args.data["participants"]
  this.seatsavailable = args.data["seatsavailable"]
  this.time =  args.data["time"]
  this.testdate = args.data["testdate"]
  this.starttesttime = args.data["starttesttime"]
  this.endtesttime = args.data["endtesttime"]
  const div = new String(this.description);
  if(div.length > 100)
  {
  this.ContentButton = true;
  }
}
}

courseregister(){
  this.dialog.closeAll();
  this.router.navigateByUrl('/oeep/course_registration?schid='+LocalStorage.getValue("scheduleid")+'&crsname='+LocalStorage.getValue("coursename"));
  }
  readmore(){
    this.condition = true;
    this.ContentButton = false;
   }  

   getdetails(blogid){
    LocalStorage.setValue("newsblogid",blogid);
    this.router.navigateByUrl('/news-details'); 
  }  

  


}
