import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { interval } from 'rxjs';
import { MasterService } from 'src/app/core/master.service';
import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

 
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
  selector: 'app-umm',
  templateUrl: './umm.component.html',
  styleUrls: ['./umm.component.scss']
})
export class UmmComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  dataSource = new MatTableDataSource<Transaction>();
  public myTableList :any;
  hideappic: boolean = true;
  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];
  
  displayedColumns: string[] = ['profile', 'fromdate','todate','role','status','action'];
  public transactions: Transaction[];
 // subject: any;
  coursename: any;
  errorMsg: string;
  checktest: boolean;
  constructor(private server: HyperService, private router: Router,  private dialog: MatDialog,private masterservice: MasterService) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    } 
 }
 
 isLoading = false;
 username: any;
 email: any;
 applicableic: any;
 public List: any; 
 public roleList: any; 
 public myList: any;
 oprn: string;
 role: any;
 userdata: { "username": any; "userid": any;  "status": any; "email": any;
 "totalparticipants": any; "appic": any,"role": any;"oprn": any };
 rolename: any;
 msgcolor: string;
 msg: {};
 roleid: any; 
 status: any; 
 userid: string;
 user: string;
 totalparticipants: string;
 token: string;
 public modaltitle : string;
 public modalmsg : string;
 public responseType : string;
 public responseValue : {};
 color = 'primary';
 checked = false; 
 status1: any;
 

 ngOnInit() {
  this.checktest = false;

  const logged = interval(200).subscribe(
    res => {
      if (LocalStorage.getValue("token")) {
       
        this.getroleList();
        this.getuserList();
        this.oprn = "INS";
        this.user = "0";
        logged.unsubscribe();
      } 
    } 
  );

}
  public getroleList(){

    this.server.get("onlineexamine/forms/getroleList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.roleList = this.responseValue;     
        });
  } 


  public getuserList(){

    this.server.get("onlineexamine/forms/getuserList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      this.myList = JSON.stringify(this.List);  
      this.transactions = JSON.parse(this.myList); 
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;   
    }); 
  }   

  getuserdetails(userid){
   
    this.server.get("onlineexamine/forms/getuserdetails/"+userid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
    
      this.List = this.responseValue[0];     
      this.roleid =  this.List["roleid"];
      this.username =  this.List["username"];
      this.email =  this.List["email"];
      this.user =  this.List["userid"];
      this.rolename =  this.List["rolename"];
      this.applicableic =  this.List["app_ic"];
      this.totalparticipants =  this.List["psnumber"];
      this.oprn = "UPD"; 
      this.status1 =  this.List["status"];
      this.checktest = true;

      if(this.status1== "1"){
       this.checked =true;
      }else{
        this.checked=false;
      }
      document.getElementById("user_ctr").scrollIntoView();
    });
  }
 
  delete(userid){
   
    this.server.get("onlineexamine/forms/getuserdetails/"+userid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
     
      this.List = this.responseValue[0];    
      this.user =  this.List["userid"];
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog);
    });
  }


     
    
  showview(userid){
      this.server.get("onlineexamine/forms/getuserdetails/"+userid)
      .then((data) => {
  
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
      
        this.List = this.responseValue[0];      
        this.roleid =  this.List["roleid"];
        this.username =  this.List["username"];
        this.coursename =  this.List["coursename"];
        this.email =  this.List["email"];
        this.user =  this.List["userid"];
        this.rolename =  this.List["rolename"];
        this.applicableic =  this.List["app_ic"];
        this.totalparticipants =  this.List["psnumber"];
        this.oprn = "UPD"; 
        this.status1 =  this.List["status"];
        if(this.status1== "1"){
          this.checked =true;
         }else{
           this.checked=false;
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

public saveuser(){
  this.errorMsg=""
  const validEmailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if(this.email !=null){
    if (validEmailRegEx.test(this.email)) {
  this.isLoading = true;
   this.userdata = { "username": this.username, "userid": this.user,  "totalparticipants": this.totalparticipants,
  "email":this.email,"role":this.roleid,"appic":this.applicableic,"status":this.status1,"oprn":this.oprn  };
 console.log(this.userdata)
 //return false;
  this.server.post( "onlineexamine/forms/saveuser", this.userdata, false)
  .then((data) => {
    if(data.status==200){
      this.isLoading = false;
      this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
       
       if (this.responseType == 'F')
        {           
            this.modalmsg = this.responseValue["out_result_msg"];
            this.cleardata();  
            const dialogManualLogin = this.dialog.open(this.alertDialog);
        }else(this.responseType == 'S') 
          {               
              this.msgcolor ="green"
              this.cleardata();
              this.modaltitle="Success";

            if(this.oprn == "INS"){
              this.modalmsg =  this.responseValue["out_result_msg"] ;
            }else if(this.oprn = "UPD"){
              this.modalmsg =  this.responseValue["out_result_msg"];
            }else{
              this.modalmsg = "User Details deleted successfully";
            }
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            this.router.navigateByUrl('/oeep/user_management_module');
      
        }
    }  
     
  });   
  }else{
    this.errorMsg="Invalid email"
  }
  }else{
  this.errorMsg="Email is required"
  }

}


clearfields(){
  this.roleid =  0;
  this.username = "";
  this.email =  "";
  this.applicableic = "";
  this.totalparticipants =  "";
  this.oprn = "INS";
}

cleardata(){ 
  this.roleid =  0;
  this.username = "";
  this.email =  "";
  this.applicableic = 0;
  this.totalparticipants =  "";
  this.getuserList();
 }
 closemodal(){
  this.cleardata();  
  this.dialog.closeAll();
 }
 changerole(){
  if(this.roleid == '1'){
    console.log("priya")
    this.hideappic = false;
  }else{
   console.log("ds")
   this.hideappic = true;
  }
 }

}
