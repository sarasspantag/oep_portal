import { Component, OnInit } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { CONFIG } from 'src/app/config';
import { interval } from 'rxjs';
@Component({
  selector: 'app-viewfaculty-profilelist',
  templateUrl: './viewfaculty-profilelist.component.html',
  styleUrls: ['./viewfaculty-profilelist.component.scss']
})
export class ViewfacultyProfilelistComponent implements OnInit {
  data = [];
  page = 0;
    size = 3;
  multipleproductList:any = [];
  public responseType: string;
  public responseValue: {};
  configimageurl: string;
  public loading:boolean;
  public divshow:boolean;

  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router) {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
     }

  ngOnInit() {
    this.loading =true;
    this.divshow = true;
    const logged = interval(1000).subscribe(
      res => {
        if (LocalStorage.getValue("token")) {

    this.server.get("onlineexamine/forms/getFacultyList")
    .then((data) => {
      if (data.status == 200) {
        this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);  
        console.log(this.responseValue)          
      this.multipleproductList = this.responseValue
      console.log(this.multipleproductList)
      this.getData({pageIndex: this.page, pageSize: this.size});
      this.configimageurl = CONFIG._imageurl
      }  
      this.loading =false;
      this.divshow = false; 
    });
      logged.unsubscribe();
        } 
    });
  
  }

  getData(obj) {
    let index=0,
        startingIndex=obj.pageIndex * obj.pageSize,
        endingIndex=startingIndex + obj.pageSize;
  
    this.data = this.multipleproductList.filter(() => {
      index++;
      return (index > startingIndex && index <= endingIndex) ? true : false;
    });
  }

  navigatefacultydetails(facultyid){
    LocalStorage.setValue("facultyprofileid",facultyid);
    this.router.navigateByUrl('/faculty-details');
  }
}
