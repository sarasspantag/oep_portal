import { Component, OnInit ,ViewChild, TemplateRef} from '@angular/core';
import {
  MatDialog,
  MatDialogRef 
} from "@angular/material";
import { LocalStorage } from 'src/app/core/local_storage.service';
import { HyperService } from 'src/app/core/http.service';
import { MasterService } from 'src/app/core/master.service';
import { Router } from '@angular/router';
import { Workbook } from 'exceljs';
import * as fs from 'file-saver';
@Component({
  selector: 'app-importparticipants',
  templateUrl: './importparticipants.component.html',
  styleUrls: ['./importparticipants.component.scss']
})
export class ImportparticipantsComponent implements OnInit {
  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
  public responseType: string;
  public responseValue: {};
  public registerobject:any;
  public modalmsg : string;
  constructor(private server: HyperService, private masterservice:MasterService,
    private router: Router,private dialog: MatDialog) {
      if (LocalStorage.isSetJWT()) {
        LocalStorage.loadJWT();
      } else {
        LocalStorage.createJWT();
      }
     }

  ngOnInit() {
    LocalStorage.setValue("id",1);
    LocalStorage.setValue("type",1);
    LocalStorage.setValue("form","schedule");
    LocalStorage.setValue("foldername","schedule");
    LocalStorage.setValue("sessionname","scheduleimport");
  }
  sampleformatregisterparticipants(){
    //Excel Title, Header, Data
   
    const header = ["S.No", "Partcipant Name", "Ps Number", "Email", "Applicable Ic", "Job Code"]
    const data = [
      
    ];
    //Create workbook and worksheet
    let workbook = new Workbook();
    let worksheet = workbook.addWorksheet('sample');
    //Add Row and formatting
   
    
  
    //Add Header Row
    let headerRow = worksheet.addRow(header);
    
    // Cell Style : Fill and Border
    headerRow.eachCell((cell, number) => {
      cell.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: 'ffbf00' },
        bgColor: { argb: 'FF0000FF' }
      }
      cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
    })
    // worksheet.addRows(data);
    // Add Data and Conditional Formatting
    data.forEach(d => {
      let row = worksheet.addRow(d);
      let qty = row.getCell(5);
      let color = 'FF99FF99';
      if (+qty.value < 500) {
        color = 'FF9999'
      }
      qty.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: color }
      }
    }
    );
    worksheet.getColumn(2).width = 30;
    worksheet.getColumn(3).width = 30;
    worksheet.getColumn(4).width = 30;
    worksheet.getColumn(5).width = 30;
    worksheet.addRow([]);
    //Footer Row
   
    //Generate Excel File with given name
    workbook.xlsx.writeBuffer().then((data) => {
      let blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      fs.saveAs(blob, 'sampleexcel.xlsx');
    })
  

 // window.open('onlineexamine/forms/downloadsampleformatparticipantregister','_blank');
 
}

importexcelschedule(){
  this.registerobject={"filename":LocalStorage.getValue("filename"),"scheduleid":LocalStorage.getValue("scheduleid"),
  "roleid":LocalStorage.getValue("roleid"),"userid":LocalStorage.getValue("userid")};
  this.server.post( "onlineexamine/forms/saveparticpantsimport", this.registerobject, false)
  .then((data) => { 
    if(data.status==200){
      this.responseType = this.masterservice.getResponseType(data);
        this.responseValue = this.masterservice.getResponseValue(data);
        console.log( this.responseType)
        console.log( this.responseValue )
       
        
            
          const dialogManualLogin = this.dialog.open(this.alertDialog);
        
         
    }  
     
  }); 
}
closemodal(){
  this.dialog.closeAll();
}
}
