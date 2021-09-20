import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { FileUploader } from "ng2-file-upload";
import { LocalStorage } from 'src/app/core/local_storage.service';
import { MatDialog,MatTableDataSource, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';

export interface Transaction {
  courseid: string;
  coursename: string; 
  coursedesc :String; 
  action :String; 
}

@Component({
  selector: 'app-galleryform',
  templateUrl: './galleryform.component.html',
  styleUrls: ['./galleryform.component.scss']
})
export class GalleryformComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  @ViewChild('deleteDialog') deleteDialog: TemplateRef<any>;
  @ViewChild('confirmDialog') confirmDialog: TemplateRef<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Transaction>();
  public myTableList :any;
  public transactions: Transaction[]; 

  showsavebutton:boolean;
  displayedColumns: string[] = ['profile', 'fromdate','todate','status','action'];
  data = [];
  table_data = [];
  page = 0;
  size = 8;
  table_page = 0;
  table_size = 10;
  uploadForm: FormGroup;
  public responseType: string;
  public responseValue: {};
  galleryobject:any;
  gallerydata:any;
  galleryarray: any = [];
  filename: string;
  files: any;
  oprn: any;
  categoryid:any;
  categorylist :any;
  public modaltitle : string;
  public modalmsg : string;
  urls = new Array<string>();
  isLoading = false;
  
  public uploader: FileUploader = new FileUploader({

  });
 //public descarray:[]=[];
 public descarray=[];
 imagegalleryarray: any = [];
  img: any;
  showimage: any;
  desc: any;
  configurl: any;
  imageurls = new Array<string>();
  constructor(private server: HyperService,private router: Router, private masterservice: MasterService,
     private dialog: MatDialog,private fb: FormBuilder, private http: HttpClient) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  }

  ngOnInit() {
    this.oprn = "INS";
    this.configurl = CONFIG._imageurl;
    this.getallimage();
    this.showsavebutton = false;
    this.dataSource.data = JSON.parse('[{"index":"1","caetegoryname":"All","imagecount":"30","status":"Active"}]');
    this.uploadForm = this.fb.group({
      document: [null, null],
      type: [null, Validators.compose([Validators.required])]

    });


   
    
    this.server.get("onlineexamine/forms/getimagecategorydetails")
    .then((data) => {
      if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
       console.log(this.responseValue )
       this.categorylist = this.responseValue;
      }

    });
    this.getData({ pageIndex: this.page, pageSize: this.size });
    
  }
  uploadSubmit() {
    this.isLoading = true;
    console.log("upload")
    for (let i = 0; i < this.uploader.queue.length; i++) {
      let fileItem = this.uploader.queue[i]._file;
      if (fileItem.size > 10000000) {
        alert("Each File should be less than 10 MB of size.");
        return;
      }
    }
    for (let j = 0; j < this.uploader.queue.length; j++) {
      let data = new FormData();
      let fileItem = this.uploader.queue[j]._file;
      console.log(fileItem.name);
      data.append('file', fileItem);
      console.log(this.descarray[j])
      this.server.post_fileUpload("onlineexamine/forms/multipleSave/" + '1' + '/' + 'gallery' + '/' + '1' + '/' + 'galleryimage' + '/' + 'galleryimage', data, false)
        .then((data) => {
          if (data.status == 200) {
            this.isLoading = false;
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            if (this.responseType == 'F') {
           
              this.modalmsg = this.responseValue["out_result_msg"];
               const dialogManualLogin = this.dialog.open(this.alertDialog);
            
             }else(this.responseType == 'S')
             {
              console.log(this.responseValue)
              console.log(this.responseValue["filename"])
              this.galleryobject = {"filename":this.responseValue["filename"],"imagedesc":this.descarray[j]};
              this.galleryarray.push(this.galleryobject)
              // console.log(JSON.stringify(this.galleryarray))
              console.log(this.galleryarray)    
              this.modalmsg = "Are you sure want to save ??";
              const dialogManualLogin = this.dialog.open(this.confirmDialog);
           } 
          }
        });

    }
    this.uploader.clearQueue();
    this.showsavebutton = true;
  }

  detectFiles(event) {
    this.urls = [];
    
    for (let i = 0; i < this.uploader.queue.length; i++) {
      let fileitem = this.uploader.queue[i]._file;

      this.files = fileitem;
      if (this.files) {
        let reader = new FileReader();
        reader.onload = (e: any) => {
     
          this.urls.push(e.target.result);
          this.getData({pageIndex: this.page, pageSize: this.size});
        }
        reader.readAsDataURL(this.files);

      }
    }
 
  }

  deleteimage(i){
    this.urls.splice(i, 1);
    this.uploader.removeFromQueue(i);    
    this.descarray.splice(i, 1);
    this.getData({pageIndex: this.page, pageSize: this.size});
  }

  delete(imageid){
  this.oprn ="DEL";
  this.categoryid = imageid;
  console.log(this.categoryid)
  this.modalmsg ="Do you want to delete ?";      
  const dialogManualLogin = this.dialog.open(this.deleteDialog);
  }

  savegalleryimage(){
  this.gallerydata={"categoryid":this.categoryid,"oprn":this.oprn,"galleryarray":JSON.stringify(this.galleryarray)}
  console.log(this.gallerydata)
  //return false;
    this.server.post("onlineexamine/forms/savecategoryimage",this.gallerydata,false)
    .then((data) => {
      if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
      console.log(this.responseType )
      if(this.responseType == 'S'){
        this.modaltitle = "Success";
        if(this.oprn == "DEL"){
          this.modalmsg ="Image deleted successfuly";
        }else if(this.oprn = "INS"){
          this.modalmsg ="Gallery image uploaded successfuly"; 
        } 
        const dialogManualLogin = this.dialog.open(this.alertDialog);
        }else{
        {
          this.modalmsg ="Invalid";
        }
        this.cleardata();  
        const dialogManualLogin = this.dialog.open(this.alertDialog);
      }
      this.cleardata();
      }
      this.cleardata();
    });
  }
  
  getData(obj) {
    let index = 0,
      startingIndex = obj.pageIndex * obj.pageSize,
      endingIndex = startingIndex + obj.pageSize;
      this.data = this.urls.filter(() => {
      index++;
      return (index > startingIndex && index <= endingIndex) ? true : false;
    });
  }

  getTableData(obj) {
    let index = 0,
      startingIndex = obj.pageIndex * obj.pageSize,
      endingIndex = startingIndex + obj.pageSize;
      this.table_data = this.imagegalleryarray.filter(() => {
      index++;
      return (index > startingIndex && index <= endingIndex) ? true : false;
    });
  }

  closemodal(){
    this.dialog.closeAll();
    this.getallimage();
    this.oprn = "INS";
     }
     closewithclearmodal(){
      this.dialog.closeAll();
      this.getallimage();
      this.oprn = "INS";
      this.cleardata();
     }
     cleardata(){ 
      this.gallerydata = "";
      this.galleryarray = [];
      this.urls = [];
      this.getData({pageIndex: this.page, pageSize: this.size});
      this.galleryobject = "";
      this.oprn = "INS";
      if(this.modalmsg == "Image Saved Successfully"){
        //this.router.navigateByUrl('/gallery');
        window.location.reload();
      }
     }


     getallimage(){
      this.server.get("onlineexamine/forms/getAllgalleryimage")
        .then((data) => {
          if (data.status == 200) {
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
            console.log(this.responseValue )
            
            if (this.responseType == 'S') {
              this.imagegalleryarray = (this.responseValue)   
              this.getTableData({ pageIndex: this.table_page, pageSize: this.table_size });            
            } 
          }     
        });     
    }
  

    getimagecategory(categoryid){
      this.imagegalleryarray = [];
      this.getTableData({ pageIndex: this.table_page, pageSize: this.table_size }); 
      this.server.get("onlineexamine/forms/getgalleryimage/" +categoryid)
        .then((data) => {
          if (data.status == 200) {
            this.responseType = this.masterservice.getResponseType(data);
            this.responseValue = this.masterservice.getResponseValue(data);
           
            if (this.responseType == 'S') {
              this.imagegalleryarray = this.responseValue;
              this.getTableData({ pageIndex: this.table_page, pageSize: this.table_size }); 
             }
          }
        });
    }

    showview(courseid,desc) {
      this.showimage= this.configurl+courseid;
      this.desc= desc;    
   //   const dialogManualLogin = this.dialog.open(this.viewDialog);
    }
   
}
