import { Component, OnInit, ViewChild, TemplateRef,Injectable } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MatDialog, MatDialogRef} from "@angular/material";
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';
import { Workbook } from 'exceljs';
import * as fs from 'file-saver';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { SafePipe } from '../../core/safepipe';

import { DatePipe } from '@angular/common';
Injectable({ 
  providedIn: 'root'
})

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';
export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String;
}

@Component({ 
  selector: 'app-qbm',
  templateUrl: './qbm.component.html',
  styleUrls: ['./qbm.component.scss']
})

export class QbmComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  @ViewChild('importDialog') importDialog: TemplateRef<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();
  newCourseList: {};
  importquestionlist: {};

  public exportAsExcelFile(json: any[], excelFileName: string): void {
   
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);

    
    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    XLSX.utils.book_append_sheet(workbook, worksheet, 'productlist');  
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    console.log('worksheet',worksheet);
    //const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'buffer' });
    this.saveAsExcelFile(excelBuffer, excelFileName);
  }

  private saveAsExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {
      type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
  }

  displayedColumns: string[] = ['profile', 'fromdate','todate','status','action'];
  public transactions: Transaction[];
  testname: any;
  qbList: any;
  isLoading = false;

  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router ,private datePipe: DatePipe, private dialog: MatDialog) { 
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  } 
  editupload:boolean;
  subjectname: any;
  courseid: any;
  subjectid: any;
  duration: any;
  coursename: any;
  csid: any;
  public modaltitle : string;
  public modalmsg : string;
  batch: any; 
  startdate: any;
  public myList: any;
  public CourseList: any;
  public SubjectList: any; 
  userdata: { };

  userdata1: { "testid": any; "status": any;  "batch": any;"testname": any;
  "subjectid": any;"userid": any; "oprn": any,"filename":any;"qdetails":any; };

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
  public questiondetailsList: any; 
  public responseType : string;
  public responseValue : {};
  public loading:boolean;

  color = 'primary';
  checked = false;
  status1: any;

  ngOnInit() {
    
    this.getCourseList();
    this.getTestid();
    this.getQuestionBankList();
    this.editupload=false;
    this.oprn = "INS";
    LocalStorage.setValue("rid",3);
    LocalStorage.setValue("id",1);
    LocalStorage.setValue("type",0);
    LocalStorage.setValue("form","productimport");
    LocalStorage.setValue("foldername","import");
    LocalStorage.setValue("sessionname","productimport");
  }

   
  public getCourseList(){
 
    this.server.get("onlineexamine/forms/getCourseList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.newCourseList = this.responseValue;
        
    }); 
  }   
  
  getTestid(){

    this.server.get("onlineexamine/forms/getIdformat/3/qbm")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.testid = this.responseValue[0]["refno"];    
   
    }); 

  }

  showview(id){

    this.server.get("onlineexamine/forms/getQuestionbankdetails/"+id)
    .then((data) => {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
    
      this.subjectdetailsList = this.responseValue["questionmasterList"][0];

      // this.subjectid =  this.subjectdetailsList["subjectid"];  
      // this.subjectname =  this.subjectdetailsList["subjectname"];
      this.courseid =  this.subjectdetailsList["courseid"];  
      this.coursename =  this.subjectdetailsList["coursename"];
      this.testname =  this.subjectdetailsList["testname"];
      this.testid =  this.subjectdetailsList["testid"]; 
      this.batch =  this.subjectdetailsList["batch"];         
      this.status1 =  this.subjectdetailsList["status"];
      const dialogManualLogin = this.dialog.open(this.viewDialog);
   });
  }

 

  getQuestionBankList(){

    this.server.get("onlineexamine/forms/getQuestionBankList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.qbList = this.responseValue;
      this.qbList = JSON.stringify(this.qbList);  
      this.transactions = JSON.parse(this.qbList); 
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
    }); 

  }

  getdtls(testid){
  console.log(testid)
 // return false;
this.editupload=true;
    this.server.get("onlineexamine/forms/getQuestionbankdetails/"+testid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
   this.questiondetailsList = this.responseValue["questiondetailsList"];
   console.log(this.questiondetailsList)
      this.subjectdetailsList = this.responseValue["questionmasterList"][0];
      // this.subjectid =  this.subjectdetailsList["subjectid"];  
      // this.subjectname =  this.subjectdetailsList["subjectname"];

      this.courseid =  this.subjectdetailsList["courseid"];  
      this.coursename =  this.subjectdetailsList["coursename"];


      this.testname =  this.subjectdetailsList["testname"];
      this.testid =  this.subjectdetailsList["testid"]; 
      this.batch =  this.subjectdetailsList["batch"];  
      this.id =  this.subjectdetailsList["quesid"];         
      this.status1 =  this.subjectdetailsList["status"];

      if(this.status1== "Active"){
       this.checked =true;
       this.status1 = 1;
      }else{
        this.checked=false;
        this.status1 = 0;
      }
      this.oprn = "UPD";
      document.getElementById("qbm_ctr").scrollIntoView();  
    }); 
  }

  delete(testid){
   
    this.server.get("onlineexamine/forms/getQuestiondetails/"+testid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      
      this.List = this.responseValue[0];      
      this.testid =  this.List["quesid"];     
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog);
    
    });
  }


  clearfields(){
     
      this.courseid =  "";
       this.testname =  "";
      this.batch =  "";
      this.duration =  0;
      this.oprn = "INS";
      this.ngOnInit();
			document.getElementById("qbm_ctr").scrollIntoView();
  }

  changed(e){
    
    if(e.checked==true){
      this.status1="1";
    }else{
      this.status1="0";
    }
  
  }


  sampleformatregisterparticipants(){
   
   
    const header = ["S.No.", "Question", "Question type", "option_1", "option_2", "option_3",
     "option_4","answer","Question image if Available","Score for correct answer"]
    const data = [
      
    ];
  
    let workbook = new Workbook();
    let worksheet = workbook.addWorksheet('productlist');  
    console.log(worksheet)
    let headerRow = worksheet.addRow(header);    
  
    headerRow.eachCell((cell, number) => {
      cell.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: 'ffbf00' },
        bgColor: { argb: 'FF0000FF' }
      }
      cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
    })
   
    data.forEach(d => {
      let row = worksheet.addRow(d);
      let qty = row.getCell(6);
      let color = 'FF99FF99';
      if (+qty.value < 500) {
        color = 'FF9999'
      }
      qty.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: color }
      }
    }
    );
    worksheet.getColumn(2).width = 30;
    worksheet.getColumn(3).width = 30;
    worksheet.getColumn(4).width = 30;
    worksheet.getColumn(5).width = 30;
    worksheet.getColumn(6).width = 30;
    worksheet.getColumn(7).width = 30;
    worksheet.getColumn(8).width = 30;
    worksheet.getColumn(9).width = 30;
    worksheet.getColumn(10).width = 30;
    worksheet.addRow(["1","72*(12+18/12*3)","Single-Select","12","18","16","15","12"," ","5"]);
   
    let index = 100;
    for(let i=2;i < index;i++){
     worksheet.getCell('C'+i).dataValidation = {
       type: 'list',
       allowBlank: true,
       formulae: ['"Single-Select,Multi-Select"']
     };
    }
   
    workbook.xlsx.writeBuffer().then((data) => {
      let blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      fs.saveAs(blob, 'sampleexcel.xlsx');
    })
  

 // window.open('onlineexamine/forms/downloadsampleformatparticipantregister','_blank');
 
}

//   public savequestionbank(){
  
//     this.userid = LocalStorage.getValue("userid");
//     this.userdata = { "testid": this.testid, "status":this.status1, "batch":0, "subjectid": this.courseid,
//    "testname":this.testname, "oprn": this.oprn ,"userid":this.userid,"filename":LocalStorage.getValue("filename") };
//     console.log( this.userdata )
//     //return false;
   
//      this.server.post( "onlineexamine/forms/savequestion", this.userdata, false)
//      .then((data) => { 
//        if(data.status==200){
//          this.responseType = this.masterservice.getResponseType(data);
//            this.responseValue = this.masterservice.getResponseValue(data);
//            console.log( this.responseValue )
//            if (this.responseType == 'F') {
            
//              this.modalmsg = this.responseValue["out_result_msg"];
//              this.cleardata();  
             
//              const dialogManualLogin = this.dialog.open(this.alertDialog);
//             }else(this.responseType == 'S')
//             { 
           
//                this.msgcolor ="green"
//                this.editupload=false;
//               this.modaltitle="Success";
//               if(this.oprn == "INS"){
//                 this.modalmsg = this.responseValue[0]["out_result_msg"];
//               }else if(this.oprn = "UPD"){
//                 this.modalmsg =this.responseValue[0]["out_result_msg"];
//               }else{
//                 this.modalmsg =this.responseValue[0]["out_result_msg"];
//               }
//               const dialogManualLogin = this.dialog.open(this.alertDialog); 
//             this.router.navigateByUrl('/oeep/questionbank_management');
//             this.cleardata();
//           }
//        }  
        
//      }); 
   
//    }


//    cleardata(){ 
//     this.courseid =  0;
//     this.testid =  "";
//     this.batch =  "";
//     this.testname =  "";
//     this.getQuestionBankList();
// }

//     closemodal(){
//       this.cleardata();  
//     this.dialog.closeAll();
// }
// exportAsXLSX():void {
//     this.exportAsExcelFile(this.questiondetailsList, 'sample');
   
  
//   }
// }

public savequestionbank(){
   this.isLoading = true;
  // this.loading = true;

  this.userid = LocalStorage.getValue("userid");
  if( this.oprn == "INS"){

  
  this.userdata = { "testid": this.testid, "status":this.status1, "batch":0, "subjectid": this.courseid,
 "testname":this.testname, "oprn": this.oprn ,"userid":this.userid,"filename":LocalStorage.getValue("filename"),"zipfilename":LocalStorage.getValue("zipfilename") };
  console.log( this.userdata )
//   return false;
 
   this.server.post( "onlineexamine/forms/savequestion", this.userdata, false)
   .then((data) => { 
     if(data.status==200){
      //  this.loading = false;
       this.isLoading = false;
       this.responseType = this.masterservice.getResponseType(data);
         this.responseValue = this.masterservice.getResponseValue(data);
         console.log( this.responseValue )
         if (this.responseType == 'F') {
          this.modaltitle = "Fail";
           this.modalmsg = JSON.stringify(this.responseValue);
           this.cleardata();  
           
           const dialogManualLogin = this.dialog.open(this.alertDialog);
          }else if(this.responseType == 'S')
          { 
         
             this.msgcolor ="green"
             this.editupload=false;
            this.modaltitle="Success";
            if(this.oprn == "INS"){
              this.importquestionlist=this.responseValue;
              console.log("-----------"+this.importDialog)
              const dialogManualLogin = this.dialog.open(this.importDialog); 
              //this.modalmsg = this.responseValue[0]["out_result_msg"];
            }else if(this.oprn = "UPD"){
              this.modalmsg =this.responseValue[0]["out_result_msg"];
              const dialogManualLogin = this.dialog.open(this.alertDialog);
            }else{
              this.modalmsg =this.responseValue[0]["out_result_msg"];
              const dialogManualLogin = this.dialog.open(this.alertDialog);
            }
             
          this.router.navigateByUrl('/oeep/questionbank_management');
          this.cleardata();
        }
     }  
      
   }); 
  }else{

    this.userdata1 = { "testid": this.testid, "status":this.status1, "batch":0, "subjectid": this.courseid,
    "testname":this.testname, "oprn": this.oprn ,"userid":this.userid,
    "qdetails":JSON.stringify(this.questiondetailsList),"filename":LocalStorage.getValue("filename") };
     console.log( this.userdata1)
    // return false;
    
      this.server.post( "onlineexamine/forms/updatequestiondetails", this.userdata1, false)
      .then((data) => { 
        if(data.status==200){
           this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            console.log( this.responseValue )
            if (this.responseType == 'F') {
             
              this.modalmsg = this.responseValue["out_result_msg"];
              this.cleardata();  
              
              const dialogManualLogin = this.dialog.open(this.alertDialog);
             }else(this.responseType == 'S')
             { 
             
                this.msgcolor ="green"
                this.editupload=false;
               this.modaltitle="Success";
               if(this.oprn == "INS"){
                 this.modalmsg = this.responseValue[0]["out_result_msg"];
               }else if(this.oprn = "UPD"){
                 this.modalmsg =this.responseValue["out_result_msg"];
               }else{
                 this.modalmsg =this.responseValue["out_result_msg"];
               }
               const dialogManualLogin = this.dialog.open(this.alertDialog); 
             this.router.navigateByUrl('/oeep/questionbank_management');
             this.cleardata();
           }
        }  
         
      }); 

  }
 }


 cleardata(){ 
  this.courseid =  0;
  this.testid =  "";
  this.batch =  "";
  this.testname =  "";
  this.getQuestionBankList();
}

  closemodal(){
    this.cleardata();  
  this.dialog.closeAll();
}
exportAsXLSX():void {
  this.exportAsExcelFile(this.questiondetailsList, 'sample');
 

}
}

