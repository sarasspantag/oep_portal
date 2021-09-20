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
import { DatePipe } from '@angular/common';
import { CONFIG } from 'src/app/config';
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';
export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String;
}
 
@Component({
  selector: 'app-certificate', 
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})


export class CertificateComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();
  public _URL: string = CONFIG._url;
  partList: any;
  batchList: any;
  batch: any;
  partid: any;
  duration: any;
  testid: any;
  partname: any;
  selectedYears: any[];
  marks: any;
  grade: any;
  public responseType : string;
  public responseValue : {};
  randomno: any;
  oprn: string;
  // userdata: { "testid": any; "questionid": any; "tsid": any; 
  // "batch": number; "partid": any; "oprn": string; };
  userdata: { "batch": number; "partid": any; "tsid": any;"oprn": string; };
  singleuserdata: { "batch": number; "partid": any; "tsid": any;"oprn": string; };
  data: { "downloadlink": number; "partid": any; "oprn": string;"randomno":string; "tsid":any };
  userid: any;
  questionid: any;
  tsid: any;
  tempid: any;
  checkrole:boolean;
  tsList: {};
  testscheduleid: any;
  check: boolean;
  checkdownload: boolean;
  public modaltitle: string;
  public msg: string;
  loadershow:boolean;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  fileUrl;
  downloadlink: any;
  loading: boolean;
  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router ,private datePipe: DatePipe, private dialog: MatDialog) { 
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  } 

  ngOnInit() {
 
    this.getBatchList(); 
    this.permissionFn(); 
    this.check=false;
    this.checkdownload=false;
    this.oprn = "INS";
    this.loadershow=false;
    this.loading=false;
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

  equals(objOne, objTwo) {
   
    if (typeof objOne !== 'undefined' && typeof objTwo !== 'undefined') {
      return objOne.id === objTwo.id;
    }
  }
  
  
  selectAll(checkAll, select: NgModel, values) {

 
    if(checkAll){
      select.update.emit(values["partid"]); 
    }
    else{
      select.update.emit([]);
    }
  
  }


  getParticipantList(id){
    this.tsid=id;
    this.check=true;
    this.server.get("onlineexamine/forms/getParticipantList/"+id)
    .then((data) => {
 
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.partList = this.responseValue;     
    }); 
  }    

  getTestscheduleList(batch){
    this.batch= batch;
    this.server.get("onlineexamine/forms/getTestscheduleList/"+batch)
    .then((data) => {
 
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.tsList = this.responseValue;     
    }); 
  }
  getBatchList(){
   
    this.server.get( "onlineexamine/forms/getBatchList")
    .then((data) => {
      if(data.status==200){
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
          this.batchList = this.responseValue;  
      }  
        
    }); 
  }

  getBatchdetails(batch,tempid){

    this.tempid=tempid;
    this.oprn = "INS";
    this.server.get( "onlineexamine/forms/reports/getBatchdetails/"+ batch+"/"+tempid)
    .then((data) => {
      if(data.status==200){
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);      
          console.log(this.responseValue)
          this.testid = this.responseValue[0]["testid"];  
          this.duration = this.responseValue[0]["testdate"]; 
          this.grade = this.responseValue[0]["grade"]; 
          this.marks = this.responseValue[0]["marks"]; 
          this.questionid = this.responseValue[0]["questionid"]; 
          this.tsid = this.responseValue[0]["tsid"]; 
         }  
        this.getsinglecertificate();
    }); 

  } 

  download(batch,tempid){

    this.tempid=tempid;
    this.checkdownload=true;
    this.oprn = "INS";
    this.server.get( "onlineexamine/forms/getBatchdetails/"+ batch+"/"+tempid)
    .then((data) => {
      if(data.status==200){
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);      
          console.log(this.responseValue)
          this.testid = this.responseValue[0]["testid"];  
          this.duration = this.responseValue[0]["testdate"]; 
          this.grade = this.responseValue[0]["grade"]; 
          this.marks = this.responseValue[0]["marks"]; 
          this.questionid = this.responseValue[0]["questionid"]; 
          this.tsid = this.responseValue[0]["tsid"]; 
         }  
        this.getsinglecertificate();
    }); 

  } 


  sendmail(batch,tempid){
    this.loadershow = true;
    this.loading =true;
    this.checkdownload = false;
    this.tempid=tempid;   
    this.oprn = "SEND_MAIL";
    this.server.get( "onlineexamine/forms/getBatchdetails/"+ batch+"/"+tempid)
    .then((data) => {
      if(data.status==200){
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);      
          console.log(this.responseValue)
          this.testid = this.responseValue[0]["testid"];  
          this.duration = this.responseValue[0]["testdate"]; 
          this.grade = this.responseValue[0]["grade"]; 
          this.marks = this.responseValue[0]["marks"]; 
          this.questionid = this.responseValue[0]["questionid"]; 
          this.tsid = this.responseValue[0]["tsid"]; 
         }  
        this.getsinglecertificate();
    }); 

  } 


  
  getsinglecertificate(){
   
    this.singleuserdata = {  "batch":this.batch,"partid":this.tempid,"oprn": this.oprn,"tsid": this.tsid};
    console.log(this.singleuserdata)
  //  return false;
     this.server.post( "onlineexamine/forms/reports/getsinglecertificate", this.singleuserdata, false)
     .then((data) => { 
       if(data.status==200){
           this.responseType = this.masterservice.getResponseType(data);
           this.responseValue = this.masterservice.getResponseValue(data);
           console.log(this.responseValue)
           this.randomno = this.responseValue["out_genrate_id"]; 
           this.downloadlink = this._URL+"onlineexamine/forms/reports/printcoursecertificate/"+this.randomno;
           
           if( this.checkdownload == true){     
                  this.downloadCertificate();
           }else{

            if(this.oprn=="INS"){
           
              window.open(this._URL+"onlineexamine/forms/reports/printcoursecertificate/"+this.randomno, '_blank', 'top=0,left=0,height=100%,width=auto');
            }else if(this.oprn=="SEND_MAIL"){
              this.sendmailtoparticipant();
              
            }else{
              this.msg = this.responseValue["out_result_msg"]; 
              window.open(this._URL+"onlineexamine/forms/reports/printcoursecertificate/"+this.randomno, '_blank', 'top=0,left=0,height=100%,width=auto');
            }
                
           }     

          } 
       });    
     }


     downloadCertificate(){
      this.server.getFile("onlineexamine/forms/reports/printcoursecertificate/"+this.randomno).subscribe(
        res =>  this.downloadFile(res)
        );
      }

     sendmailtoparticipant(){
      
      this.data = {"downloadlink":this.downloadlink,"partid":this.tempid,"oprn": "INS","randomno":this.randomno,"tsid": this.tsid};
      console.log(this.data)
     //  return false;
       this.server.post( "onlineexamine/forms/sendmailtoparticipant", this.data, false)
       .then((data) => { 
         if(data.status==200){
          this.loadershow = false;
          this.loading =false;
             this.responseType = this.masterservice.getResponseType(data);
             this.responseValue = this.masterservice.getResponseValue(data);
             console.log(this.responseValue)
             if(this.responseValue["out_result_type"] == "S"){
              this.msg = "Certificate send successfully"; 
               this.modaltitle = "Sucess";
             }else{
              this.msg = "Error occurs..  "; 
               this.modaltitle = "Failure";
             }
            
             const dialogManualLogin = this.dialog.open(this.alertDialog);
            } 
         });  
      
    }

    
    downloadFile(blob) {
      this.fileUrl = window.URL.createObjectURL(blob);
      console.log(this.fileUrl)
     // this.fileUrl.getAsBinary();
    //  var fd = new FormData(document.forms[0]);
    //  fd.append("canvasImage", blob);
    //   console.log(fd)

    //   this.server.post_fileUpload( "onlineexamine/forms/getfilesaras", fd, false)
    //   .then((data) => { 
    //       console.log(data);
    //   });
    //   return false;
      const certificate = document.createElement('a');
      certificate.href = this.fileUrl;
      certificate.download = "Course Completion Certificate.pdf";
      certificate.click();
    }

    

  printcoursecertificate(){
    
    this.userdata = {  "batch":this.batch,"partid":JSON.stringify(this.selectedYears),"oprn": this.oprn,
    "tsid": this.tsid};
    console.log(this.userdata)

     this.server.post( "onlineexamine/forms/participantcertificatedetails", this.userdata, false)
     .then((data) => { 
       if(data.status==200){
           this.responseType = this.masterservice.getResponseType(data);
           this.responseValue = this.masterservice.getResponseValue(data);
           console.log(this.responseValue)
           this.randomno = this.responseValue;         
           this.getreport();

          } 
       });    
     }

   getreport(){
    window.open(this._URL+"onlineexamine/forms/reports/printcoursecertificate/"+this.randomno, '_blank', 'top=0,left=0,height=100%,width=auto');
 
   
    } 
    closemodal() {
      this.dialog.closeAll();
    }
  }

