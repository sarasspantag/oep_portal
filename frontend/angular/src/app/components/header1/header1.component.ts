import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import { PopupModalComponent } from '../../pages/popup-modal/popup-modal.component';
import { Local } from 'protractor/built/driverProviders';

@Component({
  selector: 'app-header1',
  templateUrl: './header1.component.html',
  styleUrls: ['./header1.component.scss']
})
export class Header1Component implements OnInit {
  @ViewChild('secondDialog') secondDialog: TemplateRef<any>;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;

  public login_data =  {"username":"guest@gmail.com","password":"admin@123"};
  public userObject : {};
  public categoryurl : any;

  dRef: MatDialogRef<any>;
  constructor(private server: HyperService, private router: Router ,private masterservice: MasterService, 
  private dialog: MatDialog) {

    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    } 
    
  }
  openDialogWithRef(templateRef: TemplateRef<any>) {
   this.dialog.open(templateRef);
 }

 userdata: { "userid": any; "password": any;"confirmpassword": any };
 
 username: any;
 userid: any;
 password: any;
 newpassword: any;
 public modaltitle : string;
 public modalmsg : string;
 confirmpassword: any;
 msg: {};
 roleid: any; 
 user: any; 
 totalparticipants: string;
 token: string;
 public List: any; 
 public responseType : string;
 public responseValue : {};
 msgcolor: any; 
 errorMsg:any;

  ngOnInit() {
  
  }

  openmodaldialog(){

    LocalStorage.setValue('fromlogin', "1");
    
    const dialogManualLogin = this.dialog.open(this.secondDialog);
  }
  
  logout(){
    LocalStorage.removeJWT();
    this.guest_login();
    this.router.navigateByUrl('/myhome');
  }

  guest_login(){
    this.server.post("onlineexamine/forms/validLogin",this.login_data,false)
    .then((data) => {
      if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        if(this.responseValue["tokenkey"] != null){
          this.userObject = this.responseValue["userDetails"][0];
          LocalStorage.setValue('token', this.responseValue["tokenkey"]);
        }
       
      }
    });
  }

  savechangepassword(){
    this.userid= LocalStorage.getValue("userid");
    console.log(this.newpassword)

    if(this.newpassword != "" && this.newpassword != null 
    && this.confirmpassword != "" && this.confirmpassword != null
     && this.password != "" && this.password != null){

    if(this.newpassword != this.confirmpassword){
      this.errorMsg ="Password mismatch";
      return false;
    }

    this.userdata = { "userid": this.userid, "password": this.password,"confirmpassword":this.confirmpassword };
  
   
     this.server.post( "onlineexamine/forms/changepassword", this.userdata, false)
     .then((data) => { 
       if(data.status==200){
         this.responseType = this.masterservice.getResponseType(data);
           this.responseValue = this.masterservice.getResponseValue(data);
         
          if (this.responseType == 'F') {
            this.msgcolor = "red";
            this.msg = this.responseValue;
            this.errorMsg =this.responseValue;
          } else if (this.responseType == 'S') {
            this.msgcolor = "green";
            this.modalmsg = this.responseValue["msg"];
            this.modaltitle = "Success";
            LocalStorage.setValue("otp", this.responseValue["id"]);
            this.cleardata();
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            this.router.navigateByUrl('login');
       
          }
        
       }  
        
     }); 
    }else{

      this.errorMsg ="Enter Inputs";
      return false;
    }
   }
   cleardata(){ 
    this.userid =  0;
      
   }

   closemodal(){
   
    this.dialog.closeAll();
   }
     
   }
   