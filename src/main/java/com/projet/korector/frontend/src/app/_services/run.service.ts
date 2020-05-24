import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Run } from '../classes/run';
import {SonarResults} from '../classes/sonar-results';
const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'})
}
const API_URL= 'http://localhost:8085/api';
@Injectable({
  providedIn: 'root'
})
export class RunService {

  public url =`${environment.api_url}/run`;
  constructor(private http: HttpClient) {
  }

  public createRun(sessionId : number) : Observable<Run>
  {
    let routeQuery=this.url+"/createRun/"+sessionId;
    return this.http.get<Run>(routeQuery);
  }
public sonarQubeRun(buildName : String , urlName : String, sessionId : Number, projectId : Number ) : Observable <any> {
  // s = API_URL_JENSON  + buildName + "/" +urlName
  //console.log("Url" + API_URL_JENSON  + buildName + "/" +urlName);
  console.log ( "Run URL" + API_URL  +  "/jenkins/run/" + buildName + "/" +urlName + "/" + sessionId + "/" + projectId);
  return this.http.get<boolean> ( API_URL  + "/jenkins/run/" + buildName + "/" +urlName + "/" + sessionId + "/" + projectId,  httpOptions);
}

public getLastBuild(sessionId : Number, projectId : Number ) {
  console.log ("Results URL" + API_URL  +  "/sonarResults/getResultsBySessProj/" + sessionId + "/" + projectId,  httpOptions);
  return this.http.get<any> (API_URL  +  "/sonarResults/getResultsBySessProj/" + sessionId + "/" + projectId,  httpOptions);

}

public runExistsBySession(sessionId : Number ){
  console.log ("Results URL" + API_URL  +  "/sonarResults/runExistsSession/" + sessionId,  httpOptions);
  return this.http.get<boolean> (API_URL  +  "/sonarResults/runExistsSession/" + sessionId ,  httpOptions);

}

public runExistsBySessionProject(sessionId : Number, projectId : Number ){
  console.log ( "Results URL" + API_URL  +  "/sonarResults/runExistsSessionProject/" + sessionId + "/" + projectId,  httpOptions);
  return this.http.get<boolean> (API_URL  +  "/sonarResults/runExistsSessionProject/" + sessionId + "/" + projectId,  httpOptions);

}
public getAllResultsByProjectId(projectId : Number ) : Observable<any>{
  console.log ("Results URL" + API_URL  +  "/sonarResults/getAllResultsProjects/"  + projectId,  httpOptions);
  return this.http.get<any> (API_URL  +  "/sonarResults/getAllResultsProjects" + "/" + projectId,  httpOptions);

}
}
