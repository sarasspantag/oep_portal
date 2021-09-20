import { HyperService } from '../core/http.service';
import { Injectable } from '@angular/core';
import {MasterService} from '../core/master.service';
import { LocalStorage } from '../core/local_storage.service';


@Injectable()
export class SocketService{
    constructor(private server: HyperService, private masterservice: MasterService) {
        if (LocalStorage.isSetJWT()) {
          LocalStorage.loadJWT();
        } else {
          LocalStorage.createJWT();
        }
       }
      //  public login_data =  {"Username":"guest_user","Password":"admin@123","Applntype":1};
       public login_data =  {"username":"guest@gmail.com","password":"admin@123"};
       public responseType : string;
       public responseValue : {};
       public userObject : {};
       public categoryurl : any;
    init(){
       // console.log("saras");

        this.server.post("onlineexamine/forms/validLogin",this.login_data,false)
        .then((data) => {
          if (data.status == 200) {
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            if(this.responseValue["tokenkey"] != null){
              this.userObject = this.responseValue["userDetails"][0];
              LocalStorage.setValue('token', this.responseValue["tokenkey"]);
             // console.log( this.userObject)
              // LocalStorage.setValue('currency_code', this.userObject["currencycode"]);
              // LocalStorage.setValue('location', this.userObject["storeid"]);
            }
           
          }
        });
      
  }
}