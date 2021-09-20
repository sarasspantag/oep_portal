import { Component, OnInit, ViewChild, TemplateRef, Output, Input, EventEmitter } from '@angular/core';
import { MatDialog, MatDatepickerInputEvent,DateAdapter,MAT_DATE_FORMATS, MatDatepicker } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { Router } from '@angular/router';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { AppDateAdapter, APP_DATE_FORMATS} from 'src/app/core/dateformat';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Moment } from 'moment';
import * as moment from 'moment';
import { FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';

export interface Transaction {
  courseid: string; 
  coursename: string;
  coursedesc :String;
  action :String; 
}


@Component({
  selector: 'app-testschedule',
  templateUrl: './testschedule.component.html',
  styleUrls: ['./testschedule.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class TestscheduleComponent implements OnInit {

    @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
    @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
    @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  
    @Input() initDate: Date;
    @Output() newDatePickedEvent: EventEmitter <Date> = new EventEmitter();
    someDate: Date;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    dataSource = new MatTableDataSource<Transaction>();

    displayedColumns: string[] = ['profile', 'fromdate','todate','status','teststatus','action'];
    public transactions: Transaction[];
    testname: any;
    qbList: any;
    csList: {};
    tsid: any;
  starthour: any;
  startminute: any;
  startformat: any;
  durationhour: any;
  durationminute: any;
  participantstatusList: any;

  @ViewChild(MatDatepicker) picker: MatDatepicker<Moment>;
  TempDate : Date;
  isValidMoment: boolean = false;
  momTempDate: Moment;
  serializedDate = new FormControl();
  subid: any;
  currentdate: string;
  incharge: any;
  endformat: any;
  diff: number;
  checkminute: any;
  // enddat = new FormControl((new Date("2019-11-12")).toISOString());
  ngAfterViewInit(){
    this.picker._selectedChanged.subscribe(
      (newDate: Moment) => {
        this.isValidMoment = moment.isMoment(newDate);
        console.log(newDate);
        // console.log(newDate.format('YYYY-MM-DD'));
      },
      (error) => {
        throw Error(error);
      }
    );
  }
  isLoading = false;
  DaterForm : FormGroup;
  minFromDate= new Date();
  maxToDate = new Date().setDate(2);
    constructor(private server: HyperService, private masterservice:MasterService,
      private router: Router , private dialog: MatDialog,private datePipe: DatePipe,
      private fb:FormBuilder) { 
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
    } 
  
    subjectname: any;
    courseid: any;
    subjectid: any;
    duration: any;
    coursename: any;

    starttime: any;
    endtime: any;
    testdate: any;
    public modaltitle : string;
    public modalmsg : string;
    batch: any;
    startdate: any;
    public myList: any;
    public tsList: any;
    public tsdetailsList: any;
    public SubjectList: any; 
    userdata: { "tsid":any;"testid": any; "status": any;  "batch": any;"starttest": any;"testdate": any
    "userid": any; "oprn": any,  "starthour": any,"durationhour": any; "startminute": any,"durationminute": any;
    "startformat": any };
     facultyname: any;
    msgcolor: string;
    msg: {};
    testid: any;  
    status: any; 
    userid: string;
    totalparticipants: string; 
    token: string; 
    id: any; 
    oprn: string; 
    public List: any; 
    public subjectList: any; 

    public subjectdetailsList: any; 
    public responseType : string;
    public responseValue : {};
  
    color = 'primary';
    checked = false;
    status1: any;

  ngOnInit() {
    this.currentdate = this.datePipe.transform(new Date(), 'dd/MM/yyyy'); 
    console.log("indie init")
    this.getTestid();
    this.getQuestionBankList(); 
    //this.getTestscheduleList();
    //this.getScheduleList();
    this.oprn = "INS"; 
    //this.id= "0";

    if(LocalStorage.getValue("roleid") == "2")
    {
   this.getFacultyTestscheduleList();
    }else{
      this.getTestscheduleList();
    }

  }

  getTestid(){

    this.server.get("onlineexamine/forms/getIdformat/9/tsh")
    .then((data) => {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.testid = this.responseValue[0]["refno"];    
    }); 

  }

  startDatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.testdate = event.targetElement["value"];
  }

  
  getQuestionBankList(){
 
    this.server.get("onlineexamine/forms/getQBMListforSchedule/"+LocalStorage.getValue("userid")+"/"+LocalStorage.getValue("roleid"))
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.qbList = this.responseValue;
     
    }); 

  }  
 

  public getScheduleList(id){

    this.server.get("onlineexamine/forms/getSchListforTestSchedule/"+LocalStorage.getValue("roleid")+"/"+LocalStorage.getValue("userid")+"/"+
    id)
    .then((data) => {
  
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.csList = this.responseValue;
     
   
    
    });
  }  

  getTestscheduleList(){

    this.server.get("onlineexamine/forms/getallTestscheduleList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.tsList = this.responseValue;
      this.tsList = JSON.stringify(this.tsList);  
      this.transactions = JSON.parse(this.tsList); 
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
    }); 

  }

  getQuestionbankdetails(testid){ 
   
      this.server.get("onlineexamine/forms/getQuestionbankdetails/"+testid)
      .then((data) => {
        console.log
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);      
        this.subjectdetailsList = this.responseValue[0];
        
        //this.csList = this.responseValue["questionmasterList"];

        this.testid =  this.responseValue["questionmasterList"][0]["testid"];
      
       
        
      }); 
    }


    gettestscheduledtls(id){
  
      this.server.get("onlineexamine/forms/getTestscheduledetails/"+id)
      .then((data) => {
  
        this.responseType = this.masterservice.getResponseType(data); 
        this.responseValue = this.masterservice.getResponseValue(data);    
        this.tsdetailsList = this.responseValue[0];
         console.log(  this.tsdetailsList )
      
        this.testname =  this.tsdetailsList["testid"];
        this.serializedDate= new FormControl((new Date(this.tsdetailsList["newtestdate"])).toISOString());  
        this.testdate =  this.tsdetailsList["testdate"]; 
        this.startformat =  this.tsdetailsList["startformat"];
        this.starttime =  this.tsdetailsList["starthour"]+":"+this.tsdetailsList["startminute"]+""+  this.startformat;  
        this.testdate =  this.tsdetailsList["testdate"]; 
        this.testid =  this.tsdetailsList["testid"]; 
        this.id =  this.tsdetailsList["quesid"];
        this.subid =  this.tsdetailsList["subid"]; 
        this.starthour =  this.tsdetailsList["starthour"];  
        this.startminute =  this.tsdetailsList["startminute"];  
        this.durationhour =  this.tsdetailsList["endhour"] - this.tsdetailsList["starthour"];
        this.durationminute =  this.tsdetailsList["endminute"] - this.tsdetailsList["startminute"];
        this.startformat =  this.tsdetailsList["startformat"];
        this.endformat =  this.tsdetailsList["endformat"];

        console.log(  this.durationhour )
        console.log(  this.durationminute )
   
        
        if( this.durationhour < 0){
          this.durationhour =  this.tsdetailsList["endhour"] - this.tsdetailsList["starthour"]+12;
        }
        if( this.durationminute < 0){
        //  this.durationminute =  this.tsdetailsList["startminute"] - this.tsdetailsList["endminute"];
        this.durationminute = 60;
        this.durationminute =      this.durationminute  - this.tsdetailsList["startminute"];
        this.durationhour = this.durationhour - 1;
        }
        if( this.durationminute == "0"){
         
          this.durationminute = 60;
          this.durationminute =      this.durationminute  - this.tsdetailsList["startminute"];
          this.durationhour = this.durationhour - 1;
        }
        this.endtime = this.durationhour+":"+this.durationminute;  
        this.duration = this.durationhour+":"+this.durationminute;  
        this.tsid =  this.tsdetailsList["tsid"];
        this.batch =  this.tsdetailsList["batch"];     
        this.incharge =  this.tsdetailsList["facname"];         
        this.status1 =  this.tsdetailsList["status"];
  
        if(this.status1== "Active"){
         this.checked =true;
         this.status1="1";
        }else{
          this.checked=false;
          this.status1="0";
        }
        this.oprn = "UPD";
        this.getScheduleList(this.subid)
        document.getElementById("test_sch_ctr").scrollIntoView(); 
      }); 
    } 
 
  delete(id){
   
    this.server.get("onlineexamine/forms/getTestscheduledetails/"+id)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      
      this.List = this.responseValue[0];      
      //this.testid =  this.List["id"];    
		this.tsid =  this.List["id"]; 	  
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog); 
    
    });
  }

  public savetestschedule(){
    this.isLoading = true;
    if ( this.testdate == "undefined"  || this.testdate == null ) {  
      this.testdate = this.datePipe.transform(new Date(), 'dd/MM/yyyy');    
    }

    if ( this.starthour >  12) {  
      this.modalmsg = "Hour value must be less than 12 ";   
      this.isLoading = false; 
      this.cleardata();  					  
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
    }

    if ( this.startminute > 59) {  
      this.modalmsg = "Minute value must be less than 60 ";  
       this.isLoading = false;
       this.cleardata();
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
    }

    if ( this.durationminute > 59) {  
      this.modalmsg = "Minute value must be less than 60 ";  
      this.isLoading = false;	
      this.cleardata();				  
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
    }

    if ( this.durationhour > 3) {  
      this.modalmsg = "Duration  cannot  be  more than 3 hours ";    
       this.isLoading = false;
       this.cleardata();
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
  }
  

     this.userid = LocalStorage.getValue("userid");

        if(this.oprn == "INS"){
      this.userdata = {  "tsid": 0, "testid": this.id, "status":this.status1, "batch":this.batch, "starttest": "0",
      "starthour":this.starthour, "oprn": this.oprn ,"userid":this.userid,"durationhour":this.durationhour,
      "testdate":this.testdate,"startminute":this.startminute, "startformat":this.startformat,"durationminute":this.durationminute  }; 
     }else{
      this.userdata = { "tsid": this.tsid,"testid": this.id, "status":this.status1, "batch":this.batch, "starttest": "0",
      "starthour":this.starthour, "oprn": this.oprn ,"userid":this.userid,"durationhour":this.durationhour,
      "testdate":this.testdate,"startminute":this.startminute, "startformat":this.startformat,
      "durationminute":this.durationminute  };
         
     }
  console.log(  this.userdata ) 
  //return false;

     this.server.post( "onlineexamine/forms/savetestschedule", this.userdata, false)
     .then((data) => { 
       if(data.status==200){
        this.isLoading = false;
         this.responseType = this.masterservice.getResponseType(data);
           this.responseValue = this.masterservice.getResponseValue(data);
          
           if (this.responseType == 'F') {
            
             this.modalmsg = this.responseValue["out_result_msg"];
             this.cleardata();  
             const dialogManualLogin = this.dialog.open(this.alertDialog);
            }else(this.responseType == 'S')
            { 
           
               this.msgcolor ="green"
              this.cleardata();
              this.modaltitle="Success";
              if(this.oprn == "INS"){
                this.modalmsg = this.responseValue["out_result_msg"];
              }else if(this.oprn = "UPD"){
                this.modalmsg = this.responseValue["out_result_msg"];
              }else{
                this.modalmsg = this.responseValue["out_result_msg"];
              }
              const dialogManualLogin = this.dialog.open(this.alertDialog); 
            this.router.navigateByUrl('/oeep/test_schedule');
        
          }
       }  
        
     }); 
   
   }

   showview(id){

    this.server.get("onlineexamine/forms/getTestscheduledetails/"+id)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
    
      this.tsdetailsList = this.responseValue[0];
      this.participantstatusList = this.tsdetailsList["submenuList"];
      
      this.testname =  this.tsdetailsList["testname"];
      this.testdate =  this.tsdetailsList["testdate"];
      this.testid =  this.tsdetailsList["testid"]; 
      this.batch =  this.tsdetailsList["batch"];         
      this.status1 =  this.tsdetailsList["status"];
      this.starthour =  this.tsdetailsList["starthour"];  
      this.startformat =  this.tsdetailsList["startformat"];
      this.endformat =  this.tsdetailsList["endformat"];
      this.startminute =  this.tsdetailsList["startminute"];  
      this.durationhour =  this.tsdetailsList["endhour"];
      this.durationminute =  this.tsdetailsList["endminute"]; 

      this.checkminute = this.durationminute + this.startminute ;

      this.starttime =  this.tsdetailsList["starthour"]+":"+this.tsdetailsList["startminute"]+" "+  this.startformat;  
      if( this.durationhour < 0){
        this.durationhour =  this.tsdetailsList["endhour"] - this.tsdetailsList["starthour"]+12;
      }
      if( this.durationminute < 0){
        this.durationminute =  this.tsdetailsList["startminute"] - this.tsdetailsList["endminute"];
      }
      if( this.durationminute == "0"){
        this.durationminute = 60;
        this.durationhour = this.durationhour - 1;
      }
      this.endtime = this.durationhour+":"+this.durationminute; 

      if(this.durationhour == "0" || this.durationhour== null || this.durationhour == "undefined"){
        this.duration =this.durationminute - this.startminute  +" "+ "minutes";   
      }else{
        this.diff = this.durationminute - this.startminute  ;
        this.duration =  this.durationhour -this.starthour + "  hour"+ this.diff  +"  "+ " minutes";   
      }
     
      const dialogManualLogin = this.dialog.open(this.viewDialog);
   });
  }

   changed(){
    
    if(this.checked==true){
      this.status1="1";
    }else{
      this.status1="0";
    }
  
  }

 cleardata(){ 
    this.batch =  "";
    this.testid =  "";
    this.id =  "";
    this.testname =  "";
    this.testdate =  "";
    this.starttime =  "";
    this.endtime =  "";
    this.starthour =  "";
    this.startminute =  "";
    this.durationhour =  "";
    this.durationminute =  "";

    this.startformat =  "";
    this.duration =  "";
    this.endformat =  "";
    this.startminute =  "";
    this.durationhour =  "";
    this.durationminute =  "";

    this.checked =  false;
    this.oprn = "INS"; 
    this.startformat =  "";
    this.getTestscheduleList();
}

    closemodal(){
     
    this.cleardata();  
    this.dialog.closeAll();
    this.ngOnInit();
    }

  getFacultyTestscheduleList(){

  this.server.get("onlineexamine/forms/getFacultyTestscheduleList/"+LocalStorage.getValue("userid"))
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.tsList = this.responseValue;
    console.log( this.responseValue )
    this.tsList = JSON.stringify(this.tsList);  
    this.transactions = JSON.parse(this.tsList); 
    this.dataSource.data = this.transactions;
    this.dataSource.paginator = this.paginator;
  }); 

  }
  getScheduledetails(scheduleid){
    this.server.get("onlineexamine/forms/getScheduledetails/"+scheduleid)
    .then((data) => {     
  this.responseType = this.masterservice.getResponseType(data);
  this.responseValue = this.masterservice.getResponseValue(data);
  this.List = this.responseValue[0];

  this.incharge =  this.List["facultyname"];

 });
}

numberOnly(event): boolean {
  const charCode = (event.which) ? event.which : event.keyCode;
  if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    return false;
  }
  return true;

  }

}
