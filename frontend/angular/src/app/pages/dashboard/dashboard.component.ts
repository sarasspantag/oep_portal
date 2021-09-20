import { Component, OnInit,ViewChild,TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { HyperService } from 'src/app/core/http.service';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { interval } from 'rxjs';
import { Internationalization } from '@syncfusion/ej2-base';
import { EventSettingsModel,MonthService ,PopupOpenEventArgs, MonthAgendaService  } from '@syncfusion/ej2-angular-schedule';
import {MatDialog,MatDialogRef } from "@angular/material";
@Component({
  selector: 'app-dashboard',
  providers: [MonthAgendaService],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
 
})
export class DashboardComponent implements OnInit {
  @ViewChild('secondDialog') secondDialog: TemplateRef<any>;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('testscheduleDialog') testscheduleDialog: TemplateRef<any>;

  public scheduleobject:any;  
  public selectedDate: Date = new Date();
  public data: object[] = [
  
];
  newtaskcount: any;
  resultannouncedcount: any;
  List: any;
  oprn: string;
  content: string;
  listid: any;
  modaltitle: string;
  msgcolor: string;
  modalmsg: any;
  registerobject: { "userid": any; "listid": any; "content": any; "oprn": any; };
  ReviewList: any;
  todoList: any;
  errormsg: string;
  testdate: any;
  starttesttime: any;
  endtesttime: any;
  constructor(private server: HyperService, private masterservice: MasterService,
    private router: Router,private dialog: MatDialog) {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }

      this.server.get("onlineexamine/forms/getloadscheduleevents/"+LocalStorage.getValue("roleid")+"/"+LocalStorage.getValue("userid"))
      .then((data) => { 
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);     
       
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
   public showrgisterbtn: boolean;
   public showLoader : boolean;
   public showContent : boolean;
   public hidereturn:boolean;
   public hidecancel:boolean;
   public ratethisproduct :boolean;
   public orderid : string;
   public customerid : string;
   public productid : string;
   menuurl : string;
   public responseType : any;
   public length : any;
   public responseValue : any;
   public menuList = [];
   public orderamountList = [];
   public customerList = [];
   public currency_code : string;
   public eventslist = [];
   scheduleevent:any;
   description:any;
   duration:any;
   paticipants:any;
   seatsavailable:any;
   time:any;
   cancelledcount:any;
   completedcount:any;
   scheduledcount:any;
   participantcount:any;
   isadmin:boolean;
   isfaculty:boolean;
   isparticpant:boolean;
   regshow:boolean;
   isReadOnly:boolean=false;
   showWeekNumber:boolean=false;
   public ContentButton : boolean;
   public condition:boolean;
  ngOnInit() {
    this.ContentButton = false;
    this.condition = false;

    this.oprn = "INS";
    this.listid = "0";
    this.getTodoList();
    this.getRecentReviewList();
    const logged = interval(200).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {       
          logged.unsubscribe();
        } 
      } 
    );
if(LocalStorage.getValue("roleid") == 2){
  this.isfaculty = true;
  this.isparticpant=false;
  this.isadmin = false;
}else if(LocalStorage.getValue("roleid") == 4){
  this.isparticpant= true;
  this.isadmin = false;
  this.isfaculty = false;
}else{
  this.isadmin = true;
  this.isfaculty = false;
  this.isparticpant = false;
} 
    
    this.getdashboarddetails();

  }
  public getdashboarddetails(){

    this.server.get("onlineexamine/forms/getdashboarddetails")
    .then((data) => { 

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);     
     this.cancelledcount=this.responseValue["cancelltestList"][0]["cancelled"]
     this.completedcount=this.responseValue["completedtestList"][0]["completed"]
     this.scheduledcount=this.responseValue["scheduledcourseList"][0]["scheduled"]
     this.participantcount=this.responseValue["newparticipantList"][0]["participant"]
     this.newtaskcount=this.responseValue["newtaskList"][0]["newtaskcount"];   
     this.resultannouncedcount=this.responseValue["resultannouncedList"][0]["result"];   
    });    
  }
  public eventData: EventSettingsModel = {  dataSource: this.data }  


  onPopupOpen(args: PopupOpenEventArgs): void { 

    if (args.type === 'QuickInfo' || args.type === 'ViewEventInfo') {
    if(args.data["scheduletype"] == 'test'){
      this.dialog.open(this.testscheduleDialog);
    }else{
    LocalStorage.setValue('scheduleid', args.data["Id"]);
    console.log(args.data["seatsavailable"] )
   
    console.log(this.regshow)
    if(args.data["Id"] > 0){
      this.server.get("onlineexamine/forms/checkcourseregisterparticipants/"+LocalStorage.getValue('scheduleid') +"/"+LocalStorage.getValue('userid'))
      .then((data) => { 
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data); 
        if(this.responseValue[0]["checkcount"] == '0'){
          this.regshow = true;
        }else{
          this.regshow = false;
        }
        console.log( this.responseValue[0]["checkcount"])
        if(args.data["seatsavailable"] == '0'){
          console.log("welcome")
          this.regshow = false;
        }else{ 
          this.regshow = true;
        }
      }); 
      const dialogManualLogin = this.dialog.open(this.secondDialog);
      dialogManualLogin.afterClosed().subscribe(result => {
        // console.log(`Dialog result: ${result}`);
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

      save(){     
        this.errormsg="";
        if(this.content == null || this.content.length == 0 && this.oprn=="INS"){
          this.errormsg="Invalid data";
          }  
      else{
        this.registerobject={"userid":LocalStorage.getValue("userid"),"listid":this.listid,
        "content":this.content,"oprn":this.oprn}
        console.log(this.registerobject)

        this.server.post( "onlineexamine/forms/savetodolist", this.registerobject, false)
        .then((data) => {
          if(data.status==200){
            this.responseType = this.masterservice.getResponseType(data);
              this.responseValue = this.masterservice.getResponseValue(data);
             console.log( this.responseValue)
               const dialogManualLogin = this.dialog.open(this.alertDialog);
               this.modaltitle="Success";
               this.msgcolor = "green"
               this.modalmsg =this.responseValue["out_result_msg"];
               this.clearfields();
          }  
            
        }); 
      }  
    }
 
    set(id){
      this.listid=id;
      console.log(id)
    }
    delete(){
      this.oprn="DEL";
      this.server.get("onlineexamine/forms/getTodoListdetails/"+this.listid)
      .then((data) => { 
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);     
        this.List=this.responseValue[0];       
        this.listid= this.List["id"];
        this.content= this.List["content"];
        this.modaltitle="Success";
        this.msgcolor = "green"
        this.modalmsg ="Do you want to delete ?";
        const dialogManualLogin = this.dialog.open(this.deleteDialog);
        console.log(this.List)
      });    
    
      //this.save();
    }

    closemodal(){
      this.clearfields();  
      this.dialog.closeAll();
    }
    clearfields(){
      this.content = "";      
      this.oprn = "INS"; 
      this.getTodoList();
    }

    public getTodoList(){

      this.server.get("onlineexamine/forms/getTodoList/"+LocalStorage.getValue("userid"))
      .then((data) => { 
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);     
        this.todoList=this.responseValue;
        console.log(this.responseValue)
      });    
    }


    public getRecentReviewList(){

      this.server.get("onlineexamine/forms/getRecentReviewList")
      .then((data) => { 
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);     
        this.ReviewList = this.responseValue;
        console.log( this.responseValue)
      });    
    }
   readmore(){
      this.condition = true;
      this.ContentButton = false;
     }  
    
    }
    