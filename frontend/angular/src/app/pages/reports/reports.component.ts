import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { MatDialog, MatDatepickerInputEvent, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { Router } from '@angular/router';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/core/dateformat';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {FormBuilder, FormGroup, Validators,FormControl} from "@angular/forms";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {
  barpercentage: number = 0.5;
  categorypercentage: number = 0.5;
  public showreport:boolean;
	public showreset:boolean;
  public barChartOptions: ChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
  
    // We use these empty structures as placeholders for dynamic theming.
    scales: { 
      xAxes: [{
      barPercentage: this.barpercentage,
      categoryPercentage: this.categorypercentage
      }
    ],
      yAxes: [{
        ticks: {
          beginAtZero:true,
          max: 100,
          padding:20,
          stepSize:25
        }
      }] },
    hover: {mode: null},
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    },
   
    legend:{
      position: 'bottom',
      labels :{
        padding:20,
        boxWidth:15
      }
      
    },

  };
  public barChartLabels: Label[] = [];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];
  
  public barChartData: any[] = [
    { data: [0] }
  ];




  public responseType: string;
  public responseValue: {};
  courselist: any;
  facultylist: any;
  batch: any;
  highmarks = [];
  cumulative = [];
  highscore = [];
  cumulativecolor = [];
  highmarkscolor = [];
  highscorecolor =[];
 showchart:boolean;
 showfaculty:boolean;
 facultyid:any;
 id:any;
 roleid:any;
 userid:string;
 isLoading = false;
  public result_faculty : string;
  public result_testid : any;
  public result_batch : any;
  public result_partcount : any;
  public result_date : any;
  public result_duration : any;
  chartavailable: boolean = true;
  testlist: {};
  testid: string;
  batchlength: any;
  viewroleid: any;
  reportForm: FormGroup;
  submitted:boolean = false;

  constructor(private server: HyperService, private masterservice: MasterService,
    private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.viewroleid = LocalStorage.getValue("roleid");
    this.showchart = false;
    if(LocalStorage.getValue("roleid") ==1 ){
      this.showfaculty = true;
        this.server.get("onlineexamine/forms/getfacultylistforreport")
          .then((data) => {
    
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            console.log(this.responseValue)
            this.facultylist = this.responseValue;
          });
      }else if(LocalStorage.getValue("roleid") ==3){
        this.showfaculty = true;
        this.server.get("onlineexamine/forms/getfacultylistforreport")
          .then((data) => {
    
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            console.log(this.responseValue)
            this.facultylist = this.responseValue;
          });
      }else{
        this.showfaculty = false;
        this.server.get("onlineexamine/forms/getfacultyidforreport/"+LocalStorage.getValue("userid") +"/"+LocalStorage.getValue("roleid"))
        .then((data) => {
      
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
         this.facultyid=this.responseValue[0]["facultyid"]
         console.log( this.facultyid)
         this.getcoursedetailsfacultylogin();
        });
      }
      this.showreport = true;
      this.showreset = false;

      this.submitted=false;
      this.reportForm = this.formBuilder.group({
        Facultyname: new FormControl('', Validators.compose([
            Validators.required,
        ])),
       
        Batch: new FormControl('', Validators.compose([
            Validators.required,
        ])),
        Test: new FormControl('', Validators.compose([
            Validators.required,
        ]))
      })

  }
getcoursedetailsfacultylogin(){
  
  this.barChartData =[{ data: [0] }];
  this.barChartLabels = [];
  this.showchart = false;
  console.log(this.facultyid)
  this.server.get("onlineexamine/forms/getgetcoursedetailsforreport/" + this.facultyid +"/"+LocalStorage.getValue("roleid"))
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      console.log(this.responseValue)
      this.courselist = this.responseValue;

''
    });

}
  getcoursedetails(facultyid) {
    this.showreport = true;
    this.showreset = false;
    this.showchart = false;
    this.server.get("onlineexamine/forms/getgetcoursedetailsforreport/" + facultyid +"/"+LocalStorage.getValue("roleid"))
      .then((data) => {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        console.log(this.responseValue)
        this.courselist = this.responseValue;
        //this.testlist = {};
      });
  }

  generatebatchreport() {
    this.submitted = true;

    this.showreport = false;
    this.showreset = true;

    this.barChartData =[{ data: [0] }];
    this.barChartLabels = [];
    this.highmarks = [];
    this.highmarkscolor = [];
    this.cumulative=[];
    this.cumulativecolor = [];
    this.highscore=[];
    this.highscorecolor=[];
    this.showchart = true;
    console.log(this.batch)

    this.roleid = LocalStorage.getValue("roleid");
    if(this.roleid == "1" ){
        this.userid = this.id;
    }else if(this.roleid == "3"){
      this.userid = this.id;
    }
    else if(this.roleid == "2"){
        this.userid = this.facultyid;
    }else if(this.roleid == "4"){
      this.userid = LocalStorage.getValue("userid");
    }
    if(LocalStorage.getValue("roleid") == 2 || LocalStorage.getValue("roleid") == 4){
      this.reportForm.controls.Facultyname.setValue("faculty")
    }
    if(this.reportForm.valid){
      this.isLoading = true;
    this.server.get("onlineexamine/forms/generatebatchreport/" + this.batch +"/"+LocalStorage.getValue("roleid")+"/"+this.userid+"/"+this.testid)
      .then((data) => {
        this.isLoading = false;
        this.submitted=false;
          console.log(data)
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        //console.log(this.responseValue["reportslist"])

    if(this.responseValue["batchDetails"].length != 0){
      this.batchlength = this.responseValue["batchList"].length;
      if(this.batchlength == 1 || this.batchlength == "1"){
        this.barpercentage = 0.4;
        this.categorypercentage = 0.1; 
      }else if(this.batchlength == 2|| this.batchlength == "2"){
        this.barpercentage = 0.4;
        this.categorypercentage = 0.1;
      }else{
       this.barpercentage = 0.5;
       this.categorypercentage = 0.5;
      }


      const object = this.responseValue["batchDetails"][0][0];
      this.result_faculty = object["facultyname"];
      this.result_testid = object["testid"];
      this.result_batch = object["schedulename"];
      this.result_partcount = object["partcount"];
      this.result_date = object["testdate"];
      this.result_duration = object["duration"];

        for (let i = 0; i < this.responseValue["batchList"].length; i++) {
            this.barChartLabels.push(this.responseValue["batchList"][i]["courseschedule"]);
        }

        for (let i = 0; i < this.responseValue["reportslist"].length; i++) {
          if(this.responseValue["reportslist"][i]["highmark"] != 0 && this.responseValue["reportslist"][i]["cumulative"] != null){
            this.highmarks.push(this.responseValue["reportslist"][i]["highmark"]);
            this.highmarkscolor.push(this.responseValue["reportslist"][i]["highmarkcolor"]);
            this.cumulative.push(this.responseValue["reportslist"][i]["cumulative"]);
            this.cumulativecolor.push(this.responseValue["reportslist"][i]["cumulativecolor"]);
            this.highscore.push(this.responseValue["reportslist"][i]["score"]);
            this.highscorecolor.push(this.responseValue["reportslist"][i]["scorecolor"]);
            console.log(this.highmarks)
          }
        }
        this.chartavailable = true;
      }else{
        this.chartavailable = false;
      }
      this.barChartOptions.scales.xAxes = [{
        barPercentage: this.barpercentage,
        categoryPercentage:this.categorypercentage
        }];
      this.barChartData = [{ data: this.highmarks,borderWidth: 0,scaleStepWidth:1, backgroundColor:this.highmarkscolor,label: 'High Marks' },
      { data: this.cumulative,borderWidth: 0, scaleStepWidth:1, backgroundColor:this.cumulativecolor,label: 'Cumulative Percentage'}];
      console.log(this.barChartData)
      console.log(this.responseValue);

      });
      // this.barChartData = [{ data: this.highmarks,borderWidth: 1,scaleStepWidth:1, backgroundColor:this.highmarkscolor,label: 'High Marks' },
      // { data: this.cumulative,borderWidth: 1, scaleStepWidth:1, backgroundColor:this.cumulativecolor,label: 'Cumulative Percentage'},
      //  { data: this.highscore,borderWidth: 1,scaleStepWidth:1, backgroundColor:this.cumulativecolor,label: 'High Score' }];
     
      this.barChartData = [{ data: this.highmarks,borderWidth: 0,scaleStepWidth:1, backgroundColor:this.highmarkscolor,label: 'High Marks' },
      { data: this.cumulative,borderWidth: 0, scaleStepWidth:1, backgroundColor:this.cumulativecolor,label: 'Cumulative Percentage'}];
      
      console.log(this.barChartData)
      console.log(this.responseValue);
    }

  }

  reset(){
    this.facultyid="";
    this.id="";
    this.batch="";
    this.testid="";
    this.ngOnInit();
    }
    
    myreset(){
      this.showreport = true;
      this.showreset = false;
      this.showchart = false;
    }

    gettestdetails(id){
      this.showreport = true;
      this.showreset = false;
      this.showchart = false;
      this.server.get("onlineexamine/forms/gettestdetails/" + id +"/"+LocalStorage.getValue("roleid"))
      .then((data) => {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        console.log(this.responseValue)
        this.testlist = this.responseValue;
      });
    }
    get f() { return this.reportForm.controls; }
}