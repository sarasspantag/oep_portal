import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router,ActivatedRoute } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { interval } from 'rxjs';
import {LocalStorage} from '../../core/local_storage.service';

import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import { PopupModalComponent } from '../../pages/popup-modal/popup-modal.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  @ViewChild('secondDialog') secondDialog: TemplateRef<any>;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;

  dRef: MatDialogRef<any>;
  public base_URL: string = CONFIG._url;
  
  username: any;
  login_data: { "username": any; "password": any; };
  password: any;
  email: any;
  user: any;
  msgcolor: string;
  msg: {};
  oprn: string;
  roleid: string;
  userid: string; 
  public modaltitle : string;
  public modalmsg : string;
  token: string;
  userdata: { "username": any };
  public List = [];
  public responseType : string;
  public responseValue : {};
  public errorMsg : any;
  urldata: {};
  isparticipant:number;
  isLoading = false;
  urlparam: any;
  constructor(private server: HyperService, private masterservice:MasterService,
     private dialog: MatDialog, private router: Router,private route: ActivatedRoute) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
   }


   openDialogWithRef(templateRef: TemplateRef<any>) {
    this.dialog.open(templateRef);
     }


  ngOnInit() {

    this.urldata={"url":window.location.href}
     console.log()
     let stringToSplit = window.location.href;
           let x = stringToSplit.split("?");
           this.urlparam=x[1];
        console.log(x[1]);
        if(x[1] != null){

          if(x[1].includes('tsid')==true){
         this.isparticipant = 1
         this.server.post("onlineexamine/forms/getdecrypturl", this.urldata, false)
         .then((data) => {
           if (data.status == 200) {
             this.responseType = this.masterservice.getResponseType(data);
             this.responseValue = this.masterservice.getResponseValue(data);
             if(this.responseValue !=null && this.responseValue > 0){
               LocalStorage.setValue("participanttsid",this.responseValue)
             }
           }
         });
        }
        }else{
          this.isparticipant = 0
          
        }
  }
 

  openmodaldialog(){

    LocalStorage.setValue('fromlogin', "1");
    const dialogManualLogin = this.dialog.open(this.secondDialog);
  }

  public onSubmitLogin(){

    if(this.username != "" && this.username != null && typeof this.username != undefined){
    
    if(this.password != "" && this.password != null && typeof this.password != undefined){

    this.login_data = { "username": this.username,"password":this.password};
 
    this.server.post( "onlineexamine/forms/validLogin", this.login_data, false)
    .then((data) => {
      if(data.status==200){ 
        this.responseType = this.masterservice.getResponseType(data);  
          this.responseValue = this.masterservice.getResponseValue(data);
        console.log(this.responseType)
          if(this.responseType == "F") 
          {
            this.modaltitle = "Info";
            this.msgcolor = "red";
            this.modalmsg = "Invalid mail id or password";
            this.cleardata();
            const dialogManualLogin = this.dialog.open(this.alertDialog);
          }else if(this.responseType == "S") 
          {
            console.log(this.responseType)
            console.log("inside yes")
            this.msgcolor = "green";
            this.List = this.responseValue["userDetails"];           
            this.roleid = this.List[0]["roleid"];          
            this.user = this.List[0]["username"];
            this.userid = this.List[0]["userid"];
            this.token = this.responseValue["tokenkey"];
            LocalStorage.setValue("userid",this.userid);
            LocalStorage.setValue("roleid",this.roleid);
            LocalStorage.setValue("token",this.token);
            LocalStorage.setValue("username",this.user);
            
            if(this.isparticipant !=null &&  this.isparticipant  > 0){
              this.router.navigateByUrl('/oeep/testsubmission');   
            }else if(this.responseValue["general_Aud"]== "1"){
              this.router.navigateByUrl('/myhome'); 
            }else if(this.urlparam != null && this.urlparam.includes("schid") == true){
              this.router.navigateByUrl('/oeep/course_registration?schid='+this.route.snapshot.queryParams["schid"]+'&crsname='+this.route.snapshot.queryParams["crsname"]);

            }else{
              this.router.navigateByUrl('/oeep/dashboard'); 
            }        
     
          }
      }  
       
    }); 
  }else{
    this.modaltitle="Alert";
    this.modalmsg ="Please Enter Password";
    const dialogManualLogin = this.dialog.open(this.alertDialog);
  }
  }else{
    this.modaltitle="Alert";
    this.modalmsg ="Please Enter Mail ID";
    const dialogManualLogin = this.dialog.open(this.alertDialog);
  }
  }


  forgotpassword(){
    this.isLoading = true;
    this.userid= LocalStorage.getValue("userid");
    if(this.email != "" && this.email != null && typeof this.email != undefined){

    
    this.userdata = { "username": this.email};
    console.log(this.email);
     this.server.post( "onlineexamine/forms/registerforgotpassword", this.userdata, false)
     .then((data) => {
       if(data.status==200){
        this.isLoading = false;
           this.responseType = this.masterservice.getResponseType(data);
           this.responseValue = this.masterservice.getResponseValue(data);
        
          if (this.responseType == "F")
           {          
           console.log(this.responseValue)
            this.modalmsg =JSON.stringify(this.responseValue);
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            this.cleardata();
          } else if(this.responseType == 'S')
           {
              this.modalmsg =this.responseValue["msg"];     
              this.msgcolor ="green"
              this.cleardata();
              this.modaltitle="Success";
              const dialogManualLogin = this.dialog.open(this.alertDialog);
              this.router.navigateByUrl('/login');
        
          }        
       }          
     }); 
    }else{
      this.errorMsg = "Please Enter Mail ID";
    }
     
   }   
   
  cleardata(){
    this.username ="";
    this.password ="";
   }

  closemodal(){ this.dialog.closeAll(); }
}



