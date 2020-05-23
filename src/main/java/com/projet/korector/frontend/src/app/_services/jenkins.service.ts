import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'
  })
}
const API_URL = 'http://localhost:8085/api/jenkins/';
@Injectable({
  providedIn: 'root'
})
export class JenkinsService {

  constructor(private http: HttpClient) { }


public waitForBuild ( jobName: String ) : Observable<boolean>{

  console.log ("URL " + API_URL + 'waitBuild/' + jobName, httpOptions) ;
  return this.http.get<boolean>(API_URL  + 'waitBuild/' + jobName , httpOptions);

}
public getConsoleOutput( jobName: String ): Observable<String>{

console.log ("URL " + API_URL + 'getOutput/' + jobName, httpOptions) ;
return this.http.get(API_URL  + 'getOutput/' + jobName , { responseType: 'text' });
}

}

