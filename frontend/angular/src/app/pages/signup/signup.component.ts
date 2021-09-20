import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { LocalStorage } from 'src/app/core/local_storage.service';
import {FormBuilder, FormGroup, Validators,FormControl} from "@angular/forms";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  username: string;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  constructor(private server: HyperService, private masterservice:MasterService,
    private dialog: MatDialog, private router: Router,private formBuilder: FormBuilder) {
   if (LocalStorage.isSetJWT()) {
     LocalStorage.loadJWT();
   } else {
     LocalStorage.createJWT();
   }
  }


  signup_data: { "username": any;"userid": any; "category": any;  "email": any;
  "mobile": any; "password": any,"oprn": any };
  userid: any;
  msgcolor: string
  mobile: any;
  password: any;
  msg: {};
  roleid: string;
  email: any; 
  category: string;
  oprn: string;
  token: string;
  oprnstatus: boolean ; 
  public List: any; 
  public myList: any;
  public responseType : string;
  public responseValue : {};
  public modaltitle : string;
  public modalmsg : string;
  public firstname:any;
  public lastname:any;
  public cnf_password:any;
  signupForm: FormGroup;
  submitted:boolean = false;


  ngOnInit() {
    this.oprn = "INS";
    this.userid = "0";

    this.signupForm = this.formBuilder.group({
      Category: new FormControl(''),
      Email: new FormControl('', Validators.compose([
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/),
          Validators.required
      ])),
  
      firstName: new FormControl('', Validators.compose([
          Validators.required,
      ])),
     
      lastName: new FormControl('', Validators.compose([
          Validators.required,
      ])),
      Password: new FormControl('', Validators.compose([
          Validators.required,
      ])),
      MobileNo: new FormControl('', Validators.compose([
          Validators.required,
          Validators.pattern(/^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/)
      ])),
    
      userName:new FormControl('', Validators.compose([
        Validators.required,
    ])),
   
  ConfirmPassword:new FormControl('', Validators.compose([
    Validators.required,
  ]))

    })

  }
 
  clearfields(){
    this.category =  "";
    this.username = "";
    this.email =  "";
    this.mobile =  ""; 
    this.password =  "";
    this.oprn = "INS";
  }

  public save(){      
    this.submitted = true;
    this.signup_data = { "username": this.username,"mobile":this.mobile,"category":this.category,
    "email": this.email,"password":this.password,"userid":this.userid,"oprn":this.oprn};
    console.log(this.signup_data)
    //return false;
    if(this.signupForm.valid){
      if(this.cnf_password != this.password){
        this.modalmsg = "Password  doesnot match";
        this.modaltitle = "Error";
        const dialogManualLogin = this.dialog.open(this.alertDialog);
        return false;
      }
   this.server.post( "onlineexamine/forms/signup", this.signup_data, false)
   .then((data) => { 
    if(data.status==200){ 
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
           this.clearfields();
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
           this.router.navigateByUrl('/');
     
       }
   }  
     
 });  
    }
}

closemodal(){
  this.clearfields(); 
  this.dialog.closeAll();
 }
 get f() { return this.signupForm.controls; }
}
