import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {FormBuilder, FormGroup, Validators,FormControl} from "@angular/forms";

import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import { Local } from 'protractor/built/driverProviders';
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
  selector: 'app-cmm',
  templateUrl: './cmm.component.html',
  styleUrls: ['./cmm.component.scss']
})

export class CmmComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();
  public condition:boolean;
  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];

  public myTableList :any;

  displayedColumns: string[] = ['profile', 'fromdate','todate','status','action'];
  public transactions: Transaction[];
  filename: any;
  isLoading = false;

  constructor(private server: HyperService, private masterservice:MasterService, private router: Router, 
    private dialog: MatDialog,private formBuilder: FormBuilder)  {
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
   course_data: { "coursename": any;"courseid": any; "status": any;  "coursedesc": any;
   "duration": any; "appic": any,"userid": any,    "coursedetails": any;"oprn": any,"filename": any };
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
   public ContentButton : boolean;
   cmmForm: FormGroup;
   submitted:boolean = false;

    ngOnInit() {
    LocalStorage.setValue("id",1);
    LocalStorage.setValue("type",1);
    LocalStorage.setValue("form","product");
    LocalStorage.setValue("foldername","description");
    LocalStorage.setValue("sessionname","description");

    this.ContentButton = false;
    this.condition = false;
    this.getCourseList();
    this.oprn = "INS";

    this.submitted=false;
      this.cmmForm = this.formBuilder.group({
        CourseName: new FormControl('', Validators.compose([
            Validators.required,
        ])),
       
        CourseDesc: new FormControl('', Validators.compose([
            Validators.required,
        ])),
        Duration: new FormControl('', Validators.compose([
            Validators.required,
        ])),
      //   ApplicableIc:new FormControl('', Validators.compose([
      //     Validators.required,
      // ]))
      })

  }
 
  public getCourseList(){

    this.server.get("onlineexamine/forms/getallCourseList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      this.myList = JSON.stringify(this.List);  
      this.transactions = JSON.parse(this.myList);
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
    console.log(this.dataSource);
    });
  }   
  
  changed(e){
  
    if(e.checked==true){  
      this.status1="1";
    }else{
      this.status1="0";
    }
 
  }
  getcoursedtls(courseid){
   
    this.server.get("onlineexamine/forms/getCoursedetails/"+courseid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];
    
      this.courseid =  this.List["course_id"];
      this.coursename =  this.List["course_name"];
      this.coursedesc =  this.List["course_desc"];
      this.duration =  this.List["duration"];
      this.applicableic =  this.List["applicable_ic"];
      this.filename =  this.List["details"];
      LocalStorage.setValue("filename",this.filename);
      this.oprn = "UPD"; 
      this.status1 =  this.List["status"];

      if(this.status1== "1"){ 
       this.checked =true;
      }else{ 
        this.checked=false;
      }
     
     document.getElementById("cmm_ctr").scrollIntoView();
    });
  }
 
  delete(courseid){
   
    this.server.get("onlineexamine/forms/getCoursedetails/"+courseid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];
   
      this.courseid =  this.List["course_id"];
      this.duration =  0;
      this.applicableic =  0;
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog);
    });
  }
 
  clearfields(){
    this.courseid =  0;
    this.coursename = "";
    this.coursedesc =  "";
    this.duration =  ""; 
    this.applicableic =  "";
    this.checked =  false;
    this.oprn = "INS";
  }

 showview(courseid){
      this.server.get("onlineexamine/forms/getCoursedetails/"+courseid)
      .then((data) => {
  
         this.responseType = this.masterservice.getResponseType(data);
         this.responseValue = this.masterservice.getResponseValue(data);      
         this.List = this.responseValue[0];
       
         this.courseid =  this.List["course_id"];
         this.coursename =  this.List["course_name"];
         this.coursedesc =  this.List["course_desc"];
         this.duration =  this.List["duration"];
         this.applicableic =  this.List["applicable_ic"];
         const dialogManualLogin = this.dialog.open(this.viewDialog);

          const div = new String(this.coursedesc);
          if(div.length > 100)
          {
          this.ContentButton = true;
          }

          dialogManualLogin.afterClosed().subscribe(result => {
           this.condition = false;
           this.ContentButton = false;
         });
 

          });
    }

     public saveCourse(){
      this.submitted = true;
       this.userid = LocalStorage.getValue("userid");
       this.course_data = { "coursename": this.coursename,"coursedesc":this.coursedesc,"courseid":this.courseid,
       "duration": this.duration,"appic":1,"userid":this.userid,"status":this.status1,
       "coursedetails":"","oprn":this.oprn,"filename":LocalStorage.getValue("filename")};
       
      if(this.cmmForm.valid){
      this.isLoading = true;
      this.server.post( "onlineexamine/forms/savecoursemastermgmt", this.course_data, false)
      .then((data) => { 
       if(data.status==200){
        this.submitted=false;
        this.isLoading = false;
        this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
        
          if (this.responseType == 'F')
          {
            this.msgcolor = "red";
            this.modalmsg =this.responseValue["out_result_msg"];
          }else(this.responseType == 'S') 
          {
              this.modalmsg =this.responseValue["out_result_msg"];
              this.msgcolor ="green"
              this.cleardata();
              this.modaltitle="Success";
              if(this.oprn = "INS"){
                this.modalmsg =this.responseValue["out_result_msg"];
              }else if(this.oprn = "UPD"){
            
                this.modalmsg =this.responseValue["out_result_msg"];
              }else if(this.oprn = "DEL"){
                
                this.modalmsg =this.responseValue["out_result_msg"];
              }else{

              }
              const dialogManualLogin = this.dialog.open(this.alertDialog);
              this.router.navigateByUrl('/oeep/coursemastermanagement');
        
          }
      }  
        
    });  
  }
 }

 cleardata(){ 
  this.courseid =  0;
  this.coursename = "";
  this.coursedesc =  "";
  this.duration =  "";
  this.applicableic ="";
  this.checked =  false;
  this.getCourseList();
 }
 closemodal(){
  this.cleardata(); 
  this.dialog.closeAll();
 }

 upload(){
  this.router.navigateByUrl('upload');
 }
 readmore(){
  this.condition = true;
  this.ContentButton = false;
 }
 numberOnly(event): boolean {
  const charCode = (event.which) ? event.which : event.keyCode;
  if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    return false;
  }
  return true;

  }
  get f() { return this.cmmForm.controls; }
}  
