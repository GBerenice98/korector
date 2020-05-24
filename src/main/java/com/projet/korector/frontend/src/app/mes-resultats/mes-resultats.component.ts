import { Component, OnInit } from '@angular/core';
import { SessionService } from '../_services/session.service';
import { Session } from '../classes/session';
import { ProjectService } from '../_services/project.service';
import { FormControl } from '@angular/forms';
import { RunService } from '../_services/run.service';
import {Project} from '../classes/project';
import {SonarResults} from '../classes/sonar-results';
import {User} from '../classes/user';
import {TokenStorageService} from '../_services/token-storage.service';
import {Observable} from 'rxjs';
import { isEmpty } from 'rxjs/operators';
import { all } from 'codelyzer/util/function';

@Component({
  selector: 'app-mes-resultats',
  templateUrl: './mes-resultats.component.html',
  styleUrls: ['./mes-resultats.component.scss']
})
export class MesResultatsComponent implements OnInit {

  public allProjects : Array<Project>=[];
  projets: Observable<Project[]>;
  private allSonarResults : Array<SonarResults>=[];
  private NotEmptySonarResults : Array<SonarResults>=[];

  user: User;

private id : number;
  constructor( private sessionService : SessionService, 
    private projectService : ProjectService,
    private runService : RunService,private tokenStorage: TokenStorageService) { }
  ngOnInit(): void {
    //Initialisation des listes allProjects et allDepositeSessions
   // let projectList: Array<Project>=[];
    this.user = this.tokenStorage.getUser();

      this.projectService.getProject(this.user.id).subscribe((value) => {
      this.allProjects = value;
      console.log("Projet" + value);
      //console.log("all projects" + JSON.stringify(this.allProjects));
      console.log(Object.values(value));

      this.allProjects.forEach(pValue => {
        this.runService.getAllResultsByProjectId(pValue.id).subscribe(sonarR =>{
              console.log("sonarR" +sonarR);
              this.allSonarResults = sonarR;
              this.allSonarResults.forEach(allSonarRValue =>{
                if(  allSonarRValue !=null )
                {
                  console.log("RValue" + Object.values(allSonarRValue));
                    this.NotEmptySonarResults.push(allSonarRValue);
                    console.log("Not empty r " + this.NotEmptySonarResults);

                }

        
            });

    });
   // this.allProjects=projectList;

  });
});
  }

}


