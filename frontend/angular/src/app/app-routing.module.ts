import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutusComponent } from './pages/aboutus/aboutus.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CmmComponent } from './pages/cmm/cmm.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { CourseScheduleComponent } from './pages/course-schedule/course-schedule.component';
import { CourseRegComponent } from './pages/course-reg/course-reg.component';
import { QbmComponent } from './pages/qbm/qbm.component';
import { TestscheduleComponent } from './pages/testschedule/testschedule.component';
import { UmmComponent } from './pages/umm/umm.component';
import { CertificateComponent } from './components/certificate/certificate.component';
import { OnlineexamineportalComponent } from './pages/onlineexamineportal/onlineexamineportal.component';
import { FacultyprofileComponent } from './pages/facultyprofile/facultyprofile.component';
import { UploadfileComponent } from './pages/uploadfile/uploadfile.component';
import { TestadministratorComponent } from './pages/testadministrator/testadministrator.component';
import { TestsubmissionComponent } from './pages/testsubmission/testsubmission.component';
import { ParticipantProfileComponent } from './pages/participant-profile/participant-profile.component';
import { ImportparticipantsComponent } from './pages/importparticipants/importparticipants.component';
import { NewsandeventsComponent } from './pages/newsandevents/newsandevents.component';
import { AluminiComponent } from './pages/alumini/alumini.component';
import { TestlistComponent } from './pages/testlist/testlist.component';
import { TemplateComponent } from './pages/template/template.component';
import { WebsitehomeComponent } from './pages/websitehome/websitehome.component';
import { ContactUsComponent } from './pages/contact-us/contact-us.component';
import { ViewfacultyProfilelistComponent } from './pages/viewfaculty-profilelist/viewfaculty-profilelist.component';
import { ViewfacultyProfiledetailsComponent } from './pages/viewfaculty-profiledetails/viewfaculty-profiledetails.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { AluminiblogformComponent } from './pages/aluminiblogform/aluminiblogform.component';
import { AluminiblogdetailsComponent } from './pages/aluminiblogdetails/aluminiblogdetails.component';
import { ReportsComponent } from './pages/reports/reports.component';
import { GalleryformComponent } from './pages/galleryform/galleryform.component';
import { NewsFormComponent } from './pages/news-form/news-form.component';
import { NewseventDetailsComponent } from './pages/newsevent-details/newsevent-details.component';
import { ErrorpageComponent } from './pages/errorpage/errorpage.component';
import { TrainingCoursesComponent } from './pages/training-courses/training-courses.component'; 
import { ReportsFacultyComponent } from './pages/reports-faculty/reports-faculty.component';

const routes: Routes = [
  {path:'',component:WebsitehomeComponent},  
  {path:'oeep',component:OnlineexamineportalComponent,children:[
    {path:'',component:DashboardComponent},
    {path:'dashboard',component:DashboardComponent},
    {path:'facultyprofile',component:FacultyprofileComponent},
    {path:'aboutus',component:AboutusComponent},
    {path:'coursemastermanagement',component:CmmComponent},
    {path:'course_schedule',component:CourseScheduleComponent},
    {path:'course_registration',component:CourseRegComponent},
    {path:'questionbank_management',component:QbmComponent},
    {path:'test_schedule',component:TestscheduleComponent},
    {path:'user_management_module',component:UmmComponent},
    {path:'testadministrator',component:TestadministratorComponent},
    {path:'testsubmission',component:TestsubmissionComponent},
    {path:'participant_profile',component:ParticipantProfileComponent},
    {path:'importparticipants',component:ImportparticipantsComponent},
    {path:'testlist',component:TestlistComponent},
    {path:'result',component:ReportsComponent},
    {path:'template',component:TemplateComponent},
    {path:'certificate',component:CertificateComponent},
    {path:'add-gallery',component:GalleryformComponent},
    {path:'alumini-blog-details',component:AluminiblogdetailsComponent},
    {path:'add-alumini-blog',component:AluminiblogformComponent},
    {path:'add_news',component:NewsFormComponent},
    {path:'result-faculty',component:ReportsFacultyComponent}
  ]},
  {path:'aboutus',component:AboutusComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'certificate',component:CertificateComponent},
  {path:'facultyprofile',component:FacultyprofileComponent},
  {path:'upload',component:UploadfileComponent},
  {path:'testadministrator',component:TestadministratorComponent},
  {path:'testsubmission',component:TestsubmissionComponent},
  {path:'participant_profile',component:ParticipantProfileComponent},
  {path:'importparticipants',component:ImportparticipantsComponent},
  {path:'newsandevents',component:NewsandeventsComponent},
  {path:'alumini-blog',component:AluminiComponent},
  {path:'testlist',component:TestlistComponent},
  {path:'myhome',component:WebsitehomeComponent},
  {path:'contact-us',component:ContactUsComponent},
  {path:'faculty-list',component:ViewfacultyProfilelistComponent},
  {path:'faculty-details',component:ViewfacultyProfiledetailsComponent},
  {path:'gallery',component:GalleryComponent},
  {path:'add-alumini-blog',component:AluminiblogformComponent},
  {path:'alumini-blog-details',component:AluminiblogdetailsComponent},
  {path:'add-gallery',component:GalleryformComponent},
  {path:'add_news',component:NewsFormComponent},
  {path:'news-details',component:NewseventDetailsComponent},
  {path:'trainingcourses',component:TrainingCoursesComponent},
  {path: '**', component: ErrorpageComponent },
  {path: 'errorpage', component: ErrorpageComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 
