import { Component } from '@angular/core';
import { LocalStorage } from 'src/app/core/local_storage.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'examportal';
  isfaculty : boolean;
  isparticipant : boolean;
  public logged_session :any;
  isNotLogged : boolean;

  load();
  load(){
      this.logged_session = LocalStorage.getValue("userid");
      if(typeof this.logged_session != 'undefined' && this.logged_session != null && this.logged_session > 0){
      if( LocalStorage.getValue("roleid") == 2){
        this.isfaculty = true;
        this.isparticipant = false;
        //console.log( this.isfaculty )
        }else if(LocalStorage.getValue("roleid") == 4){
              this.isfaculty = false;
              this.isparticipant = true;
            // console.log(this.isparticipant )
        }else {
          this.isfaculty = false;
          this.isparticipant = false;
          this.isNotLogged = false;
        // console.log(this.isparticipant )
        }
    }else{
        this.isNotLogged = true;
        this.isfaculty = false;
        this.isparticipant = false;
    }
  }

}
