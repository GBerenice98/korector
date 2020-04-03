import { Component, OnInit } from '@angular/core';
import { Project } from '../classes/project';
import { Criteria } from '../classes/criteria';
import { Session } from '../classes/session';
import { SessionService } from '../services/session.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatCardModule} from '@angular/material/card';




@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss']
})
export class CreateSessionComponent implements OnInit {

  //public projects : Array<Project> =[new Project("p1",5), new Project("p2",15), new Project("p3",8), new Project("p4",13)];
  public projects : Array<Project> =[new Project("p1",5), new Project("p2",15), new Project("p3",8), new Project("p4",13)];
  public criterias : Array<Criteria> =[new Criteria("c1"), new Criteria("c2"), new Criteria("c3"), new Criteria("c4")];
  public selectedProjects : Array<Project> = [];
  public selectedCriteria : Array<Criteria> = [];
  public typeSession : string;

  constructor(private sessionService : SessionService) {
    this.typeSession='normal';
  }

  ngOnInit(): void {

    // let listProjects: Array<Project>;
    // this.projectService().getAllProjects().subscribe(data => {
    //   data.forEach(s => {
    //     listProjects.push(s);
    //   })
    // });
    // this.projects=listProjects;

  }

  public addProjetToSelectedProject(p : Project) : void{

    if(!this.selectedProjects.includes(p)) this.selectedProjects.push(p);
    else console.log("déjà sélectionné");
  }

  public addCriteriaToSelectedCriteria(c : Criteria) : void{
    if(!this.selectedCriteria.includes(c)) this.selectedCriteria.push(c);
    else console.log("déjà sélectionné");
  }

  public changeSessionType(type : string) : void
  {
    this.typeSession=type;
  }

  public createSession(): void{
    let nameSession : string =document.getElementsByName("nameSession")[0]["value"];
    let createSession = new Session(nameSession, this.selectedProjects);
    //createSession.projects=this.selectedProjects;
    //createSession.criteria=this.selectedCriteria;

    this.sessionService.createSession(createSession).subscribe(data =>
      console.log("data", data)
    );
  }

  public retrieveProjectToselected(project : Project) : void
  {
    this.selectedProjects.splice(this.selectedProjects.indexOf(project),1);
  }
}