import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MaterialModule } from './material/material.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { AboutusComponent } from './pages/aboutus/aboutus.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ChartModule } from '@syncfusion/ej2-angular-charts';
import { Header1Component } from './components/header1/header1.component';
import { FooterComponent } from './components/footer/footer.component';
import { CmmComponent } from './pages/cmm/cmm.component'; 
// import { FileSelectDirective } from 'ng2-file-upload';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { HyperService } from './core/http.service';
import { SocketService } from './core/socket.service';
import { HttpClientModule } from "@angular/common/http";
import { HttpModule } from '@angular/http';
import { CourseScheduleComponent } from './pages/course-schedule/course-schedule.component';
import { CourseRegComponent } from './pages/course-reg/course-reg.component';
import { QbmComponent } from './pages/qbm/qbm.component';
import { TestscheduleComponent } from './pages/testschedule/testschedule.component';
import { OnlineexamineportalComponent } from './pages/onlineexamineportal/onlineexamineportal.component';
import { CertificateComponent } from './components/certificate/certificate.component';
import { UmmComponent } from './pages/umm/umm.component';
import { PopupModalComponent } from './pages/popup-modal/popup-modal.component';
import { DatePipe } from '@angular/common'
import {FacultyprofileComponent }  from './pages/facultyprofile/facultyprofile.component';
import { UploadfileComponent } from './pages/uploadfile/uploadfile.component';
import { TestadministratorComponent } from './pages/testadministrator/testadministrator.component';
import { TestsubmissionComponent } from './pages/testsubmission/testsubmission.component';
import {ScheduleModule, AgendaService, DayService, WeekService, WorkWeekService, MonthService } from '@syncfusion/ej2-angular-schedule';
import { ParticipantProfileComponent } from './pages/participant-profile/participant-profile.component';
import { ImportparticipantsComponent } from './pages/importparticipants/importparticipants.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { NewsandeventsComponent } from './pages/newsandevents/newsandevents.component';
import { AluminiComponent } from './pages/alumini/alumini.component';
import { TestlistComponent } from './pages/testlist/testlist.component';
import {NgxPrintModule} from 'ngx-print';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TemplateComponent } from './pages/template/template.component';
import { WebsitehomeComponent } from './pages/websitehome/websitehome.component';
import { ContactUsComponent } from './pages/contact-us/contact-us.component';
import { ViewfacultyProfilelistComponent } from './pages/viewfaculty-profilelist/viewfaculty-profilelist.component';
import { ViewfacultyProfiledetailsComponent } from './pages/viewfaculty-profiledetails/viewfaculty-profiledetails.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { AluminiblogformComponent } from './pages/aluminiblogform/aluminiblogform.component';
import { AluminiblogdetailsComponent } from './pages/aluminiblogdetails/aluminiblogdetails.component';
import { ReportsComponent } from './pages/reports/reports.component';
import { ChartsModule } from 'ng2-charts';
import { GalleryformComponent } from './pages/galleryform/galleryform.component';
import { NewsFormComponent } from './pages/news-form/news-form.component';
import { NewseventDetailsComponent } from './pages/newsevent-details/newsevent-details.component';
import { SafePipe } from './core/safepipe';
import { NewUploadComponent } from './pages/new-upload/new-upload.component';
import { RichTextEditorModule } from '@syncfusion/ej2-angular-richtexteditor';
import { NgxLoadingModule,ngxLoadingAnimationTypes  } from 'ngx-loading';
import { ErrorpageComponent } from './pages/errorpage/errorpage.component';
import { TrainingCoursesComponent } from './pages/training-courses/training-courses.component';
import {FormControl} from '@angular/forms';
import { FileUploadModule } from "ng2-file-upload";
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import { ReportsFacultyComponent } from './pages/reports-faculty/reports-faculty.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FacultyprofileComponent,
    AboutusComponent,
    DashboardComponent,
    Header1Component,
    FooterComponent,
    CmmComponent,
    //FileSelectDirective,
    LoginComponent,
    SignupComponent,
    CourseScheduleComponent,
    CourseRegComponent,
    QbmComponent,
    TestscheduleComponent,
    OnlineexamineportalComponent,
    CertificateComponent,
    UmmComponent,
    PopupModalComponent,
    UploadfileComponent,
    TestadministratorComponent,
    TestsubmissionComponent,
    ParticipantProfileComponent,
    ImportparticipantsComponent,
    NewsandeventsComponent,
    AluminiComponent,
    TestlistComponent,
    TemplateComponent,
    WebsitehomeComponent,
    ContactUsComponent,
    ViewfacultyProfilelistComponent,
    ViewfacultyProfiledetailsComponent,
    GalleryComponent,
    AluminiblogformComponent,
    AluminiblogdetailsComponent,
    ReportsComponent,
    GalleryformComponent,
    NewsFormComponent,
    NewseventDetailsComponent,
    SafePipe,
    NewUploadComponent,
    ErrorpageComponent,
    TrainingCoursesComponent,
    ReportsFacultyComponent
    
  ],
  imports: [
    BrowserModule,    
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ChartModule,
    HttpClientModule,
    HttpModule,
    ScheduleModule,
    NgxMaterialTimepickerModule,
    NgxPrintModule,
    FormsModule,
    ReactiveFormsModule,
    ChartsModule,
    RichTextEditorModule,
    FileUploadModule,
    NgxLoadingModule.forRoot({ animationType: ngxLoadingAnimationTypes.wanderingCubes,
      backdropBackgroundColour: 'rgba(0,0,0,0.1)', 
      backdropBorderRadius: '4px',
      primaryColour: '#262D61;', 
      secondaryColour: '#262D61;', 
      tertiaryColour: '#262D61;',
      fullScreenBackdrop:false})
  ],
  providers: [HyperService,SocketService,DatePipe,AgendaService, DayService, WeekService, WorkWeekService, MonthService, 
    {provide: LocationStrategy, useClass: HashLocationStrategy}],
  
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor (private _socketService: SocketService) {
    _socketService.init();
  }
}
