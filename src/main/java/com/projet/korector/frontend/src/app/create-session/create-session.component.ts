import {Component, OnInit} from '@angular/core';
import {Project} from '../classes/project';
import {Session} from '../classes/session';
import {SessionService} from '../_services/session.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatCardModule} from '@angular/material/card';
import { ProjectService } from '../_services/project.service';
import { Router } from '@angular/router';
import { ValidationModalComponent } from '../validation-modal/validation-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SessionCritere } from '../classes/session-critere';
import { SessionCritereService } from '../_services/session-critere.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss']
})
export class CreateSessionComponent implements OnInit {

  public projects : Array<Project>=[];
  public selectedProjects : Array<Project> = [];

  public typeSession : string;
  public nameSession: string;
  public heureDepot : string;
  public userRole: string;

  constructor(private router :Router,
              private modalService: NgbModal,
              private sessionService : SessionService, 
              private projectService : ProjectService,
              private http: HttpClient, 
              private tokenStorage: TokenStorageService) {
    this.typeSession='normal';
    this.nameSession="";
  }

  ngOnInit(): void {

    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' , 
      'Authorization' : 'Bearer ' + this.tokenStorage.getToken()})
    };
    this.http.get(environment.api_base_url + '/user/me', httpOptions).subscribe(
      data => {
        this.tokenStorage.saveUser(data);      
        this.userRole = this.tokenStorage.getUser().roles.map(x => x.name).join(',');
        this.userRole = this.userRole.replace("ROLE_", "");
        console.log("profile",this.userRole)
      });
      
    let listProjects: Array<Project>=[];
    this.projectService.getProjectList().subscribe(data => {
      data.forEach(p => {
        listProjects.push(p);
      })
    });
    console.log("projects :", listProjects)
    this.projects=listProjects;
  }

  public createSession(): void
  {        
    let dateDepot:string="null";
    let heureDepot:string="null";

    if(this.typeSession=="depot") 
    {
      dateDepot  = document.getElementsByName("date")[0]["value"];
      heureDepot  = document.getElementsByName("heure")[0]["value"];
    }

    let createSession = new Session(this.nameSession, dateDepot, heureDepot);
    let selectedProjectIds: Array<number>=[];
   
    this.selectedProjects.forEach(p=>{ selectedProjectIds.push(p.id)});
    createSession.projects=selectedProjectIds;
        
    console.log("session à créer  : ", createSession);
    console.log("namesession",this.nameSession);
    if(this.nameSession=="")
    {
      this.openValidationModal("Vous devez donner un nom à votre Session");
    }
    else 
    {
      this.sessionService.createSession(createSession).subscribe(data =>{
        if(data.id!=null) 
        {
          this.router.navigate(['/ajout-sessionCritere/'+data.id]);
        }
        else this.openValidationModal("Une erreur est survenue. Veuillez contacter le support.");
      });    
    }
  }

  public addProjetToSelectedProject(p : Project) : void{
    if(!this.selectedProjects.includes(p)) this.selectedProjects.push(p);
    else this.openValidationModal("Ce projet est déjà dans la liste");
  } 

  public changeSessionType(type : string) : void { this.typeSession=type; }

  public retrieveProjectToselected(project : Project) : void
  {
    this.selectedProjects.splice(this.selectedProjects.indexOf(project),1);
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = message;
  }
}
