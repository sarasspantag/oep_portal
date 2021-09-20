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

@Component({
  selector: 'app-reports-faculty',
  templateUrl: './reports-faculty.component.html',
  styleUrls: ['./reports-faculty.component.scss']
})
export class ReportsFacultyComponent implements OnInit {
  barpercentage: number = 1;
  categorypercentage: number = 1;
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
  result_course: any;
  viewroleid: any;

  constructor(private server: HyperService, private masterservice: MasterService,
    private router: Router) { }

  ngOnInit() {
      this.viewroleid = LocalStorage.getValue("roleid");
      this.showchart = false;
      this.getcoursedetails();
      this.showreport = true;
      this.showreset = false;
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


    });

}
  getcoursedetails() {
    this.showreport = true;
    this.showreset = false;
    this.showchart = false;
    this.server.get("onlineexamine/forms/getgetcoursedetailsforreport/0/"+LocalStorage.getValue("roleid"))
      .then((data) => {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        console.log(this.responseValue)
        this.courselist = this.responseValue;
        //this.testlist = {};
      });
  }

  generatefacultyreport() {
    this.isLoading = true;
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
    if(this.roleid == "1"){
        this.userid = this.id;
    }else if(this.roleid == "2"){
        this.userid = this.facultyid;
    }else if(this.roleid == "4"){
      this.userid = LocalStorage.getValue("userid");
    }
    this.server.get("onlineexamine/forms/generatefacultyreport/" + this.batch +"/"+LocalStorage.getValue("roleid")+"/"+this.userid+"/"+this.testid)
      .then((data) => {
        this.isLoading = false;
          console.log(data)
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        //console.log(this.responseValue["reportslist"])

    if(this.responseValue["facultyDetails"].length != 0){
      this.batchlength = this.responseValue["facultyList"].length;
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


      const object = this.responseValue["facultyDetails"][0][0];
      this.result_course = object["coursename"];
      this.result_testid = object["testname"];


        for (let i = 0; i < this.responseValue["facultyList"].length; i++) {
            this.barChartLabels.push(this.responseValue["facultyList"][i]["facultyname"]);
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

}