import { Component, OnInit } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from '../../core/http.service';
import { MasterService } from '../../core/master.service';
@Component({
  selector: 'app-popup-modal',
  templateUrl: './popup-modal.component.html',
  styleUrls: ['./popup-modal.component.scss']
})

export class PopupModalComponent implements OnInit {

  constructor(private server: HyperService, private masterservice: MasterService) { }
  public register_data: any;
  public responseValue: {};
  public responseType: {};
  otp: any;
  msgcolor: any;
  responsemsg: any;
  oprn:any;
  ngOnInit() {
  
    if(LocalStorage.getValue("fromlogin") ==1){
      this.oprn="VERIFY";
    }else{
      this.oprn="INS";
    }
  }
  registerverifycustomer() {

    this.register_data = { 
      "firtname": LocalStorage.getValue("firtname"),
      "lastname": LocalStorage.getValue("lastname"),
      "country": LocalStorage.getValue("country"),
      "state": LocalStorage.getValue("state"),
      "city": LocalStorage.getValue("city"),
      "street": LocalStorage.getValue("street"),
      "landmark": LocalStorage.getValue("landmark"),
      "fulladdress": LocalStorage.getValue("fulladdress"),

      "email": LocalStorage.getValue("email"),
       "Username": LocalStorage.getValue("Username"),
        "mobileno": LocalStorage.getValue("mobileno"),
         "otpid": LocalStorage.getValue("otp"),
          "otp": this.otp,"oprn":this.oprn,
           "address":LocalStorage.getValue("address")};
    console.log(this.register_data);
  //  return false;
    this.server.post("herostore/forms/registerverifycustomer", this.register_data, false)
      .then((data) => {
        //console.log(data)
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
          if (this.responseValue["out_result_type"]  == 'S') {
            
            LocalStorage.setValue('customerid', this.responseValue["out_genrate_id"]);
          //  console.log(this.responseValue)
            this.responsemsg = this.responseValue["out_result_msg"];
            this.msgcolor = "green";
          } else {
            this.responsemsg = this.responseValue["out_result_msg"];
            this.msgcolor = "red";
          }
        }
      });

  }
}
