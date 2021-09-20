import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import {FormBuilder, FormGroup, Validators,FormControl} from "@angular/forms";

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String; 
}

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss']
})
export class TemplateComponent implements OnInit {

  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();
  
  isLoading = false;
  public responseType: string;
  public responseValue: {};
  public transactions: Transaction[];
  List: {};
  myList: string;  
  public myTableList :any;
  displayedColumns: string[] = ['profile', 'fromdate','todate','action'];
  content: string;
  name: string;
  subject: string;
  OnEdit: boolean ; 
  schedule_data: { "name": any; "subject": any;"tempid": any;"content": any; "oprn": any;};
  userid: any;
  modalmsg: any;
  msgcolor: string;
  modaltitle: string;
  oprn: string;
  id: any;
  tempid: any;
  templateForm: FormGroup;
  submitted:boolean = false;

  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router, private dialog: MatDialog,private formBuilder: FormBuilder) {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
     }

  ngOnInit() {
    this.gettemplateList();
    this.oprn = "INS";
    this.id = "0";
    this.OnEdit = false ;

    this.templateForm = this.formBuilder.group({
      TemplateName: new FormControl('', Validators.compose([
          Validators.required,
      ])),
     
      TemplateSubject: new FormControl('', Validators.compose([
          Validators.required,
      ])),
      TemplateContent: new FormControl('', Validators.compose([
          Validators.required,
      ]))
    })

  }

  gettemplateList(){
    this.server.get("onlineexamine/forms/gettemplateList" )
    .then((data) => {
      if (data.status == 200) {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);       
      this.List = this.responseValue;
      this.myList = JSON.stringify(this.List);  
      this.transactions = JSON.parse(this.myList); 
       this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
      }  
    }); 
    }

  showview(id){
    this.server.get("onlineexamine/forms/gettemplatetails/"+id)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue[0];  
      this.name =  this.List["name"];
        this.subject =  this.List["subject"];
        this.content =  this.List["content"];
      const dialogManualLogin = this.dialog.open(this.viewDialog);
 });
}

  getdetails(id){
    this.server.get("onlineexamine/forms/gettemplatetails/"+id)
    .then((data) => {
      this.OnEdit = true ;
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue[0];  
      this.name =  this.List["name"];
      this.subject =  this.List["subject"];
      this.content =  this.List["content"];
      this.id =  this.List["id"];
      this.oprn = "UPD";
      document.getElementById("course_sche_ctr").scrollIntoView();

});
}

delete(id){
 
  this.server.get("onlineexamine/forms/gettemplatetails/"+id)
  .then((data) => {
    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);  
    this.List = this.responseValue[0]; 
    this.id =  this.List["id"];
    this.oprn = "DEL";
    this.modalmsg ="Do you want to delete ?";      
    const dialogManualLogin = this.dialog.open(this.deleteDialog);

  });
}

public savemailtemplate(){ 

  this.submitted = true;

  if(this.templateForm.valid){
  this.isLoading = true;
  this.userid = LocalStorage.getValue("userid");
  this.schedule_data = { "name": this.name, "subject": this.subject,"tempid": this.id,"content":this.content,"oprn":this.oprn};

  this.server.post( "onlineexamine/forms/savemailtemplate", this.schedule_data, false)
  .then((data) => {
    if(data.status==200){ 
      this.isLoading = false;
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.submitted = false;
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
            this.router.navigateByUrl('/oeep/template');
        }
    }   
  }); 
  }
}  

cleardata(){ 
  this.name = ""; 
  this.subject =  "";
  this.content =  "";
  this.oprn = "INS";
  this.gettemplateList();
 }
 closemodal(){
  this.cleardata();  
  this.dialog.closeAll();
 }

 get f() { return this.templateForm.controls; }

}






