import { Component, OnInit } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { interval } from 'rxjs';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  
  isfaculty : boolean;
  isparticipant : boolean;
  public logged_session :any;
  isNotLogged : boolean;
  isDashboardShow : boolean;
  public login_data =  {"username":"guest@gmail.com","password":"admin@123"};
  public userObject : {};
  public responseType : string;
  public responseValue : {};

  constructor(private location:Location, private router: Router,
    private server: HyperService,private masterservice: MasterService) { 
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  }

  ngOnInit() {
    this.logged_session = LocalStorage.getValue("userid");

    const logged = interval(2000).subscribe(
      res => {
  
    if(typeof this.logged_session != 'undefined' && this.logged_session != null && this.logged_session > 0){

      if( LocalStorage.getValue("roleid") == 2){
        this.isfaculty = true;
        this.isparticipant = false;
        //console.log( this.isfaculty )
        }else if(LocalStorage.getValue("roleid") == 4){
              this.isfaculty = false;
              this.isparticipant = true;
             // console.log(this.isparticipant )
        }else {
          this.isfaculty = false;
          this.isparticipant = false;
          this.isNotLogged = false;
         // console.log(this.isparticipant )
        }
    }else{
        this.isNotLogged = true;
        this.isfaculty = false;
        this.isparticipant = false;
    }
    let url = location.href.includes('oeep')
    if(url==true){
     if(LocalStorage.getValue("userid") != 'undefined' && LocalStorage.getValue("userid") !=null 
     && LocalStorage.getValue("userid") >0) {

     }else{
      this.router.navigateByUrl('/errorpage'); 
     }
    }
    this.getrefreshdata();
      
    });
    }

   public getrefreshdata(){
         if(LocalStorage.getValue("userid") !=null && LocalStorage.getValue("userid") > '0' ){
           if(LocalStorage.getValue("roleid") != 5){
            this.isNotLogged = false;
            this.isDashboardShow = false;
           }else{
            this.isNotLogged = false;
            this.isDashboardShow = true;
           }
         }else{
           this.isNotLogged = true;
           this.isDashboardShow = true;
         }
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
  }


