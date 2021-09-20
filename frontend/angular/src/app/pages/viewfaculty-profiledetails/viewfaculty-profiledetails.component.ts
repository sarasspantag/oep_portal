import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { interval } from 'rxjs';
@Component({
  selector: 'app-viewfaculty-profiledetails',
  templateUrl: './viewfaculty-profiledetails.component.html',
  styleUrls: ['./viewfaculty-profiledetails.component.scss']
})
export class ViewfacultyProfiledetailsComponent implements OnInit {
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  public responseType: string;
  public responseValue: {};
  firstname:any;
  lastname:any;
  profession:any;
  employeeid:any;
  dob:any;
  email:any;
  contactno:any;
  image:any;
  configurl:any;
  experiencelist =[];
  educationlist= [];
  public loading:boolean;
  public divshow:boolean;
  constructor(private _formBuilder: FormBuilder,private server: HyperService, private masterservice:MasterService,
    private router: Router) { }

  ngOnInit() {
    this.loading =true;
    this.divshow = true;
    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.server.get("onlineexamine/forms/getFacultyprofiledetails/"+  LocalStorage.getValue("facultyprofileid"))
    .then((data) => {
      if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);  
        console.log(this.responseValue)
      this.firstname=this.responseValue["facultylist"][0]["firstname"];
      this.lastname=this.responseValue["facultylist"][0]["lastname"];
      this.profession=this.responseValue["facultylist"][0]["profession"];
      this.employeeid=this.responseValue["facultylist"][0]["employeeid"];
      this.dob=this.responseValue["facultylist"][0]["dob"];
      this.email=this.responseValue["facultylist"][0]["email"];
      this.contactno=this.responseValue["facultylist"][0]["contactno"];
      this.image=this.responseValue["facultylist"][0]["image"];
      this.configurl = CONFIG._imageurl;
      this.experiencelist = this.responseValue["experienceList"];
      this.educationlist = this.responseValue["educationList"];
      console.log( this.educationlist)
      }  
      this.loading =false;
      this.divshow = false; 
    });
    
      logged.unsubscribe();
      } 
    });

  }
}
