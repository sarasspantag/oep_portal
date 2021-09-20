import { Component, OnInit, ViewChild, TemplateRef, EventEmitter, Output, Input } from '@angular/core';
import { MatDialog, MatDatepickerInputEvent,DateAdapter,MAT_DATE_FORMATS } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { Router } from '@angular/router';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { NgModel } from '@angular/forms';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { AppDateAdapter, APP_DATE_FORMATS} from 'src/app/core/dateformat';

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String;
}


@Component({
  selector: 'app-testadministrator',
  templateUrl: './testadministrator.component.html',
  styleUrls: ['./testadministrator.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class TestadministratorComponent implements OnInit {

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
    username: any;
    selectedYears: any[];
    years: any[];
    teststarted: any;
    start = new Date();
  checktime: string;
  timediff: any;
  startedtime: any;
  checktest: boolean;
  check: any;
  participantstatusList: any;	
  isLoading = false;				 
  starthour: any;
  endhour: any;
  startmint: any;
  endmint: any;
  dhour: any;
  dmint: any;
  tsid: any;
  startformat: any;
  endformat: any;
  showmsg: boolean;
  errormsg: string;
    constructor(private server: HyperService, private masterservice:MasterService,
      private router: Router , private dialog: MatDialog) { 
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
    } 

   // subjectname: any;
    courseid: any;
  //  subjectid: any;
    duration: any;
    coursename: any;
    partid: any;
    starttime: any;
    endtime: any;
    testdate: any;
    public modaltitle : string;
    public modalmsg : string;
    batch: any;
    startdate: any;
    public myList: any;
    public csList: any;
    public partList: any;
    public tsList: any;
    public tsdetailsList: any;
    userdata: { "tsid":any; "testid": any; "status": any;  "batch": any;"starttest": any;"testdate": any
								"userid": any; "oprn": any,"starttime": any,"endtime": any,"participantid": any;
								"starthour": any,"durationhour": any; "startminute": any,"durationminute": any;
								"startformat": any};
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
    // public subjectList: any; 
    // public subjectdetailsList: any; 
    public responseType : string;
    public responseValue : {};
    public participantname:string;
    
     today = new Date();
     time = this.today.getHours() + ":" + this.today.getMinutes();
    color = 'primary';
    checked = false;
    status1: any;

  ngOnInit() {
    this.getQuestionBankList();  
    this.getTestscheduleList();
    this.getScheduleList();
    console.log(this.time) ;
    this.checktest=false;
  }

  equals(objOne, objTwo) {
   
    if (typeof objOne !== 'undefined' && typeof objTwo !== 'undefined') {
      return objOne.partid === objTwo.partid;
    }
  }
  
  
  selectAll(checkAll, select: NgModel, values) {

 
    if(checkAll){
      select.update.emit(values); 
    }
    else{
      select.update.emit([]);
    }
  
  }

  getQuestionBankList(){

    this.server.get("onlineexamine/forms/getQuestionBankList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.qbList = this.responseValue;
     
    }); 

  }  


  getTestscheduleList(){

    this.server.get("onlineexamine/forms/getTestscheduleList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      console.log(this.responseValue)
      this.tsList = this.responseValue;

      if(this.tsList.length == 0 || this.tsList == null || this.tsList==""|| this.tsList==undefined){
        console.log("-----------------")
        this.showmsg=true;
        this.errormsg = "Your test session empty";
      }else{
        console.log("else")
        this.showmsg=false;
        this.errormsg=""
      }
      // hide by saraswathy
      // this.startedtime = this.responseValue[0]["starttime"];
      // this.check = this.responseValue[0]["teststarted"];

      // if( this.check == "Not started"){
      //   this.checktest=false;
      //   console.log(this.checktest)
      //       }else{
      //         this.checktest=true;
      //         console.log(this.checktest)
      //       }
      // this.checktime = this.responseValue[0]["starthours"]+':'+this.responseValue[0]["startminutetime"];
      
      this.tsList = JSON.stringify(this.tsList);  
      this.transactions = JSON.parse(this.tsList); 
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
    }); 

  }

  public getScheduleList(){

    this.server.get("onlineexamine/forms/getScheduleList")
    .then((data) => {
  
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.csList = this.responseValue;     
    });
  }  

  getdata(csid){
   
    this.server.get("onlineexamine/forms/getParticipants/"+csid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.partList = this.responseValue;     
    
    }); 
  }

 

  delete(id){
   
    this.server.get("onlineexamine/forms/getTestadministratordetails/"+id)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      
      this.List = this.responseValue[0];      
      this.testid =  this.List["id"];     
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog);
    
    });
  }



  endDatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.testdate = event.targetElement["value"];
  
  }


  gettestscheduledtls(id){

    this.server.get("onlineexamine/forms/getTestscheduledetails/"+id)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.tsdetailsList = this.responseValue[0];
      console.log(  this.tsdetailsList )
      this.starttime =  this.tsdetailsList["starttime"];  
      this.endtime =  this.tsdetailsList["endtime"];
      this.testname =  this.tsdetailsList["testname"];
      this.testdate =  this.tsdetailsList["testdate"];
      this.testid =  this.tsdetailsList["testid"]; 
      this.id =  this.tsdetailsList["quesid"];  
      this.batch =  this.tsdetailsList["batch"];         
      this.status1 =  this.tsdetailsList["status"];
      this.teststarted =  this.tsdetailsList["teststarted"];
      this.tsid =  this.tsdetailsList["tsid"];
      this.getdata(this.batch);

      this.startformat =  this.tsdetailsList["startformat"];  
			this.endformat =  this.tsdetailsList["endformat"];
			this.starthour =  this.tsdetailsList["starthour"];  
			this.endhour =  this.tsdetailsList["endhour"];
		  this.startmint =  this.tsdetailsList["startminute"];  
			this.endmint =  this.tsdetailsList["endminute"];

      this.starthour =  this.tsdetailsList["starthour"];  
      this.endhour =  this.tsdetailsList["endhour"];
      this.startmint =  this.tsdetailsList["startminute"];  
      this.endmint =  this.tsdetailsList["endminute"];
     
      if(this.starthour > this.endhour){
      this.dhour =  this.tsdetailsList["starthour"]-this.tsdetailsList["endhour"];
      }else{
      this.dhour =  this.tsdetailsList["endhour"]-this.tsdetailsList["starthour"];
        }
      
        if(this.endmint == 0){
          this.endmint = 60;
          this.dhour =  this.dhour -1;
          this.dmint =    this.endmint -this.tsdetailsList["startminute"];
        }else if(this.startmint > this.endmint){
        this.dmint =  this.tsdetailsList["startminute"]-this.tsdetailsList["endminute"];
      }else{
        this.dmint =  this.tsdetailsList["endminute"]-this.tsdetailsList["startminute"];
      }

      if(this.dmint == "0"){
        this.duration  =  this.dhour+" hours";
      }else if(this.dhour == "0"){
        this.duration  =  this.dmint+" minutes";
      }else{
        this.duration  =  this.dhour+" hour" +":"+ this.dmint+" minutes";
      }
      
      if(this.status1== "Active"){
       this.checked =true;
       this.status1="1"
      }else{
        this.checked=false;
        this.status1="0"
      }
      this.oprn = "UPD";
      document.getElementById("login_ctr").scrollIntoView();
    }); 
  }

savetest(){
  this.timediff = (this.checktime + this.time);
  this.timediff=parseFloat(this.checktime) - parseFloat(this.time);
  //console.log(this.timediff)

  if(this.teststarted =="Test Started" ){
    this.modaltitle="Success";
    this.modalmsg = "Exam already started";
    const dialogManualLogin = this.dialog.open(this.alertDialog);
    return false;
  }
  if(this.selectedYears ==null ){
    this.modaltitle="Failure";
    this.modalmsg = "select participants";
    const dialogManualLogin = this.dialog.open(this.alertDialog);
    return false;
  }

  if(this.timediff>1){
    this.modaltitle="Failure";
    this.modalmsg = "You can start the exam before an hour";
    const dialogManualLogin = this.dialog.open(this.alertDialog);
    return false;
  }
  this.modaltitle="Success";
  this.modalmsg = "Do you want to start test ?";
  const dialogManualLogin = this.dialog.open(this.deleteDialog);
 
}
 
  
  public savetestadministrator(){
    this.isLoading = true;
   
    this.userid = LocalStorage.getValue("userid");
    this.userdata = { "tsid": this.tsid,"testid": this.id, "status":this.status1, "batch":this.batch, "starttest": "1","participantid":JSON.stringify(this.selectedYears),
											   "starttime":this.starttime, "oprn": this.oprn ,"userid":this.userid,"endtime":this.endtime,"starthour":this.starthour, 
											   "durationhour":this.dhour,"testdate":this.testdate,"startminute":this.startmint, "startformat":this.startformat,
												  "durationminute":this.dmint };
   console.log( this.userdata )
  
   
     this.server.post( "onlineexamine/forms/savetestadministrator", this.userdata, false)
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
            this.router.navigateByUrl('/oeep/testadministrator');
        
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
      console.log(this.tsdetailsList )
      this.starttime =  this.tsdetailsList["starttime"];  
      this.endtime =  this.tsdetailsList["endtime"];
      this.testname =  this.tsdetailsList["testname"];
      this.testdate =  this.tsdetailsList["testdate"];
      this.testid =  this.tsdetailsList["testid"]; 
      this.batch =  this.tsdetailsList["batch"];         
      this.status1 =  this.tsdetailsList["status"];
      this.teststarted =  this.tsdetailsList["teststarted"];
      this.participantname =  this.tsdetailsList["username"];
      this.tsid =  this.tsdetailsList["tsid"];
      this.batch =  this.tsdetailsList["batch"]; 
      console.log(this.teststarted);

      this.starthour =  this.tsdetailsList["starthour"];  
      this.endhour =  this.tsdetailsList["endhour"];
      this.startmint =  this.tsdetailsList["startminute"];  
      this.endmint =  this.tsdetailsList["endminute"];
     
      if(this.starthour > this.endhour){
      this.dhour =  this.tsdetailsList["starthour"]-this.tsdetailsList["endhour"];
      }else{
      this.dhour =  this.tsdetailsList["endhour"]-this.tsdetailsList["starthour"];
        }
      
        if(this.endmint == 0){
          this.endmint = 60;
          this.dhour =  this.dhour -1;
          this.dmint =    this.endmint -this.tsdetailsList["startminute"];
        }else if(this.startmint > this.endmint){
        this.dmint =  this.tsdetailsList["startminute"]-this.tsdetailsList["endminute"];
      }else{
        this.dmint =  this.tsdetailsList["endminute"]-this.tsdetailsList["startminute"];
      }

      if(this.dmint == "0"){
        this.duration  =  this.dhour+" hours";
      }else if(this.dhour == "0"){
        this.duration  =  this.dmint+" minutes";
      }else{
        this.duration  =  this.dhour+" hour" +":"+ this.dmint+" minutes";
      }


      if(this.teststarted =='Not started'){
           this.checktest=false;
          console.log(this.checktest)
              }else if(this.teststarted =='Test Started' && this.tsdetailsList["participantstart"] == "0"){
                this.checktest=false;
                console.log(this.checktest)
              }else if(this.teststarted =='Test Started' && this.tsdetailsList["participantstart"] > "0"){
                this.checktest=true;
              }else{

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
    this.testname =  "";
    this.testdate =  "";
    this.starttime =  "";
    this.selectedYears = [];
    this.endtime =  "";
    this.checked =  false;
    this.oprn = "UPD";
    this.getTestscheduleList();
}

    closemodal(){
     this.cleardata();  
     this.dialog.closeAll();
}

}
