import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material';

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String;
  action :String; 
}

@Component({
  selector: 'app-testlist',
  templateUrl: './testlist.component.html',
  styleUrls: ['./testlist.component.scss']
})


export class TestlistComponent implements OnInit {

  
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
    @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
    @ViewChild('viewDialog') viewDialog: TemplateRef<any>;

    
  public myTableList :any;
  displayedColumns: string[] = ['index','testname','testdate','starttime','endtime','action'];
  public responseType: any;
  today = new Date();
  time = this.today.getHours() + ":" + this.today.getMinutes();
  public responseValue: any;
  public List: any; 
   public myList: any;
   public transactions: Transaction[];
  checktime: any;
  modalmsg: string;
  starttime: any;
  duration: string;

  constructor(private server: HyperService, private masterservice:MasterService, private router: Router,
    private datePipe: DatePipe,private dialog: MatDialog ) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
   } 

  ngOnInit() {
   // console.log(LocalStorage.getValue("userid"));
    this.getTestList();   
    this.datePipe.transform(this.today, 'HH:mm');
    console.log(this.time) ;
  }
  getTestList() {   
    this.server.get("onlineexamine/forms/getParticipantTestList/"+LocalStorage.getValue("userid"))
    .then((data) => {
      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      this.starttime = this.responseValue[0]["starttime"];
      this.checktime = this.responseValue[0]["starthours"]+':'+this.responseValue[0]["startminutetime"];
      this.duration= this.responseValue[0]["endhours"]+this.responseValue[0]["starthours"];
      this.myList = JSON.stringify(this.List);  
      this.transactions = JSON.parse(this.myList);
     console.log(this.checktime)  
     console.log(this.duration)       
    });
  }

  next(quesid){
    if(this.checktime>this.time){
      this.modalmsg = "You can start the exam at";
      const dialogManualLogin = this.dialog.open(this.alertDialog);
     // this.ngOnInit();
      //window.location.reload();
      return false;
    }
    LocalStorage.setValue("questionid",quesid);   
    this.router.navigateByUrl('/oeep/testsubmission');          
  }

  closemodal(){
    //this.cleardata();  
    this.dialog.closeAll();
}

}
