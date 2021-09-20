import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { Router } from '@angular/router';
import { MasterService } from 'src/app/core/master.service';
import { CONFIG } from 'src/app/config';

import {
  MatDialog,
  MatDialogRef,MatTableDataSource, MatPaginator
} from "@angular/material";
import { interval } from 'rxjs';



@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {

  private defaultimage = "herofiles/documents/gallery/galleryimage/0.jpg"
  @ViewChild('viewDialog') viewDialog: TemplateRef<any>;

  

  public responseType: string;
  public responseValue: {};
  imagegalleryarray: any;
  img= [];
  showimage:any;
  configurl: any;
  color=[];
  public loading:boolean;
  public divshow:boolean;
  desc: any;

  constructor(private server: HyperService, private masterservice: MasterService, private router: Router,
    private dialog: MatDialog) {
    if (LocalStorage.isSetJWT()) {
      LocalStorage.loadJWT();
    } else {
      LocalStorage.createJWT();
    }
  }

  ngOnInit() {
    this.configurl = CONFIG._imageurl;
    this.loading =true;
    this.divshow = true;

    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {

          this.getallimage();
      logged.unsubscribe();
      } 
  });


  }

  getallimage(){
    this.server.get("onlineexamine/forms/getAllgalleryimage")
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
          console.log(this.responseValue)
          if (this.responseType == 'S') {
            this.imagegalleryarray = (this.responseValue)
            console.log( this.imagegalleryarray.length)
           
              for(let i=0;i < 27;i++){
                if( i < this.imagegalleryarray.length){
                 
                  this.img[i+1] =this.imagegalleryarray[i]["imagepath"];
                }else{
                  this.img[i+1] = "";
                //this.img[i+1] =this.defaultimage;
                }
            }   
          }
        }
        this.loading =false;
        this.divshow = false;
      });
      this.color[1] = "yellow";
  }

  getimagecategory(categoryid){
    this.color=[];
    this.color[categoryid] = "yellow";
    this.server.get("onlineexamine/forms/getgalleryimage/" +categoryid)
      .then((data) => {
        if (data.status == 200) {
          this.responseType = this.masterservice.getResponseType(data);
          this.responseValue = this.masterservice.getResponseValue(data);
          console.log(this.responseValue)
          if (this.responseType == 'S') {
            this.imagegalleryarray = (this.responseValue)
            console.log( this.imagegalleryarray.length)
           if( this.imagegalleryarray.length != 0){
            for(let i=0;i < 27;i++){
              if( i < this.imagegalleryarray.length){
              
                this.img[i+1] =this.imagegalleryarray[i]["imagepath"];
              }else{
                this.img[i+1] = "";
                //this.img[i+1] =this.defaultimage;
              }
            
          }
         
           }else{
            this.img=[]; 
            for(let i=0;i < 27;i++){
              this.img[i+1] = "";
               //this.img[i+1] =this.defaultimage;
          }
           }
             
          }

        }

      });

  }
  showview(courseid,desc) {
    this.showimage= this.configurl+courseid;
    this.desc= desc;
console.log(courseid)
    const dialogManualLogin = this.dialog.open(this.viewDialog);
  }
  closemodal() {
    this.dialog.closeAll();
  }
}
