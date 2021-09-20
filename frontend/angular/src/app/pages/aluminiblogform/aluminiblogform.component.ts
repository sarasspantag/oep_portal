import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { Router } from '@angular/router';
import { ToolbarService, LinkService, ImageService, HtmlEditorService } from '@syncfusion/ej2-angular-richtexteditor';
import {NgForm} from '@angular/forms';
import { CONFIG } from '../../config';

export interface Transaction {
  courseid: string;
  coursename: string;
  coursedesc :String; 
  action :String; 
}

@Component({
  selector: 'app-aluminiblogform',
  templateUrl: './aluminiblogform.component.html',
  styleUrls: ['./aluminiblogform.component.scss'],
  providers: [ToolbarService, LinkService, ImageService, HtmlEditorService]
})

export class AluminiblogformComponent implements OnInit {
  public tools: object = {
    items: ['Undo', 'Redo', '|',
        'Bold', 'Italic', 'Underline', 'StrikeThrough', '|',
        'FontName', 'FontSize', 'FontColor', 'BackgroundColor', '|',
        'SubScript', 'SuperScript', '|',
        'LowerCase', 'UpperCase', '|',
        'Formats', 'Alignments', '|', 'OrderedList', 'UnorderedList', '|',
        'Indent', 'Outdent', '|', 'CreateLink',
        'Image', '|', 'ClearFormat', 'Print', 'SourceCode', '|', 'FullScreen']
};
public quickTools: object = {
  image: [
      'Replace', 'Align', 'Caption', 'Remove', 'InsertLink', '-', 'Display', 'AltText', 'Dimension']
};
public insertImageSettings = {
  display: 'inline',
  saveFormat:'Base64'
};
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();

  public errorMsg:string = "";

  public myTableList :any;
  showsavebutton:boolean;
  displayedColumns: string[] = ['profile', 'fromdate','todate','status','action'];
  public transactions: Transaction[];
  filename: any;
  public Editor=ClassicEditor;
  headline: any;
  content: any;
  blogid: any;
  date: any;
  public contentdata :string;
  public image_URL: string = CONFIG._imageurl;
  mandatoryvalid: boolean;
  constructor(private server: HyperService, private masterservice:MasterService, private router: Router, 
    private dialog: MatDialog)  {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }

   }


   color = 'primary';
   checked = false;
   status1: any;
   coursename: any;
   duration: any;
   applicableic: any;
   courseid: any;
   id: any;
   alumni_data: { "headline": any;"content": any; "status": any; 
   "userid": any, "oprn": any,"filename": any,"blogid": any,"filename1": any,"formdata":any};
   coursedesc: any;
   msgcolor: string;
   msg: {};
   roleid: string;
   status: any; 
   userid: string;
   oprn: string;
   token: string;
   oprnstatus: boolean ;
   formvalue:string; 
   value:any;
   public List: any; 
   public myList: any;
   public responseType : string;
   public responseValue : {};
   public modaltitle : string;
   public modalmsg : string;
   public editorData = '<p>Your Text here,</p>';
   public image;
   isLoading = false;

   onSubmit(form: NgForm): void {
    console.log(form.value.name);
   
   this.formvalue=form.value.name;
   this.showsavebutton = true;
  }
  ngOnInit() {
    LocalStorage.setValue("filename",null);
    this.showsavebutton = true;
    LocalStorage.setValue("id",1);
    LocalStorage.setValue("type",3);
    LocalStorage.setValue("form","product");
    LocalStorage.setValue("foldername","blogimage");
    LocalStorage.setValue("sessionname","alumniblogimage");

    LocalStorage.setValue("newid",1);
    LocalStorage.setValue("newtype",7);
    LocalStorage.setValue("newform","product");
    LocalStorage.setValue("newfoldername","blogdescription");
    LocalStorage.setValue("newsessionname","alumniblogdesc");

    this.oprn = "INS";
    this.getblogList();
    this.blogid = "0";
    this.value = "";
  }

  changed(e){
  
    if(e.checked==true){  
      this.status1="1";
    }else{
      this.status1="0"; 
    }
 
  }

 

  public getblogList(){

    this.server.get("onlineexamine/forms/getalumniblogList")
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);
      this.List = this.responseValue;
      this.myList = JSON.stringify(this.List);  
      this.transactions = JSON.parse(this.myList);
      this.dataSource.data = this.transactions;
      this.dataSource.paginator = this.paginator;
       //   console.log(this.dataSource);
    });
  }   
  

  getblogdtls(blogid){
    this.server.get("onlineexamine/forms/getalumniblogdetails/"+blogid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];   
      console.log( this.List)   
      this.headline =  this.List["headline"];
      this.value =  this.List["content"];
      this.contentdata =  this.List["content"];
      this.status =  this.List["status"]; 
      this.date =  this.List["date"]; 
      this.blogid =  this.List["blogid"]; 
      this.oprn =  "UPD" ;    
      
      if(this.status=="Active"){ 
        this.checked=true; 
        this.status1="1";
      }else{
        this.checked=false; 
        this.status1="0";
      }
      document.getElementById("login_ctr").scrollIntoView();
    });
  }

//   public onChange( { editor }: ChangeEvent ) {
//     const data = editor.getData();
//     console.log( data );
// }


  delete(blogid){
   
    this.server.get("onlineexamine/forms/getalumniblogdetails/"+blogid)
    .then((data) => {

      this.responseType = this.masterservice.getResponseType(data);
      this.responseValue = this.masterservice.getResponseValue(data);    
      this.List = this.responseValue[0];   
      this.headline =  this.List["headline"];
      this.content =  this.List["content"];
      this.blogid =  this.List["blogid"]; 
      this.status =  this.List["status"]; 
      this.oprn = "DEL";
      this.modalmsg ="Do you want to delete ?";
      const dialogManualLogin = this.dialog.open(this.deleteDialog);
    });
  }
 
  clearfields(){
    this.headline =  "";
    this.content = "";
    this.checked =  false;
    this.oprn = "INS";
   
  }

 showview(blogid){
  this.server.get("onlineexamine/forms/getalumniblogdetails/"+blogid)
  .then((data) => {

    this.responseType = this.masterservice.getResponseType(data);
    this.responseValue = this.masterservice.getResponseValue(data);    
    this.List = this.responseValue[0];      
    this.headline =  this.List["headline"];
    this.content =  this.List["content"];
    this.status =  this.List["status"]; 
    this.date =  this.List["date"]; 
    this.image =  this.List["image"]; 

    if(this.status=="Active"){ 
      this.checked=true; 
      this.status1="1"; 
    }else{
      this.checked=false; 
      this.status1="0";
    }
    const dialogManualLogin = this.dialog.open(this.viewDialog);
 
  });
    }



     public save(form: NgForm){
      this.formvalue=form.value.name;
      this.errorMsg=""
      if(this.oprn == 'INS'){
        if (this.headline == null || this.headline == "" || this.headline == undefined ||
      this.formvalue== null || this.formvalue == "" || this.formvalue == undefined ||
      LocalStorage.getValue("filename") == null || LocalStorage.getValue("filename") == "" || LocalStorage.getValue("filename") == undefined) {
     
      this.mandatoryvalid = false;
      }else{
        this.mandatoryvalid = true;
      }
      }else if(this.oprn == 'UPD'){
        if (this.headline == null || this.headline == "" || this.headline == undefined ||
        this.formvalue== null || this.formvalue == "" || this.formvalue == undefined 
       ) {
     
        this.mandatoryvalid = false;
        }else{
          this.mandatoryvalid = true;
        }
      }else if(this.oprn == 'DEL'){
        this.mandatoryvalid = true;
      }
      if ( this.mandatoryvalid == false) {
      this.errorMsg="Please Fill All Required Field";
       
      }else{
       this.userid = LocalStorage.getValue("userid");

       this.alumni_data = {"headline": this.headline,"content":this.formvalue,"userid":this.userid,
       "status":this.status1,"oprn":this.oprn,"blogid":this.blogid,"filename":LocalStorage.getValue("filename"),
       "filename1":LocalStorage.getValue("filename1"),"formdata":this.formvalue};
       console.log(this.alumni_data)
     
  this.isLoading = true;
      this.server.post( "onlineexamine/forms/savealumniblogdetails", this.alumni_data, false)
      .then((data) => { 
       if(data.status==200){ 
        this.isLoading = false;
        this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
        console.log(data)
     
          if (this.responseType == 'F')
          {
            this.msgcolor = "red";
            this.modalmsg =this.responseValue["out_result_msg"];
          }else(this.responseType == 'S') 
          {
            this.formvalue="";
              this.modalmsg =this.responseValue["out_result_msg"];
              this.msgcolor ="green"
              this.cleardata();
              this.modaltitle="Success";
              if(this.oprn = "INS"){
                this.modalmsg =this.responseValue["out_result_msg"];
              }else if(this.oprn = "UPD"){
            
                this.modalmsg =this.responseValue["out_result_msg"];
              }else if(this.oprn = "DEL"){                
                this.modalmsg =this.responseValue["out_result_msg"];
              }else{

              }
              const dialogManualLogin = this.dialog.open(this.alertDialog);
              this.router.navigateByUrl('/alumini-blog');
          }
      }    
    });  
  }
 }

 confirmDel(){
  this.formvalue="";
  this.userid = LocalStorage.getValue("userid");

  this.alumni_data = {"headline": this.headline,"content":this.formvalue,"userid":this.userid,
  "status":this.status1,"oprn":this.oprn,"blogid":this.blogid,"filename":LocalStorage.getValue("filename"),
  "filename1":LocalStorage.getValue("filename1"),"formdata":this.formvalue};

 this.server.post( "onlineexamine/forms/savealumniblogdetails", this.alumni_data, false)
 .then((data) => { 
  if(data.status==200){ 
   this.isLoading = false;
   this.responseType = this.masterservice.getResponseType(data);
     this.responseValue = this.masterservice.getResponseValue(data);
   
     if (this.responseType == 'F')
     {
       this.msgcolor = "red";
       this.modalmsg =this.responseValue["out_result_msg"];
     }else(this.responseType == 'S') 
     {
       this.formvalue="";
         this.modalmsg =this.responseValue["out_result_msg"];
         this.msgcolor ="green"
         this.cleardata();
         this.modaltitle="Success";
         if(this.oprn = "INS"){
           this.modalmsg =this.responseValue["out_result_msg"];
         }else if(this.oprn = "UPD"){
       
           this.modalmsg =this.responseValue["out_result_msg"];
         }else if(this.oprn = "DEL"){                
           this.modalmsg =this.responseValue["out_result_msg"];
         }else{
         }
         const dialogManualLogin = this.dialog.open(this.alertDialog);
         this.router.navigateByUrl('/alumini-blog');
     }
  }  
  });  
 }

 cleardata(){ 
  this.courseid =  0;
  this.coursename = "";
  this.coursedesc =  "";
  this.duration =  "";
  this.applicableic ="";
  this.checked =  false;
  this.getblogList();

 }
 closemodal(){
  this.cleardata(); 
  this.dialog.closeAll();
 }

 upload(){
  this.router.navigateByUrl('upload');
 }




}
