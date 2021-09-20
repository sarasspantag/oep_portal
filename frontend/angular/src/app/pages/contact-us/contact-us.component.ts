import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { LocalStorage } from 'src/app/core/local_storage.service';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.scss']
})
export class ContactUsComponent implements OnInit {

  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  username: any;
  mobile: any;
  email: any;
  userid: any;
  oprn: any;
  public myList: any;
  public responseType : string;
  public responseValue : {};
  public modaltitle : string;
  public modalmsg : string;
  msgcolor: string
  content: any;

  constructor(private server: HyperService, private masterservice:MasterService,
    private dialog: MatDialog, private router: Router) {
   if (LocalStorage.isSetJWT()) {
     LocalStorage.loadJWT();
   } else {
     LocalStorage.createJWT();
   }
  }

  signup_data: { "username": any;"userid": any;  "email": any; "content": any; "mobile": any; "oprn": any };

  ngOnInit() { 
  }


  submit(){
    this.userid = LocalStorage.getValue("userid");

    if(this.mobile == null || this.mobile == undefined || this.mobile == "" ){
        this.modaltitle="Error";
        this.modalmsg = "Enter valid mobile number";
        const dialogManualLogin = this.dialog.open(this.alertDialog);
    }else if(this.email == null || this.email == undefined || this.email == ""){
      this.modaltitle="Error";
      this.modalmsg = "Enter valid email ";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
    }else{

        this.signup_data = { "username": this.username,"mobile":this.mobile,"content": this.content,
        "email": this.email,"userid":this.userid,"oprn":"INS"};

        this.server.post( "onlineexamine/forms/savecontactus", this.signup_data, false)
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
                }else{}
               const dialogManualLogin = this.dialog.open(this.alertDialog);
               this.router.navigateByUrl('/contact-us');
           
                  }
               }  
           
               });  
               }
  }

  clearfields(){
    
    this.username = "";
    this.email =  "";
    this.mobile =  ""; 
    this.content =  "";
    this.oprn = "INS";
  }

  closemodal(){
    this.clearfields(); 
    this.dialog.closeAll();
   }

}
