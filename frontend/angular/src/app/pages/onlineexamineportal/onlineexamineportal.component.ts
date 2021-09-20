import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-onlineexamineportal',
  templateUrl: './onlineexamineportal.component.html',
  styleUrls: ['./onlineexamineportal.component.scss']
})
export class OnlineexamineportalComponent implements OnInit {
  user: any;

  constructor(private server: HyperService, private masterservice: MasterService,
    private router: Router) {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
   }

   public showLoader : boolean;
   public showContent : boolean;
   public hidereturn:boolean;
   public hidecancel:boolean;
   public ratethisproduct :boolean;
   public orderid : string;
   public customerid : string;
   public productid : string;
   menuurl : string;
   public responseType : any;
   public length : any;
   public responseValue : any;
   public menuList = [];
   public orderamountList = [];
   public customerList = [];
   public currency_code : string;
   public usermenu : any;



  ngOnInit() {
    this.user = LocalStorage.getValue("username");
    const logged = interval(200).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
    
          this.getMenuList();
          logged.unsubscribe();
        } 
      } 
    );

  }
   

  public getMenuList(){

     this.menuurl = "onlineexamine/forms/getMenuList/"+LocalStorage.getValue("roleid");
 
     this.server.get(this.menuurl) 
    .then((data) => {
   
    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);
    
    this.menuList =this.responseValue;
   // console.log(this.menuList)
    this.showLoader = false;
    this.showContent = true;
   
  });

  }
}
  