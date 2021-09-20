import { Component, OnInit,Injectable, ViewChild, TemplateRef } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router,ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogRef} from "@angular/material";
import { DatePipe } from '@angular/common';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {FormBuilder, FormGroup, Validators,FormControl} from "@angular/forms";

export interface Applicable {
  value: string;
  viewValue: string;
}

Injectable({
  providedIn: 'root'
})

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String;
}

@Component({
  selector: 'app-course-reg',
  templateUrl: './course-reg.component.html',
  styleUrls: ['./course-reg.component.scss']
})
export class CourseRegComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  dataSource = new MatTableDataSource<Transaction>();

  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];

  public responseType: string;
  public responseValue: {};
  public registerobject:any;
  public modalmsg : string;
  public modaltitle : string;
  public msgcolor :any;
  displayedColumns: string[] = ['profile', 'fromdate','todate','status','action'];
  public transactions: Transaction[];
  participantname:any;
  psnumber:any;
  e_mail:any;
  applicableic:any;
  jobcode:any;
  List: {};
  myList: string;
  roleid: any;
  username: any;
  email: any;
  user: any;
  rolename: any;
  totalparticipants: any;
  oprn: string;
  status1: any;
  color = 'primary';
  checked = false; 
  //subject: any;
  coursename: any;
  showdetails:boolean;
  approvebutton:boolean;
  public details = {
    participantname : "",
  };
  public errorMsg="";
  coursenameshow:String=" No schedule selected";
  checkrole: boolean;
  isLoading = false;
  courseregForm: FormGroup;
  submitted:boolean = false;
  userid: any;
  deptList: {};
  deptid: any;

  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router,private datePipe: DatePipe,private dialog: MatDialog,
    private route: ActivatedRoute, private formBuilder: FormBuilder) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
   }

  ngOnInit() {
    this.oprn = "INS"; 
    this.scheduleid = this.route.snapshot.queryParams["schid"];
    this.userid = "0"; 
    this.permissionFn();   
    this.getParticipantScheduledetails();
    this.getParticipantList();
    this.getDepartmentList();

    this.submitted=false;
    this.courseregForm = this.formBuilder.group({
      PartName: new FormControl('', Validators.compose([
          Validators.required,
      ])),
      PSnumber: new FormControl('', Validators.compose([
        Validators.required,
    ])),
    Email:new FormControl('', Validators.compose([
      Validators.required,
    ])),
    //   ApplicableIC: new FormControl('', Validators.compose([
    //     Validators.required,
    // ])),
    Projectcode: new FormControl('', Validators.compose([
      Validators.required,
  ]))
    })

  }

  public permissionFn(){
    if( LocalStorage.getValue("roleid") == 4){
      this.checkrole= false;
      //console.log(this.checkrole)
    }else{
      this.checkrole= true;
      //console.log(this.checkrole)
    }
  }

  public getParticipantScheduledetails(){
    if(LocalStorage.getValue("roleid") == '4' && this.scheduleid != null && typeof this.scheduleid != undefined ){
      this.coursenameshow = this.route.snapshot.queryParams["crsname"];
      this.showdetails = true;
      this.server.get( "onlineexamine/forms/getcourseparticipantdetails/"+LocalStorage.getValue("userid") )
      .then((data) => {
      if(data.status==200){
        this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
        this.participantname = this.responseValue[0]["partcipantname"];
        this.psnumber = this.responseValue[0]["psnumber"];
        this.email = this.responseValue[0]["email"];
        this.applicableic = this.responseValue[0]["applicableic"];
        this.jobcode = this.responseValue[0]["jobcode"];
          console.log(this.responseValue)
      }  
      
    }); 
    }else if(LocalStorage.getValue("roleid") != '4'){
      this.coursenameshow = this.route.snapshot.queryParams["crsname"];
    }
    else{
      this.showdetails = false;
    }
  }


  scheduleid = "";
  regCourse(){
    this.submitted = true;
    this.errorMsg=""
    const validEmailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const phonenopattern = /[0-9\+\-\ ]/;

   
   
    if(this.psnumber !=null ){
      if(phonenopattern.test(this.psnumber)){
    if(this.email !=null){
      console.log(validEmailRegEx.test(this.email))
      if (validEmailRegEx.test(this.email)) {
    if(LocalStorage.getValue("scheduleid") != null && LocalStorage.getValue("scheduleid") != "" &&
     typeof this.scheduleid != undefined){
      this.registerobject={"roleid":LocalStorage.getValue("roleid"),"courseid":LocalStorage.getValue("scheduleid"),
      "participantname":this.participantname,"psnumber":this.psnumber,"email":this.email,
      "applicableic":1,
      "jobcode":this.jobcode,"oprn":this.oprn,"userid": this.userid,}

      console.log(this.registerobject)
     // return false;
    
   
      if(this.courseregForm.valid){
        this.isLoading = true;

      this.server.post( "onlineexamine/forms/registerparticipantcourse", this.registerobject, false)
      .then((data) => {
        
        if(data.status==200){
          this.submitted=false;
          this.isLoading = false;
          this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
           
             const dialogManualLogin = this.dialog.open(this.alertDialog);
             this.modaltitle="Success";
             this.msgcolor = "green"
             this.modalmsg =this.responseValue["out_result_msg"];
             this.clearfields();

        }  
          
      }); }
    }else{
      console.log(LocalStorage.getValue("scheduleid"))
      this.modalmsg = "Please Choose the Course from Schedule and Try to Register!!";
      this.modaltitle= "Registration Failed";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
      
    }
  }else{
    this.errorMsg="Invalid email"
  }
  }else{
  this.errorMsg="Email is required"
  }
 } else{
    this.errorMsg="Invalid phone no"
    }
  }else{
  this.errorMsg="Phone no is required"
  
  }
  }

importRegCall(){
  if(LocalStorage.getValue("scheduleid") != null && LocalStorage.getValue("scheduleid") != "" && typeof this.scheduleid != undefined){
    this.router.navigateByUrl('/oeep/importparticipants');
  }else{
    this.errorMsg = "Please Choose the Course from Schedule and Try to Register!!";
  }
}

getParticipantList(){

  this.server.get("onlineexamine/forms/getParticipantList/"+LocalStorage.getValue("roleid")+"/"
                      +LocalStorage.getValue("userid"))
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.List = this.responseValue;
    this.myList = JSON.stringify(this.List);  
    this.transactions = JSON.parse(this.myList);  
    this.dataSource.data = this.transactions;
    this.dataSource.paginator = this.paginator;
    
    for(let i=0;i<this.transactions.length;i++){
     
      console.log(this.transactions[i]["isapproved"]);
      if(this.transactions[i]["isapproved"] == 0){
        console.log(this.transactions[i]["isapproved"])
        this.transactions[i]["approvebutton"] = true;
      }else{
        this.transactions[i]["approvebutton"] = false;
        this.approvebutton = false;
      }
    }  
  });
}


getDepartmentList(){

  this.server.get("onlineexamine/forms/getDepartmentList")
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    this.deptList = this.responseValue;    
  })
}


showview(userid,courseid){
  this.server.get("onlineexamine/forms/getParticipantdetails/"+userid+'/'+courseid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
  
    this.List = this.responseValue[0];      
   
    this.username =  this.List["username"]; 
    this.email =  this.List["emailid"]; 
    this.coursename =  this.List["coursename"];   
    this.status1 =  this.List["status"];
    const dialogManualLogin = this.dialog.open(this.viewDialog);
});
}

approvedparticipant(coursedetailid){
  if(LocalStorage.getValue("roleid") != "4"){
  this.registerobject={"roleid":LocalStorage.getValue("roleid"),"courseid":coursedetailid,"userid": this.userid,
  "participantname":this.participantname,"psnumber":this.psnumber,"email":this.email,
  "applicableic":1,
  "jobcode":this.jobcode,"oprn":"APPROVED"}
  this.server.post( "onlineexamine/forms/registerparticipantcourse", this.registerobject, false)
  .then((data) => {
    if(data.status==200){
      this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
       
         const dialogManualLogin = this.dialog.open(this.alertDialog);
         this.modaltitle="Success";
         this.msgcolor = "green"
         this.modalmsg =this.responseValue["out_result_msg"];
         this.clearfields();
    }  
      
  }); 
  }else{
            const dialogManualLogin = this.dialog.open(this.alertDialog);
             this.modaltitle="Info";
             this.msgcolor = "green"
             this.modalmsg = "Your Course is waiting for Approval";
  }
}
getuserdetails(userid){
  
  this.server.get("onlineexamine/forms/getuserdetails/"+userid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);  
    console.log(this.responseValue)
    this.List = this.responseValue[0];     
    LocalStorage.setValue("scheduleid",this.List["courseid"])
    this.roleid =  this.List["roleid"];
    this.username =  this.List["username"];
    this.participantname =  this.List["username"];
    this.email =  this.List["email"];
    this.user =  this.List["userid"];
    this.userid =  this.List["userid"];
    this.rolename =  this.List["rolename"];
    this.applicableic =  this.List["app_ic"];
    this.totalparticipants =  this.List["psnumber"];
    this.psnumber =  this.List["psnumber"];
    this.oprn = "UPD"; 
    this.status1 =  this.List["status"];
    this.jobcode =  this.List["jobcode"]
    this.deptid =  this.List["jobcode"]

    if(this.status1== "1"){
     this.checked =true;
    }else{
      this.checked=false;
    }
    document.getElementById("course_reg").scrollIntoView();
  });
}

closemodal(){
  this.clearfields();  
  this.dialog.closeAll();
}
clearfields(){
  this.participantname = "";
  this.psnumber = " ";
  this.email = "";
  this.applicableic = " ";
  this.jobcode = " "; 
  this.oprn = "INS"; 
  this.getParticipantList();
}
get f() { return this.courseregForm.controls; }

numberOnly(event): boolean {
  const charCode = (event.which) ? event.which : event.keyCode;
  if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    return false;
  }
  return true;
}

}

