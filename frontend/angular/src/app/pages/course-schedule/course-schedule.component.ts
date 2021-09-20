import { Component, OnInit, ViewChild, TemplateRef, EventEmitter, Output, Input } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MasterService } from 'src/app/core/master.service';
import { MatDialog, MatDialogRef, DateAdapter, MAT_DATE_FORMATS} from "@angular/material";
import {
  MatDatepickerInputEvent
} from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS} from 'src/app/core/dateformat';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { MatDatepicker } from '@angular/material';
import { Moment } from 'moment';
import * as moment from 'moment';
import { FormControl } from '@angular/forms';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String; 
  action :String; 
}
export interface Applicable {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-course-schedule',
  templateUrl: './course-schedule.component.html',
  styleUrls: ['./course-schedule.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class CourseScheduleComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();

  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];

  @Input() initDate: Date;
  @Output() newDatePickedEvent: EventEmitter <Date> = new EventEmitter();
  @Output() newDatePickedEvent1: EventEmitter <Date> = new EventEmitter();
  someDate: Date;
  public myTableList :any;

  displayedColumns: string[] = ['profile', 'fromdate','todate','Incharge','status','register','action'];
  public transactions: Transaction[];
  coursedesc: any;
  starttime: any;
  username: any;
  course_schedule_Form: FormGroup;
  submitted:boolean = false;
  @ViewChild(MatDatepicker) picker: MatDatepicker<Moment>;
  TempDate : Date;
  isValidMoment: boolean = false;
  momTempDate: Moment;
  serializedDate = new FormControl();
  enddat = new FormControl();
  startformat: any;
  starthour: any;
  endformat: any;
  startminute: any;
  endminute: any;
  endhour: any;
  startyear: any;
  startmonth: any;
  startdays: any;
  endmonth: any;
  endyear: any;
  enddays: any;
  isyearequal:boolean;
  ismonthequal:boolean;
  isdaysequal:boolean;
  logged_session: any;

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
  
  DaterForm : FormGroup;
  minFromDate= new Date();
  maxToDate = new Date().setDate(2);
  constructor(private server: HyperService, private router: Router,private masterservice: MasterService,
    private dialog: MatDialog, private fb:FormBuilder,private formBuilder: FormBuilder)  {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT(); 
    } 
 }

 public List: any; 
 public myList: any;
 public CourseList: any;
 programname: any;
 duration: any; 
 applicableic: any; 
 oprn: string;
 csid: any;
 enddate: any;
 courseid: any;
 startdate: any;
 checkdate: any;
 schedule_data: { "programname": any; "facultyname": any;"totalparticipants": any; "appic": any,"userid": any,
  "startdate": any;"oprn": any,  "enddate": any,"startformat": any,"endformat": any;   "status": any,  "csid": any,
  "starthour": any,"endhour": any; "startminute": any,"endminute": any; };
  facultyname: any;
 msgcolor: string;
 msg: {};
 roleid: string;
 status: any;
 diffdate: any;
 facultyid:any;
 userid: string;
 totalparticipants: string;
 token: string;
 partname:any;
 public facultyList:any;
 public responseType : string;
 public responseValue : {};   
 public modaltitle : string;
 public modalmsg : string;
 public testdate = new Date();
 endtime:any;
 courseduration:any;
 color = 'primary';
 checked = false;
 status1: any;
 startdate1 : string;
 enddate1:string;  
 checkrole: boolean;
 public sarasdate ;
 public availableseats:any;
 isLoading = false;

ngOnInit() {
  
  this.getScheduleList();
  this.getCourseList();
  this.oprn = "INS";
  this.checkdate  = new Date();
  //console.log(this.checkdate)
  if( LocalStorage.getValue("roleid") == 4){
    this.checkrole= false;
    //console.log(this.checkrole)
  }else{
    this.checkrole= true;
    //console.log(this.checkrole)
  }
  //this.sarasdate.targetElement["value"] = "01/10/2019";

  this.course_schedule_Form = this.formBuilder.group({

    courseid: new FormControl('', Validators.compose([  Validators.required, ])),   
    /* startdate: new FormControl('', Validators.compose([ Validators.required, ])),
    enddate: new FormControl('', Validators.compose([   Validators.required, ])), */
    starthour:new FormControl('', Validators.compose([  Validators.required, ])),
    startminute: new FormControl('', Validators.compose([ Validators.required, ])),
    startformat: new FormControl('', Validators.compose([ Validators.required, ])),      
    endhour: new FormControl('', Validators.compose([ Validators.required, ])), 
    endminute:new FormControl('', Validators.compose([  Validators.required, ])),
    endformat: new FormControl('', Validators.compose([ Validators.required, ])),
    totalparticipants: new FormControl('', Validators.compose([ Validators.required,])),
   // ApplicableIc: new FormControl('', Validators.compose([ Validators.required, ])),
    facultyid:new FormControl('', Validators.compose([ Validators.required,]))

  });

} 


public getCourseList(){

  this.server.get("onlineexamine/forms/getCourseList")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.CourseList = this.responseValue;  
  });
}   


startDatePicked(event: MatDatepickerInputEvent <Date> ): void {
  console.log(event.value)
  //this.newDatePickedEvent.emit(event.value); 
  console.log(this.newDatePickedEvent);
  this.startdate = event.targetElement["value"];  
  console.log(this.startdate)
}

endDatePicked(event: MatDatepickerInputEvent <Date> ): void {
  this.newDatePickedEvent.emit(event.value); 
  this.enddate = event.targetElement["value"]; 
}

 showview(scheduleid){
      this.server.get("onlineexamine/forms/getScheduledetails/"+scheduleid)
      .then((data) => {
        //console.log(data)
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        this.List = this.responseValue[0];
      
        this.courseid =  this.List["programname"];
        this.coursedesc =  this.List["coursedesc"];
        this.startdate =  this.List["start_date"];
        this.enddate =  this.List["end_date"];
        //console.log(this.enddate)
        
		this.starthour =  this.List["starthour"];  
		this.endhour =  this.List["endhour"];
		this.startminute =  this.List["startminute"];  
		this.endminute =  this.List["endminute"];
    this.startformat =  this.List["startformat"];  
    this.endformat =  this.List["endformat"];	
    this.starttime =  this.List["starthour"]+":"+this.List["startminute"]+" "+ this.startformat ;  
    this.endtime =  this.List["endhour"]+":"+this.List["endminute"]+" "+  this.endformat ; 
        this.applicableic =  this.List["appic"];
        this.totalparticipants =  this.List["totalparticipants"];
        this.facultyname =  this.List["facultyname"];
        this.facultyid =  this.List["facultyid"];
        this.startdate1 =  this.List["date1"];
        this.enddate1 =  this.List["date2"];
        this.availableseats =  this.List["availableseats"];
        this.partname =  this.List["partname"];
        const dialogManualLogin = this.dialog.open(this.viewDialog);
   });
  }


public getScheduleList(){

  this.server.get("onlineexamine/forms/getScheduleList")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.List = this.responseValue;
    this.myList = JSON.stringify(this.List);  
    this.transactions = JSON.parse(this.myList); 
    //console.log(this.transactions)
    this.dataSource.data = this.transactions;
    this.dataSource.paginator = this.paginator;
  });
}  



changed(){

  if(this.checked==true){
    this.status1="1";
  }else{
    this.status1="0";
  }
}

public getFacultyList(courseid){

  this.server.get("onlineexamine/forms/getFacultyList/"+courseid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.facultyList = this.responseValue;
   
  });
}  

getScheduledetails(scheduleid){ 
   
  this.server.get("onlineexamine/forms/getScheduledetails/"+scheduleid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data); 
    this.List = this.responseValue[0];
    
     console.log(this.List)

    this.csid =  this.List["scheduleid"];
    this.courseid =  this.List["programname"];
    this.coursedesc =  this.List["coursedesc"];
    this.serializedDate= new FormControl((new Date(this.List["start_date"])).toISOString());
    this.startdate=this.List["start_date"];
    this.enddat= new FormControl((new Date(this.List["end_date"])).toISOString());
    this.enddate=this.List["end_date"];    		
    this.startformat =  this.List["startformat"];  
    this.endformat =  this.List["endformat"];	
    this.starttime =  this.List["starthour"]+":"+this.List["startminute"]+" "+ this.startformat ;  
    this.endtime =  this.List["endhour"]+":"+this.List["endminute"]+" "+  this.endformat ; 
    this.starthour =  this.List["starthour"];  
    this.endhour =  this.List["endhour"];
    this.startminute =  this.List["startminute"];  
    this.endminute =  this.List["endminute"];						   
    this.applicableic =  this.List["appic"];
    this.totalparticipants =  this.List["totalparticipants"];
    this.facultyname =  this.List["facultyname"];
    this.username =  this.List["username"];
    this.facultyid =  this.List["facultyid"];
    this.oprn = "UPD"; 
    this.getFacultyList(this.courseid);
    this.status1 =  this.List["status"];

      if(this.status1== "1"){ 
       this.checked =true;
      }else{
        this.checked=false;
      }
    document.getElementById("test_sch_ctr").scrollIntoView();  
  });
}

getcoursedetails(courseid){
   
  this.server.get("onlineexamine/forms/getCoursedetails/"+courseid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);  
    this.List = this.responseValue[0];  
    this.courseduration =  this.List["duration"];  
  });
}

delete(scheduleid){
 
  this.server.get("onlineexamine/forms/getScheduledetails/"+scheduleid)
  .then((data) => {
    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);  
    this.List = this.responseValue[0]; 
    this.csid =  this.List["scheduleid"];
    this.oprn = "DEL";
    this.modalmsg ="Do you want to delete ?";      
    const dialogManualLogin = this.dialog.open(this.deleteDialog);

  });
}

public savecourseschedule(){ 
  this.submitted = true;
 


  
  if(this.oprn != "DEL"){

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

      if ( this.endminute > 59) {  
      this.modalmsg = "Minute value must be less than 60 ";  
      this.isLoading = false;  
      this.cleardata();
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
      }

      if ( this.endhour > 12) {  
      this.modalmsg = "Hour value must be less than 12 "; 
      this.isLoading = false;
      this.cleardata();   
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      return false;
    }

  this.diffdate=Date.parse(this.enddate) - Date.parse(this.startdate);
  this.diffdate=(this.diffdate/86400000);

  let stringToSplit = this.startdate;
  let x = stringToSplit.split("/");
  this.startyear=x[2];
  this.startmonth=x[1];
  this.startdays=x[0];

  console.log(this.startdays);
  console.log(this.startmonth);
  console.log(this.startyear);

  let stringToSplit1 = this.enddate;
  let y = stringToSplit1.split("/");
  this.endyear=y[2];
  this.endmonth=y[1];
  this.enddays=y[0];

  console.log(this.enddays);
  console.log(this.endmonth);
  console.log(this.endyear);
console.log(parseInt(this.startyear) <= parseInt(this.endyear));


if(parseInt(this.startyear) <= parseInt(this.endyear)){
  if(parseInt(this.startyear) == parseInt(this.endyear)){
    this.isyearequal=true;
  }
  else{
    this.isyearequal=false;
  }
  if(this.isyearequal){
  if(this.startmonth <=  this.endmonth){
        if(this.startmonth == this.endmonth){
          this.ismonthequal=true;
        }
        else{
          this.ismonthequal=false;
          }
          if(this.ismonthequal){
          if(this.startdays <=  this.enddays){
            console.log(this.startdays +"    " + this.enddays)
            if(this.startdays == this.enddays){
              this.isLoading = false;
              this.modaltitle = "Info";
              this.modalmsg ="Start date and end date must not be same";      
                const dialogManualLogin = this.dialog.open(this.alertDialog);
                return false;
            }
          //  else{
          //    return true;
          //  }
              }else{
                this.isLoading = false;
                this.modaltitle = "Info";
                this.modalmsg ="Enter valid start date and end date";      
                const dialogManualLogin = this.dialog.open(this.alertDialog);
                return false;
              }
            }
            // else{
            //   return true;
            // }
          }else{
            this.isLoading = false;
            this.modaltitle = "Info";
            this.modalmsg ="Enter valid start date and end date";      
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            return false;
          }
        }
        //  else{
        //    return true
        //  } 
}
else{
  this.isLoading = false;
  this.modaltitle = "Info";
  this.modalmsg ="Enter valid start date and end date";      
  const dialogManualLogin = this.dialog.open(this.alertDialog);
  return false;
}
}

 console.log("saras");
  this.userid = LocalStorage.getValue("userid");
  if(this.course_schedule_Form.valid){
    this.isLoading = true;
  this.schedule_data = { "programname": this.courseid, "totalparticipants": this.totalparticipants,"facultyname":this.facultyid,"csid":this.csid,
   "startdate": this.startdate,"appic":1,"userid":this.userid,"status":this.status1,
    "enddate":this.enddate,"oprn":this.oprn,"endhour":this.endhour,"starthour":this.starthour,
    "endminute":this.endminute,"startminute":this.startminute,"endformat":this.endformat,"startformat":this.startformat};
    console.log( this.schedule_data )																
  this.server.post( "onlineexamine/forms/savecourseschedule", this.schedule_data, false)
  .then((data) => {
    if(data.status==200){ 
      this.isLoading = false;
      this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
       
        if (this.responseType == 'F')
         {              
            this.modalmsg =this.responseValue["out_result_msg"];
            this.cleardata();  
            const dialogManualLogin = this.dialog.open(this.alertDialog);
          }else(this.responseType == 'S')
          { 
           
            this.msgcolor ="green"
            this.cleardata();
            this.modaltitle="Success";

            if(this.oprn == "INS"){
              
            this.msgcolor ="green"
            this.cleardata();
            this.modaltitle="Success";
              this.modalmsg =this.responseValue["out_result_msg"];
            }else if(this.oprn = "UPD"){
              
            this.msgcolor ="green"
            this.cleardata();
            this.modaltitle="Success";
              this.modalmsg =this.responseValue["out_result_msg"];
            }else{
              
            this.msgcolor ="green"
            this.cleardata();
            this.modaltitle="Success";
              this.modalmsg =this.responseValue["out_result_msg"];
            }
             
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            this.router.navigateByUrl('/oeep/course_schedule');
      
        }
    }  
     
  }); 
  }
}  

clearfields(){
  this.csid = 0; 
  this.programname =  "";
  this.applicableic =  "";
  this.totalparticipants =  "";
  this.facultyname =  "";
  this.courseid = ""; 
  this.starthour =  "";
  this.endhour =  "";
  this.startformat =  "";
  this.endformat =  "";
  this.startminute =  "";
  this.endminute =  "";
  this.facultyid =  "";
  this.oprn = "INS";
  this.ngOnInit();
}


get f() { return this.course_schedule_Form.controls; }



cleardata(){ 
  this.csid = ""; 
  this.programname =  "";
  this.startdate =  "";
  this.enddate = ""; 
  this.applicableic =  "";
  this.totalparticipants =  "";
  this.facultyname =  "";
  this.facultyid =  "";
  this.starttime =  "";
  this.endtime =  "";
  this.courseid = ""; 
  this.getScheduleList();
 }
 closemodal(){
  //this.cleardata();  
  //this.ngOnInit();
  this.dialog.closeAll();
 }

 startdatechange(newDate) {
  //console.log(newDate)
 this.startdate = newDate;

}

enddatechange(newDate) {
this.enddate = newDate;
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


numberOnly(event): boolean {
  const charCode = (event.which) ? event.which : event.keyCode;
  if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    return false;
  }
  return true;
}

}



