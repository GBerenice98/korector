import { Component, OnInit } from '@angular/core';
import { Session } from '../classes/session';
import { SessionService } from '../_services/session.service';
import { JenkinsService } from '../_services/jenkins.service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})

export class SessionComponent implements OnInit {
  public sessions : Array<Session> = [];

  constructor(private sessionService : SessionService , private jenkinsService : JenkinsService) { }

  ngOnInit(): void {
    let letSessions : Array<Session> =[];
    this.sessionService.getAllSessionsByUser().subscribe(data => {
      data.forEach(s => {      
        letSessions.push(s);
      })
    });
    this.sessions=letSessions;
    console.log("sessions : ",this.sessions);
  }

  public deleteSession(session :Session) : void
  {
    this.sessionService.deleteSession(session.id).subscribe();
    this.sessions.splice(this.sessions.indexOf(session),1);
  }

  public runSession(){
   // this.jenkinsService.runProject();
    console.log("Start run session ")

  }

}
