import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { MatDialog } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';

@Component({
  selector: 'app-uploadfile',
  templateUrl: './uploadfile.component.html',
  styleUrls: ['./uploadfile.component.scss']
})
export class UploadfileComponent implements OnInit {
  myFileUploadService: any;
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  filename: string;
  arr: string;
  show: boolean;
  fileextension: string;
  imgshow: boolean;

  constructor(private server: HyperService, private router: Router,private masterservice: MasterService,private dialog: MatDialog)  {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT(); 
    } 
 }

 
 public List: any; 
 public myList: any;
 public CourseList: any;
 totalparticipants: any;
 courseid: any; 
 schedule_data: any;
 userid: any;
 startdate: any; 
 id: any;
 type: any; 
 form: any; 
  sessionname: any;
  foldername: any; 
  file: any;
 applicableic: any;
 status1: any;
 facultyid: any; 
 enddate: any;
 course_data: { "id": any;"type": any; "form": any;  "sessionname": any;"foldername": any; };
 public responseType : string;
 public responseValue : {}; 
 public modaltitle : string;
 public modalmsg : string;
 public fileToUpload:File = null; 
 public filedata:any; 
 url:any;
 imgSrc : any;

  ngOnInit() {
    LocalStorage.setValue("zipfilename","")
    this.show = false;
    this.id = LocalStorage.getValue("id");
    this.type = LocalStorage.getValue("type");
    this.form = LocalStorage.getValue("form");
    this.sessionname = LocalStorage.getValue("sessionname");
    this.foldername = LocalStorage.getValue("foldername");
      
  }



  public upload(){ 
    this.filedata = new FormData();
    this.filedata.append("file",this.fileToUpload);

    this.course_data = { "id": this.id, "type":this.type, "form": this.form,"sessionname": this.sessionname,"foldername":this.foldername};
    console.log(this.course_data)
   // return false;
    //  this.server.post_fileUpload( "onlineexamine/forms/savefile", this.course_data, false)
     this.server.post_fileUpload( "onlineexamine/forms/savefile/"+this.id+'/'+this.form+'/'+this.type+'/'+this.sessionname+'/'+this.foldername,this.filedata,false)
    .then((data) => { 
      if(data.status==200){
        this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
        console.log(data)
          if (this.responseType == 'F') {
           
            this.modalmsg = this.responseValue["out_result_msg"];
             const dialogManualLogin = this.dialog.open(this.alertDialog);
          
           }else(this.responseType == 'S')
           {    
            this.modaltitle = "Success";
            this.modalmsg = this.responseValue["out_result_msg"];

            this.show = true;
            if(this.fileextension != "xlsx"){
              this.imgSrc = this.url ;
              this.imgshow = true;
            }

            const dialogManualLogin = this.dialog.open(this.alertDialog);
            LocalStorage.setValue("filename",this.responseValue["filename"]);
            console.log(this.responseValue["filename"])
         } 
      }   
       
    }); 
     }

  public handleFileInput(files: FileList) {
  
    var reader = new FileReader();
    this.fileToUpload = files[0];
    this.filename = files[0].name; 
    console.log(this.filename);
    let stringToSplit = this.filename;
    let x = stringToSplit.split(".");
    console.log(x[1]); 
    console.log(x[0]) 
    if(x[1] == 'zip'){
      LocalStorage.setValue("zipfilename",x[0]);
    }
  
    this.fileextension = x[1];
    this.show = false;
    this.imgshow = false;
    if(this.fileextension != "xlsx"){
      reader.readAsDataURL(this.fileToUpload);
      reader.onload = (event) => { // called once readAsDataURL is completed
        this.url = event.target.result;
      }
    }
    
}

closemodal(){
  this.dialog.closeAll();
 }


}
     
    
    
    