import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { timer } from 'rxjs';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { interval } from 'rxjs';
import { CONFIG } from '../../config';
import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import * as moment from 'moment';
import { element } from '@angular/core/src/render3/instructions';
import {formatDate} from '@angular/common';
import { Http,HttpModule, Response, RequestOptions, Headers, Request, RequestMethod } from '@angular/http';

interface myAns {
  questionid: number;
  index: number;
  questioname: string;
  option: string;
  checked?: boolean;
}

interface reivewObject {
  questionid: number;
  index:number;
  questioname: string;
  answer: string;
}

interface ansObject {
  questionid: number;
  questioname: string;
  scheduleid: number;
  partid: number;
  questionanswer: string;
}

@Component({
  selector: 'app-testsubmission',
  templateUrl: './testsubmission.component.html',
  styleUrls: ['./testsubmission.component.scss']
})

export class TestsubmissionComponent implements OnInit {
  @ViewChild('reviewDialog') reviewDialog: TemplateRef<any>;
  @ViewChild('confirmDialog') confirmDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;
  counter: any;
  hour: number;
  hours: number;
  exm_mins: number;
  exm_hour: number;
  exm_sec: number;
  exm_tke_mins: number;
  exm_tke_hrs: number;
  alerttime: any;
  tot_hrs: number;
  counter1: number;
  interval1: NodeJS.Timer;
  alert1: number;
  alerttimesec: number;
  public responseType: any;
  public responseValue: any;
  public List: any; 
  public myList: any;
  testid: any;
  batch: any;
  username: any;
  testdate: any;
  public image_URL: string = CONFIG._imageurl;
  questioncount: number;
  public questionlist:any;
  public questiondetails: {} = {
    question : "",
    questiontype : ""
  };
  public questionid : any;
  public userid : any;
  public errormsg : string;
  public index :number;
  public showSubmit :boolean;
  public myans: myAns[];
  public radioans: myAns[];
  public myansList:Array<myAns[]> = [];
  public availableQnIds =[];
  public availableAnsIds =[];
  public checked:boolean;
  public modaltitle:string;
  public selectedAnsw:string;
  public finalAnsw:string;
  public reivewObject : reivewObject ={
    questioname :"",
    index:0,
    questionid : 0,
    answer : ""
  };
  public reivewList: reivewObject[] = [];
  public modalmsg:string; 
  public ansObject : ansObject ={
    questioname :"",
    questionid : 0,
    scheduleid : 1,
    partid :1,
    questionanswer : ""
  };
  public ansList: ansObject[] = [];
  public ans_data:{} = {};
  duration: any;
  starttme:any;
  endtime:any;
  timertime:any;
  testtime:any;
  showmark:boolean;
  total_mark : string;
  part_mark : string;
  ispass: number = 0;
  isexamstarted : boolean;
  isexamnotstarted : boolean;
  scheduleid:string;
  participantname:any;
  participanttestdate:any;
  testduration:any;
  coursebatch:any;
  batch_1:any;
  test_hour:number = 0;
  test_minute:number = 0;
  mark: any;
  saras: string;
  localtime: Date;
  single_ans: { };
  singleFinalAns : string;
  nextquestionid: any;
  constructor(private server: HyperService, private masterservice:MasterService, 
    private router: Router,private dialog: MatDialog,public http: Http ) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    } 
   } 

  ngOnInit() {
   // this.countOn(2, 30);
    this.reloadConfirm();
    this.username=LocalStorage.getValue("username");
    this.nextquestionid = 0;
    
    const logged = interval(200).subscribe(
      res => {
        this.localtime = new Date();
      } 
    );

    this.questionid = 1;
    this.userid = LocalStorage.getValue("userid");
    this.scheduleid = LocalStorage.getValue("participanttsid");
    this.index = 0;
    this.getQestionList();
    this.checked =false;
    this.showmark = false;
    this.isexamstarted = false;
    this.isexamnotstarted = true;
  }

 

  public getQestionList(){
    console.log(this.userid);
    console.log(this.scheduleid);

    if(this.userid != null && this.userid != "" && typeof this.userid != undefined &&
    this.scheduleid != null && this.scheduleid != "" && typeof this.scheduleid != undefined){
      console.log(this.userid);
    console.log(this.scheduleid);
    
      this.server.get("onlineexamine/forms/loadquestions/" +  LocalStorage.getValue("participanttsid") +"/"+LocalStorage.getValue("userid") )
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
        
          console.log(this.responseValue);
          if( this.responseType == "S"){
            if( data.result.inventoryResponse.responseObj.questiondetailsList.length > 0){
            this.questioncount = (data.result.inventoryResponse.responseObj.questiondetailsList.length)
            this.questionlist = this.responseValue["questiondetailsList"];
            this.setquestion();
            this.setoption();
            this.starttme = this.responseValue["particpantdetailslist"][0]["starttime"];
            this.endtime = this.responseValue["particpantdetailslist"][0]["endtime"];

            this.participantname=  this.responseValue["particpanttestdetailList"][0]["partcipantname"];
            this.participanttestdate=  this.responseValue["particpanttestdetailList"][0]["testdate"];
            this.batch_1 = this.responseValue["particpanttestdetailList"][0]["batch"];
            this.testduration = this.responseValue["particpanttestdetailList"][0]["duration"]; 
            this.coursebatch = this.responseValue["particpanttestdetailList"][0]["coursebatch"];

            const temp_arr = this.testduration.split(":");
            this.test_hour = temp_arr[0];
            this.test_minute = temp_arr[1];
            console.log(this.test_minute);
            this.countOn(this.test_hour, this.test_minute);

             //testtime start
             var startTime = moment(this.starttme, "HH:mm a");
             var endTime = moment(this.endtime, "HH:mm:ss a");
             console.log(startTime)
             // calculate total duration
             this.duration = moment.duration(endTime.diff(startTime));
  
             // duration in hours
             var hours = parseInt(this.duration.asHours());
  
             // duration in minutes
             var minutes = parseInt(this.duration.asMinutes()) % 60;
               this.testtime = hours+":"+minutes;
               console.log(this.testtime)
             console.log(hours + ' hour and ' + minutes + ' minutes.');
  
             //testtime end

             this.isexamnotstarted = false;
             this.isexamstarted = true;
            }
            else{
              this.isexamnotstarted = true;
              this.isexamstarted = false;
              this.errormsg = "Invalid Access"; 
            }
          }
          else{
            this.isexamnotstarted = true;
            this.isexamstarted = false;
            this.errormsg = "Your Question Session is Empty"; 
          }
        }
      });
    }
    else{
      this.isexamnotstarted = true;
      this.isexamstarted = false;
      this.errormsg = "Your Question Session is Empty"; 
    }
  }

  public startexam(){

  }

  public setquestion(){
    this.questiondetails = this.questionlist[this.index];
    this.questiondetails["question"] = this.questionlist[this.index]["question"];

    console.log(this.questiondetails)
    if (this.index + 1 == this.questioncount) {
      this.showSubmit = true;
    } else {
      this.showSubmit = false;
    }
  }

  public setoption() {

    if(this.myansList.length > 0){

      this.myansList.forEach(element => {
          if(element[0].questionid == this.questiondetails["questionid"]){
            this.myans = this.myansList[this.index];
          }else{
            if(element[0].questionid != this.questiondetails["questionid"]){
              if(!this.availableQnIds.find(e=>e === this.questiondetails["questionid"])){
              this.myans = [
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option1"], checked: this.checked },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option2"], checked: this.checked  },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option3"], checked: this.checked  },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option4"], checked: this.checked  }
              ];
              this.radioans = [
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option1"], checked: this.checked },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option2"], checked: this.checked  },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option3"], checked: this.checked  },
                { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option4"], checked: this.checked  }
              ];
            }
            }
          }
      });
    }else{
      this.myans = [
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option1"], checked: this.checked },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option2"], checked: this.checked  },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option3"], checked: this.checked  },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option4"], checked: this.checked  }
      ];
      this.radioans = [
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option1"], checked: this.checked },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option2"], checked: this.checked  },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option3"], checked: this.checked  },
        { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option4"], checked: this.checked  }
      ];
    }
    
    this.availableQnIds.push(this.questiondetails["questionid"]);
  }

  public setradio(i){
    console.log(i)
    this.myans.forEach(element => {
      element.checked = false;
    });
    this.myans[i].checked = true;
    console.log(this.myans)
  }
  public setanswer(){
    console.log(this.myans);
    // this.myans.filter((e) => e.checked).forEach(inner_element => {
    //   console.log(inner_element.option); 
    //   this.saras = inner_element.option;
    // });
    // this.myans = [
    //   { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option1"], checked: this.checked },
    //   { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option2"], checked: this.checked  },
    //   { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option3"], checked: this.checked  },
    //   { questionid: this.questiondetails["questionid"],index:this.index,questioname: this.questiondetails["question"], option: this.questiondetails["option4"], checked: this.checked  }
    // ];
    // console.log(this.radioans);
    // this.myans.filter((e) => e.checked).forEach(inner_element => {
    //   console.log(inner_element.option); 
    //   this.saras = inner_element.option;
    // });
    // console.log(this.saras)

   if(this.myansList.length > 0){
          if(!this.availableAnsIds.find(e=>e === this.myans[0].questionid)){
              this.myansList.push(this.myans);
          }
      }else{
        this.myansList.push(this.myans);
      }
      this.availableAnsIds.push(this.myans[0].questionid)

     
  }

  public nextquestion() {
    // this.setanswer();
    // this.index = this.index + 1;
    // this.setquestion();
    // this.setoption();

    this.setanswer();
    let element = this.myans;
    this.selectedAnsw = "";
    this.myans.forEach(element => {
      console.log(element.checked)
      if(element.checked === true){
        this.selectedAnsw += element.option + ",";
        this.singleFinalAns = this.selectedAnsw.substring(0, this.selectedAnsw.length - 1);
      }
    });
    if(this.questionlist[this.index+1] != null && typeof this.questionlist[this.index+1] != undefined){
      this.nextquestionid = this.questionlist[this.index+1]["questionid"];
    }else{
      this.nextquestionid = 0;
    }
    const dateZone = this.getTodayDate();
    const timeZoneOffset = this.localtime.getHours() + ':' + this.localtime.getMinutes();
    this.single_ans = {"tsid":this.scheduleid, "questionid" : this.myans[0]["questionid"],
    "userid":LocalStorage.getValue("userid"),"questionanswer":this.singleFinalAns,
    "date" : dateZone, "time":timeZoneOffset, 
    "question_status":"-1", "nextquestionid" : this.nextquestionid};
    console.log(this.single_ans);

    // let headers: Headers = new Headers();
    // let requestoptions: RequestOptions;
    // let theBody: any;
    // theBody = this.single_ans;
    // headers.append("Content-Type", 'application/json');
    // headers.append("tokenkey", LocalStorage.getValue("token"));
        
    // requestoptions = new RequestOptions({
    //         method: RequestMethod.Post,
    //         url: CONFIG._url + "onlineexamine/forms/savetestquestiontime",
    //         headers: headers,
    //         body: theBody
    // })
    //   this.http.request(new Request(requestoptions)).toPromise()
    //   .then((res) => {console.log(res)})
    //   this.index = this.index + 1;
    //   this.setquestion();
    //   this.setoption();
     this.server.post("onlineexamine/forms/savetestquestiontime",this.single_ans, false)
      .then((data) => {
         if (data.status == 200) {
           this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
           console.log(data)
           this.index = this.index + 1;
           this.setquestion();
           this.setoption();
         }
       });
  }

  public previousquestion() {
    //this.setanswer();
    //this.index = this.index - 1;
    //this.setquestion();
    //this.setoption();

    this.setanswer();
    let element = this.myans;
    this.selectedAnsw = "";
    this.myans.forEach(element => {
      console.log(element.checked)
      if(element.checked === true){
        this.selectedAnsw += element.option + ",";
        this.singleFinalAns = this.selectedAnsw.substring(0, this.selectedAnsw.length - 1);
      }
    });
    if(this.questionlist[this.index-1] != null && typeof this.questionlist[this.index-1] != undefined){
      this.nextquestionid = this.questionlist[this.index-1]["questionid"];
    }else{
      this.nextquestionid = 0;
    }
    const dateZone = this.getTodayDate();
    const timeZoneOffset = this.localtime.getHours() + ':' + this.localtime.getMinutes();
    this.single_ans = {"tsid":this.scheduleid, "questionid" : this.myans[0]["questionid"],
    "userid":LocalStorage.getValue("userid"),"questionanswer":this.singleFinalAns,
    "date" : dateZone, "time":timeZoneOffset, 
    "question_status":"-1", "nextquestionid" : this.nextquestionid};
    console.log(this.single_ans);
    
    try {
    this.server.post("onlineexamine/forms/savetestquestiontime",this.single_ans, false)
    .then((data) => {
      console.log(data)
       if (data.status == 200) {
         this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
         console.log(data)
        this.index = this.index - 1;
        this.setquestion();
        this.setoption();
       }else{
         alert("saras")
       }
     });
    } catch(error) {
      // Note that 'error' could be almost anything: http error, parsing error, type error in getPosts(), handling error in above code
     console.log("Failed to load posts!");
  }
  }

  public reviewquestion(){
    this.reivewList = [];
    this.modaltitle = "Review your Answers";
     console.log(this.myansList)
    this.myansList.forEach(element => {

      //element.filter((e) => e.checked);
      this.selectedAnsw = "";

       this.reivewObject.questionid = element[0].questionid;
       this.reivewObject.index = element[0].index;
       this.reivewObject.questioname = element[0].questioname;

       if(element.find(e=>e.checked === true)){
        element.filter((e) => e.checked).forEach(inner_element => {
          this.selectedAnsw += inner_element.option + ",";
          this.finalAnsw = this.selectedAnsw.substring(0, this.selectedAnsw.length - 1);
        });
      }else{
        this.finalAnsw = ""
      }
      this.reivewObject.answer = this.finalAnsw;
      this.reivewList.push(this.reivewObject);

      this.reivewObject = {
        questioname :"",
        index:0,
        questionid : 0,
        answer : ""
      };
    });

    //console.log(this.reviewobject);
    const dialogManualLogin = this.dialog.open(this.reviewDialog,{panelClass: 'full-width-dialog'});
  }

  public confirmSubmitquestion(){
    this.modalmsg = "Are you Sure want to Submit ??";
    const dialogManualLogin = this.dialog.open(this.confirmDialog);
  }

  public submitquestion(){
    this.ansList = [];
    this.myansList.forEach(element => {
      
       this.selectedAnsw = "";

       this.ansObject.questionid = element[0].questionid;
       this.ansObject.questioname = element[0].questioname;
       this.ansObject.scheduleid =  LocalStorage.getValue("participanttsid");
       this.ansObject.partid = LocalStorage.getValue("userid");
       if(element.find(e=>e.checked === true)){
        element.filter((e) => e.checked).forEach(inner_element => {
          this.selectedAnsw += inner_element.option + ",";
          this.finalAnsw = this.selectedAnsw.substring(0, this.selectedAnsw.length - 1);
        });
      }else{
        this.finalAnsw = ""
      }
      this.ansObject.questionanswer = this.finalAnsw;
      this.ansList.push(this.ansObject);

      this.ansObject = {
        questioname :"",
        questionid : 0,
        scheduleid : 1,
        partid:0,
        questionanswer : ""
      };
    });

    this.ans_data = { "questionlist": JSON.stringify(this.ansList)};
    console.log(this.ans_data);

    this.server.post("onlineexamine/forms/savetestschedulequestion",this.ans_data, false)
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
          this.closemodal();
          this.modalmsg = this.responseValue[0]["msg"];
          this.modaltitle ="Success";
          this.showmark = true;
          this.total_mark = this.responseValue[0]["fullmark"];
          this.part_mark = this.responseValue[0]["partmark"];
          this.ispass = this.responseValue[0]["ispass"];
          this.mark = this.responseValue[0]["mark"];
          const dialogManualLogin = this.dialog.open(this.viewDialog);
        }
      });

  }

  
  public closemymodal(){
    this.dialog.closeAll();
    this.router.navigateByUrl('/oeep/dashboard'); 
  }

  
  public closemodal(){ this.dialog.closeAll(); }

  public reviewEdit(index){
    this.dialog.closeAll();
    this.index = index;
    this.setquestion();
    this.setoption();
  }
  public getTodayDate(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    return dd + '/' + mm + '/' + yyyy;
  }






























































  // Aravind Timer and Reload Functionlity

  reloadConfirm() {
    window.addEventListener("beforeunload", function (e) {
      var confirmationMessage = "Are You Sure?";
    //  console.log("cond");
      e.returnValue = confirmationMessage;     // Gecko, Trident, Chrome 34+
      return confirmationMessage;              // Gecko, WebKit, Chrome <34
    });
  }
  countOn(hour: number, mins: number) {
    this.exm_hour = hour;
    this.exm_mins = mins;
    this.alerttimesec = 600;
    this.tot_hrs = ((this.exm_hour) * 3600) + (this.exm_mins * 60);
    this.alert1 = this.tot_hrs - this.alerttimesec;
    //console.log(this.tot_hrs)
    this.exm_tke_mins = 0;
    this.exm_tke_hrs = 0;
   // console.log(this.exm_hour, this.exm_mins)
    this.startCountDown(0);
    this.alertCountDown(0);
  }
  alertCountDown(coun1: number) {
    this.counter1 = coun1;
    this.interval1 = setInterval(() => {
      this.counter1++;
      if (this.counter1 >= this.alert1) {
        document.getElementById("timer").style.backgroundColor = "#FF0000";
      }
    }, 1000);
  }
  startCountDown(coun: number) {
    this.counter = coun;
    console.log(this.counter)
    var interval = setInterval(() => {
      this.counter++;
      if (this.counter > 59) {
        this.exm_tke_mins = this.exm_tke_mins + 1;
        console.log(this.exm_tke_mins )
        clearInterval(interval);
        this.startCountDown(coun);
        if (this.exm_tke_mins > 59) {
          this.exm_tke_hrs = this.exm_tke_hrs + 1;
          console.log(this.exm_tke_hrs)
          this.exm_tke_mins = 0;
          clearInterval(interval);
        }
      }
    
     
      
    }, 1000);
    console.log(this.testtime)
    console.log(this.timertime)
    this.timertime =this.exm_tke_hrs+":"+this.exm_tke_mins; 
    // if(this.testtime == this.timertime ){
    //   console.log("time over");

    // }
    if ((this.exm_hour == this.exm_tke_hrs) && (this.exm_mins == this.exm_tke_mins)) {
      console.log("exam Finished");
      clearInterval(interval);
      this.submitquestion();
      
    }
  }


}
