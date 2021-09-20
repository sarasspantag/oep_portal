import { Component, OnInit, ViewChild, TemplateRef, EventEmitter, Output, Input } from '@angular/core';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';

import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { MatDialog, MatDialogRef, DateAdapter, MAT_DATE_FORMATS, MatDatepickerInputEvent } from "@angular/material";
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/core/dateformat';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { MasterService } from 'src/app/core/master.service';
import {FormControl} from '@angular/forms';
export interface Applicable {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-facultyprofile',
  templateUrl: './facultyprofile.component.html',
  styleUrls: ['./facultyprofile.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class FacultyprofileComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @Input() initDate: Date;
  @Output() newDatePickedEvent: EventEmitter<Date> = new EventEmitter();
  public responseType: string;
  public responseValue: {};
  public facultyprofileview : any;
  public userdata: any;
  firstname: any;
  lastname: any;
  username: any;
  password: any;
  email: any;
  contactno: any;
  gender: any;
  subjectname: any;
  courseid: any;
  subjectid: any;
  dob: any;
  qualification: any;
  public image_URL: string = CONFIG._imageurl;
  occupation: any;
  experience: any;
  main_subject: any;
  ref_no: any;
  oprn: string;
  msgcolor: string;
  public modaltitle: string;
  public modalmsg: string;
  isfaculty: boolean;
  isparticipant: boolean;
  public experienceForm: FormGroup;
  public educationForm: FormGroup;
  public facultyForm:FormGroup;
  public skillsForm: FormGroup;
  experienceobjectarray: {}
  educationobjectarray: {};
  skillsobjectarray: {};
  startdate: any;
  enddate: any;
  educationstartdate: any;
  educationtodate: any;
  someDate: Date;
  coursename: any;
  CourseList: {};
  courseseleted: boolean;

  dateslice:any;
  date_dob;
  ApplicableIc: Applicable[] = [
    {value: '1', viewValue: 'Applicable IC-1'},
    {value: '2', viewValue: 'Applicable IC-2'},
    {value: '3', viewValue: 'Applicable IC-3'}
  ];
  submitted:boolean = false;
  errormsg: string;
  fielddob: Date;
  edustartdate: any;
  edufieldstartdate: any;

  constructor(private server: HyperService, private masterservice: MasterService,
    private router: Router, private dialog: MatDialog, private _fb: FormBuilder) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  }

  ngOnInit() {
    this.courseseleted = false ; 
    LocalStorage.setValue("id", 1);
    LocalStorage.setValue("type", 1);
    LocalStorage.setValue("form", "faculty");
    LocalStorage.setValue("foldername", "facultyimage");
    LocalStorage.setValue("sessionname", "facultyimage");
    this.getCourseList(); 
    this.experienceForm = this._fb.group({
      experienceitemRows: this._fb.array([this.initexperienceItemRows()])
    });
    this.educationForm = this._fb.group({
      educationitemRows: this._fb.array([this.initeducationItemRows()])
    });
    this.skillsForm = this._fb.group({
      skillsitemRows: this._fb.array([this.initskillsItemRows()])
    });

    
    this.facultyForm = this._fb.group({
      firstName: new FormControl('', Validators.compose([
        Validators.required,
    ])),
      lastName: new FormControl(''),
      middleName: new FormControl(''),
  
      Gender: new FormControl('', Validators.compose([
          Validators.required,
      ])),
     
      DOB: new FormControl('', Validators.compose([
        Validators.required,
    ])),
      EmpId: new FormControl('', Validators.compose([
          Validators.required,
      ])),
      Email: new FormControl('', Validators.compose([
        Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/),
        Validators.required
    ])),
    
      Profession:new FormControl('', Validators.compose([
        Validators.required,
    ])),
   
    ContacctNo:new FormControl('', Validators.compose([
      Validators.required,
      Validators.pattern(/^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/)
  ])),
  Department:new FormControl('', Validators.compose([
    Validators.required,
])),
CourseId:new FormControl(''),
ApplicableIC:new FormControl('', Validators.compose([
  Validators.required,
])),
dESC:new FormControl('')

    })
    if (LocalStorage.getValue("roleid") == 2) {
      this.server.get("onlineexamine/forms/getFacultydetails/" + LocalStorage.getValue("userid"))
        .then((data) => {
          if (data.status == 200) {
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
           console.log(this.responseValue)
            this.facultyprofileview = this.responseValue["facultydetailsList"];
            console.log(this.facultyprofileview)
           // this.courseid = this.facultyprofileview["main_subject"];
            if(this.facultyprofileview["main_subject"] != null && this.facultyprofileview["main_subject"] != "undefined"){
             this.courseid = this.facultyprofileview["main_subject"];
            }
         if(this.facultyprofileview["dateofbirth"] !=null && this.facultyprofileview["dateofbirth"] !=undefined){
          let date = new Date(this.facultyprofileview["dateofbirth"]);
        
          this.dob = this.facultyprofileview["datedob"];
          this.fielddob=date
          this.facultyForm.controls.DOB.setValue(this.fielddob)
         }
            
           
           // console.log("--------------------------------")
           // console.log(this.responseValue["educationdetailsList"])
            const resp = { "skills": this.responseValue["skilladetailsList"] }
            const items = this.skillsForm.get('skillsitemRows') as FormArray;

            if(this.responseValue["skilladetailsList"]!=null && 
            typeof this.responseValue["skilladetailsList"] != undefined && 
            this.responseValue["skilladetailsList"].length>0){
              while (items.length) {
                items.removeAt(0);
              }
            }
            this.skillsForm.patchValue(resp);
            resp.skills.forEach(item => items.push(this._fb.group(item)));

            const expresp = { "experience": this.responseValue["experiencedetailsList"] }
            const expitems = this.experienceForm.get('experienceitemRows') as FormArray;
            if(this.responseValue["experiencedetailsList"]!=null && 
            typeof this.responseValue["experiencedetailsList"] != undefined && 
            this.responseValue["experiencedetailsList"].length>0){
              while (expitems.length) {
                expitems.removeAt(0);
              }
            }
            this.experienceForm.patchValue(expresp);
            expresp.experience.forEach(expitem => {
              expitem.startdate=this.sanitizeDate(expitem["startdate"])
              expitem.fieldstartdate= new Date(expitem["fieldstartdate"])
              expitem.enddate=this.sanitizeDate(expitem["enddate"])
              expitem.fieldenddate=new Date(expitem["fieldenddate"])
              expitems.push(this._fb.group(expitem))
            }
              );
            
            const eduresp = { "education": this.responseValue["educationdetailsList"] }
            const eduitems =this.educationForm.get('educationitemRows') as FormArray;
            if(this.responseValue["educationdetailsList"]!=null && 
            typeof this.responseValue["educationdetailsList"] != undefined && 
            this.responseValue["educationdetailsList"].length>0){
              while (eduitems.length) {
               
                eduitems.removeAt(0);
              }
            }
            this.educationForm.patchValue(eduresp);
            eduresp.education.forEach(eduitem =>{
            
              eduitem.educationstartdate=this.sanitizeDate(eduitem["educationstartdate"])
              eduitem.educationstartfielddate = new Date(eduitem["educationfieldstartdate"])
              eduitem.educationtodate=this.sanitizeDate(eduitem["educationtodate"])
              eduitem.educationtofielddate=new Date(eduitem["educationfieldenddate"])
              console.log(eduitem)
              eduitems.push(this._fb.group(eduitem))
          }
            );
          }
        });
    } else { }
  }
  startDatePicked(event: MatDatepickerInputEvent<Date>): void {
    this.newDatePickedEvent.emit(event.value);
    this.startdate = event.targetElement["value"];
  }
  endDatePicked(event: MatDatepickerInputEvent<Date>): void {
    this.newDatePickedEvent.emit(event.value);
    this.enddate = event.targetElement["value"];
  }
  educationstartdatePicked(event: MatDatepickerInputEvent<Date>): void {
    this.newDatePickedEvent.emit(event.value);
    this.educationstartdate = event.targetElement["value"];
  }
  
  educationtodatePicked(event: MatDatepickerInputEvent<Date>): void {
    this.newDatePickedEvent.emit(event.value);
    this.educationtodate = event.targetElement["value"];
  }
  dobPicked(event: MatDatepickerInputEvent<Date>): void {
    console.log(event.value);
    this.newDatePickedEvent.emit(event.value);
    this.dob = event.targetElement["value"];
    
  }
  get f() { return this.facultyForm.controls; }
  initexperienceItemRows() {
    return this._fb.group({
      designation: [''],
      companyname: [''],
      job: [''],
      startdate: [''],
      enddate: [''],
      fieldstartdate:[''],
      fieldenddate:['']
    });
  }
  initeducationItemRows() {
    return this._fb.group({
      qualification: [''], 
      course: [''],
      specification: [''],
      university: [''],
      educationstartdate: [''],
      educationstartfielddate: [''],
      educationtodate: [''],
      educationtofielddate: ['']
    });
  }
  initskillsItemRows() {
    return this._fb.group({
      skill: [''],
      efficiency: ['']
    });
  }
  get experienceformArr() {
    return this.experienceForm.get('experienceitemRows') as FormArray;
  }
  get educationformArr() {
    return this.educationForm.get('educationitemRows') as FormArray;
  }
  get skillsformArr() {
    return this.skillsForm.get('skillsitemRows') as FormArray;
  }
  addNewexperienceRow() {
    this.experienceformArr.push(this.initexperienceItemRows());

  }
  addNeweducationRow() {
    this.educationformArr.push(this.initeducationItemRows());

  }
  addNewskillsRow() {
    this.skillsformArr.push(this.initskillsItemRows());

  }
  public getCourseList() {

    this.server.get("onlineexamine/forms/getCourseList")
      .then((data) => {

        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        this.CourseList = this.responseValue;
        this.courseid = this.CourseList["courseid"];
        this.coursename = this.CourseList["coursename"];

      });
  }
  sanitizeDate(date: string): string {
    if (!date) {
      return null;
    }
  
    const dataArray = date.split('/');
    const day = Number(dataArray[0]) ;
    const month = Number(dataArray[1]) ;
    const year = Number(dataArray[2]);
    // console.log(dataArray)
    // console.log(month)
    // console.log(day)
    // console.log(year)
    // console.log(year+"-"+month+"-"+day)
   
    return year+"-"+month+"-"+day;
  }


  updateprofile() {
    this.submitted = true;
    this.experienceobjectarray = this.experienceForm.value;
    this.educationobjectarray = this.educationForm.value;
    this.skillsobjectarray = this.skillsForm.value;
      this.dob=this.sanitizeDate(this.dob)

    this.userdata = {
      "facultyid": LocalStorage.getValue("userid"), "firstname": this.facultyprofileview["firstname"], "lastname": this.facultyprofileview["lastname"], "username": this.facultyprofileview["username"],
      "password": this.facultyprofileview["password"], "email": this.facultyprofileview["email"], "contactno": this.facultyprofileview["contactnumber"], "gender": this.facultyprofileview["gender"],
      "dob": this.dob, "qualification": this.facultyprofileview["qualification"], "occupation": this.facultyprofileview["profession"], "experience": this.facultyprofileview["experience"],
      "mainsubject": this.courseid, "refno": this.facultyprofileview["employeeid"], "oprn": "UPD", "filename": LocalStorage.getValue("filename"),
      "middlename": this.facultyprofileview["middlename"],"description": this.facultyprofileview["description"],
      "applicableic": this.facultyprofileview["applicableic"],
      "experiencearray": JSON.stringify(this.experienceobjectarray["experienceitemRows"]),
      "educationarray": JSON.stringify(this.educationobjectarray["educationitemRows"]),
       "skillsarray": JSON.stringify(this.skillsobjectarray["skillsitemRows"])
    };
    console.log(JSON.stringify(this.experienceobjectarray["experienceitemRows"]))
    console.log(JSON.stringify(this.educationobjectarray["educationitemRows"]))

   if(this.facultyForm.valid){
    
  //  console.log(this.userdata)
  //  return false;
    this.server.post("onlineexamine/forms/savefacultydetails", this.userdata, false)
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
          if (this.responseType == 'F') {
            this.msgcolor = "red";

          } else if (this.responseType == 'S') {

            this.msgcolor = "green"

            this.modaltitle = "Success";
            if (this.oprn = "INS") {
              this.modalmsg = "Faculty Details  saved successfully";
            } else if (this.oprn = "UPD") {

              this.modalmsg = "Faculty Details updated successfully";
            } else if (this.oprn = "DEL") {

              this.modalmsg = "Faculty Details deleted successfully";
            } else {

            }
            const dialogManualLogin = this.dialog.open(this.alertDialog);
            //this.router.navigateByUrl('/oeep/dashboard');

          } else {

            this.msgcolor = "red";
          }

        }

      });
    }
  }
  closemodal() {
    window.location.reload();
    this.dialog.closeAll();
  }

}
