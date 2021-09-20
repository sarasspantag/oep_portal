 import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
 import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service'; 
import { MasterService } from 'src/app/core/master.service';
 import { Router } from '@angular/router';
import { DatePipe } from '@angular/common'
 import {  MatDialog, MatDialogRef} from "@angular/material";
import { CONFIG } from '../../config';
import {EventEmitter,Output,Input } from '@angular/core';
import { DateAdapter,MAT_DATE_FORMATS,MatDatepickerInputEvent} from "@angular/material";
import { AppDateAdapter, APP_DATE_FORMATS} from 'src/app/core/dateformat';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import {FormControl} from '@angular/forms';
export interface Applicable {
  value: string;
  viewValue: string;
}
 
export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String; 
}
@Component({
  selector: 'app-participant-profile',
  templateUrl: './participant-profile.component.html',
  styleUrls: ['./participant-profile.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class ParticipantProfileComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @Input() initDate: Date;
  @Output() newDatePickedEvent: EventEmitter <Date> = new EventEmitter();
  public responseType: string;
  public responseValue: {};
  public profileview : any;
  public userdata:any;
  firstname:any;
  lastname:any;
  username:any;
  password:any;
  email:any;
  public image_URL: string = CONFIG._imageurl;
  contactno:any;
  gender:any;
 // subjectname: any;
  courseid: any;
 // subjectid: any;
  dob:any;
  qualification:any;
  occupation:any;
 // SubjectList:any;
  experience:any;
 // main_subject:any;
  ref_no:any;
  oprn: string;
  msgcolor: string;
  public transactions: Transaction[];
  public modaltitle : string;
public modalmsg : string;
isfaculty : boolean;
isparticipant : boolean;
  List: {};
  myList: string;
  address: any;
  pincode: any;
  imagepath: any;
  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];
  
  public educationForm: FormGroup;
  public skillsForm:FormGroup;
  experienceobjectarray:{}
  educationobjectarray:{};
  skillsobjectarray:{};
  startdate:any;
  enddate:any;
  educationstartdate:any;
  educationtodate:any;
  someDate: Date;
  submitted:boolean = false;
  errormsg: string;
  public participantForm:FormGroup;
  fielddob: any;
  constructor(private server: HyperService, private masterservice:MasterService,
  private router: Router,public datepipe: DatePipe, private dialog: MatDialog,private _fb: FormBuilder) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else { 
      LocalStorage.createJWT();
    }
   }

  ngOnInit() {
    
    LocalStorage.setValue("id",1);
    LocalStorage.setValue("type",2);
    LocalStorage.setValue("form","product");
    LocalStorage.setValue("foldername","participantimage");
    LocalStorage.setValue("sessionname","productimage");
   this.getCourseList();
 
   
  this.educationForm = this._fb.group({
    educationitemRows: this._fb.array([this.initeducationItemRows()])
  });
  this.skillsForm = this._fb.group({
    skillsitemRows: this._fb.array([this.initskillsItemRows()])
  });
  
  this.participantForm = this._fb.group({
    firstName: new FormControl('', Validators.compose([
      Validators.required,
  ])),
    lastName: new FormControl(''),
    middleName: new FormControl(''),
    Gender: new FormControl('', Validators.compose([
        Validators.required,
    ])),
   
    DOB:  new FormControl('', Validators.compose([
      Validators.required,
  ])),
 
    CourseName:new FormControl(''),
    Email: new FormControl('', Validators.compose([
      Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/),
      Validators.required
  ])),
 
  ContacctNo:new FormControl('', Validators.compose([
    Validators.required,
    Validators.pattern(/^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/)
])),
Department:new FormControl(''),
StudentId:new FormControl('', Validators.compose([
  Validators.required,
])),
ApplicableIC:new FormControl('', Validators.compose([
Validators.required,
])),
dESC:new FormControl('')

  })
    if(LocalStorage.getValue("roleid")== 4){
      this.server.get("onlineexamine/forms/getParticipantdetails/" + LocalStorage.getValue("userid"))
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);         
          this.profileview = this.responseValue["participantdetailsList"];
          console.log( this.profileview)
          let date = new Date(this.profileview["dob"]);
        if(this.profileview["fielddob"] !=null && this.profileview["fielddob"] !=undefined){
          this.dob = this.profileview["fielddob"];
          console.log(this.dob)
          this.fielddob=date
          this.participantForm.controls.DOB.setValue(this.fielddob)
        }
          //console.log(  this.profileview)
          this.courseid= this.responseValue["participantdetailsList"]["courseid"];
          this.imagepath= this.responseValue["participantdetailsList"]["imagepath"];
          const resp = { "skills": this.responseValue["skilldetailsList"] }
          const items = this.skillsForm.get('skillsitemRows') as FormArray;
          if(this.responseValue["skilldetailsList"]!=null && 
          typeof this.responseValue["skilldetailsList"] != undefined && 
          this.responseValue["skilldetailsList"].length>0){
            while (items.length) {
              items.removeAt(0);
            }
          }
          this.skillsForm.patchValue(resp);
          resp.skills.forEach(item => items.push(this._fb.group(item)));

          const eduresp = { "education": this.responseValue["educationdetailsList"] }
          const eduitems =this.educationForm.get('educationitemRows') as FormArray;
          if(this.responseValue["educationdetailsList"]!=null && 
          typeof this.responseValue["educationdetailsList"] != undefined && 
          this.responseValue["educationdetailsList"].length>0){
            while (items.length) {
              items.removeAt(0);
            }
          }
          this.educationForm.patchValue(eduresp);
          eduresp.education.forEach(eduitem =>{
            eduitem.educationfieldstartdate = new Date(eduitem["educationsfieldtartdate"])
            eduitem.educationtodate=this.sanitizeDate(eduitem["educationtodate"])
            eduitem.educationtofielddate=new Date(eduitem["educationsfieldenddate"])
             eduitems.push(this._fb.group(eduitem))
             
          });
        }  
      }); 
    }

  }  
  get f() { return this.participantForm.controls; }
  public getCourseList(){ 

    this.server.get("onlineexamine/forms/getCourseList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
        
    });
  }   
 
  startDatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.startdate = event.targetElement["value"];
  }
  endDatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.enddate = event.targetElement["value"];
  }
  educationstartdatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.educationstartdate = event.targetElement["value"];
    
  }
  educationtodatePicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.educationtodate = event.targetElement["value"];
  }
  dobPicked(event: MatDatepickerInputEvent <Date> ): void {
    this.newDatePickedEvent.emit(event.value); 
    this.dob = event.targetElement["value"];
  
  }
  
  initeducationItemRows() {
    return this._fb.group({
      qualification: [''],
      course: [''],
      specification: [''],
      university: [''],
      educationstartdate: [''],
      educationtodate: [''],
      educationfieldstartdate:[''],
      educationtofielddate:['']
    });
  }
  initskillsItemRows() {
    return this._fb.group({
      skill: ['']
     
    });
  }
  
  get educationformArr() {
    return this.educationForm.get('educationitemRows') as FormArray;
  }
  get skillsformArr() {
    return this.skillsForm.get('skillsitemRows') as FormArray;
  }
  
  addNeweducationRow() {
    this.educationformArr.push(this.initeducationItemRows());
   
  }
  addNewskillsRow() {
    this.skillsformArr.push(this.initskillsItemRows());
   
  }

updateprofile(){
  this.submitted = true;
  this.educationobjectarray = this.educationForm.value ;
  this.skillsobjectarray = this.skillsForm.value ;
  
  let latest_date =this.dob;
  if(this.participantForm.valid){
   

    let latest_date =this.sanitizeDate(this.dob);
    console.log(latest_date)
     this.userdata = {"userid":LocalStorage.getValue("userid"),"firstname":this.profileview["firstname"],"lastname":this.profileview["lastname"],"username":this.profileview["username"],
     "password":this.profileview["password"],"email":this.profileview["emailid"],"regno":this.profileview["contactno"],"gender":this.profileview["gender"],
     "dob":latest_date,"refno":this.profileview["refno"],"courseid":this.courseid,"address":this.profileview["address"],
     "city":this.profileview["city"],"pincode":this.profileview["pincode"],"filename":LocalStorage.getValue("filename"),
     "middlename": this.profileview["middlename"],"description": this.profileview["description"],
     "applicableic": this.profileview["applicableic"],"employeeid": this.profileview["employeeid"],
     "educationarray":JSON.stringify( this.educationobjectarray["educationitemRows"]),
     "skillsarray":JSON.stringify( this.skillsobjectarray["skillsitemRows"]), "department": this.profileview["department"]};
     console.log(this.userdata)
     console.log(latest_date)
  this.server.post( "onlineexamine/forms/saveparticipantdetails", this.userdata, false)
  .then((data) => {
    if(data.status==200){
      this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        if (this.responseType == 'F') {
          this.msgcolor = "red";

        }else if (this.responseType == 'S') {

          this.msgcolor ="green"

            this.modaltitle="Success";
            if(LocalStorage.getValue("roleid") == "2"){
              this.modalmsg = "Faculty Details  updated successfully";
            }else if(LocalStorage.getValue("roleid") == "4"){
          
              this.modalmsg = "Participant Details updated successfully";
            }else if(this.oprn = "DEL"){
              
              this.modalmsg = " Details deleted successfully";
            }else{

            }
            const dialogManualLogin = this.dialog.open(this.alertDialog);
          this.router.navigateByUrl('/oeep/dashboard');
      
        }else {

          this.msgcolor = "red";
        } 
        
    }  
     
  }); 
 
}
}
closemodal(){
  this.dialog.closeAll();
 }
 sanitizeDate(date: string): string {
  if (!date) {
    return null;
  }

  const dataArray = date.split('/');
  const day = Number(dataArray[0]) ;
  const month = Number(dataArray[1]) ;
  const year = Number(dataArray[2]);
  console.log(dataArray)
  console.log(month)
  console.log(day)
  console.log(year)
  console.log(year+"-"+month+"-"+day)
 
  return year+"-"+month+"-"+day;
}
}
