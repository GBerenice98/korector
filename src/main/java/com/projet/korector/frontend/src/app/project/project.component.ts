import {Component, OnInit} from '@angular/core';
import {Project} from '../classes/project';
import {ProjectService} from '../_services/project.service';
import {Observable} from 'rxjs';

import {Router} from '@angular/router';
import {TokenStorageService} from '../_services/token-storage.service';
import {User} from '../classes/user';
import {isEmpty} from 'rxjs/operators';
import { SessionService } from '../_services/session.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { environment } from 'src/environments/environment';
import { Session } from '../classes/session';
import { ValidationModalComponent } from '../validation-modal/validation-modal.component';

@Component({
  selector: 'app-projet',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})

export class ProjectComponent implements OnInit {
  user: User;
  projets: Observable<Project[]>;
  empty = false;
  public userRole : string;
  public hideView : boolean=true;
  public allProjects : Array<Project>=[];
  public allDepositeSessions : Array<Session> =[];
  public selectedProject : Project;
  public selectedSession : Session;
  public dateFormat="dd/MM/yyyy";
  public dateDelimiter="/";

  constructor(private projetService: ProjectService,
              private router: Router, private tokenStorage: TokenStorageService,
              private sessionService :SessionService,
              private http: HttpClient,
              private modalService: NgbModal
               ) {}

  ngOnInit() {
    this.user = this.tokenStorage.getUser();
    this.reloadData();

    //Recuperation du profile de l'utilisateur
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
      
      //Initialisation des listes allProjects et allDepositeSessions
      let projectList: Array<Project>=[];
      this.projetService.getProjectList().subscribe(data => {
        data.forEach(p => { projectList.push(p); })
      });
      this.allProjects=projectList;

      //session des profs
      let sessionsDepotList: Array<Session>=[];
      this.sessionService.getSessionsDepot().subscribe(data => {
        data.forEach(p => { sessionsDepotList.push(p); })
      });
      this.allDepositeSessions=sessionsDepotList;
  }

  reloadData() {
    this.projets = this.projetService.getProject(this.user.id);

    this.projets.subscribe((value) => {
      console.log(value);
      // tslint:disable-next-line:triple-equals
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
    }, () => {
      console.log('Fini !');
    });
}

  deleteProject(id: number) {
    this.projetService.deleteProject(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
    this.reloadData();
  }

  submitTest(){}

  projetDetails(id: number) {
    this.router.navigate(['projet-detail', id]);
  }

  updateProject(id: number) {
    this.router.navigate(['projet-detail', id]);
  }

  public submit() : void 
  {
    this.hideView ? this.hideView=false : this.hideView=true;
   // this.sessionService.addProjectToSession(13,3).subscribe(data=> { console.log("data",data)});
  }

  public submitProject(p:Project,s:Session) : void{

    console.log("P : ",p,"  \n S : ",s)

    let date = s.date_depot;
    let regexDate = /^((([1-9]|0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|(([1][26]|[2468][048]|[3579][26])00))))$/g;
    let dateDepot = date.match(regexDate)[0];
    console.log("dateDepot : ",dateDepot);

    let heure = s.heureDepot;
    let regexHeure = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/g;
    let heureDepot = heure.match(regexHeure)[0];  
    console.log("heureDepot : ",heureDepot);

    let today=new Date();
    //let currentDate = this.formattedDate(today);
    let currentHour =today.toLocaleTimeString([],{hour: '2-digit', minute:'2-digit'})

    console.log(" compareDate ", this.compareDate(this.stringToDate(dateDepot), today));
    console.log(" compareHeure ", this.compareHour(heureDepot,currentHour));

    if(this.compareDate(this.stringToDate(dateDepot), today) == 0)
    {
      if(this.compareHour(heureDepot,currentHour) == 0)
      {
        this.sessionService.addProjectToSession(p.id,s.id).subscribe(data=> { console.log("data",data)});
        this.openValidationModal("Votre projet : "+p.name+" a bien été déposé dans la session : "+s.name

        )
      }
      else this.openValidationModal("Oups ! Le délai est passé, contactez votre professeur.");
    }
    else this.openValidationModal("Oups ! Le délai est passé, contactez votre professeur.");
    this.hideView=true;
  }

  // public formattedDate(d : Date) : string{
  //   return [d.getDate(), d.getMonth()+1, d.getFullYear()]
  //       .map(n => n < 10 ? `0${n}` : `${n}`).join('/');
  // }

  public  stringToDate(_date)
  {
    var formatLowerCase=this.dateFormat.toLowerCase();
    var formatItems=formatLowerCase.split(this.dateDelimiter);
    var dateItems=_date.split(this.dateDelimiter);
    var monthIndex=formatItems.indexOf("mm");
    var dayIndex=formatItems.indexOf("dd");
    var yearIndex=formatItems.indexOf("yyyy");
    var month=parseInt(dateItems[monthIndex]);
    month-=1;
    var formatedDate = new Date(dateItems[yearIndex],month,dateItems[dayIndex]);
    return formatedDate;
  }

  public compareDate(dateDepot : Date, dateJour : Date) : number
  {
    if(dateJour.getFullYear()<=dateDepot.getFullYear()){
      if(dateJour.getMonth()<=dateDepot.getMonth()){
        if(dateJour.getDate()<=dateDepot.getDate()){
          return 0;
        }
      }
    }
    return 1;
  }

  public compareHour(heureDepot:string, heureJour:string) : number
  {
    let hDepot = parseInt(heureDepot.split(':')[0]);
    let mDepot = parseInt(heureDepot.split(':')[1]);
    let hJour = parseInt(heureJour.split(':')[0]);
    let mJour = parseInt(heureJour.split(':')[1]);
    
    if(hJour < hDepot){
      return 0;
    } 
    else if(hJour==hDepot)
    {
      if(mJour <= mDepot){
        return 0;
      }
    }
    return 1;
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = message;
  }
}
