import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { SessionCritere } from '../classes/session-critere';

@Injectable({
  providedIn: 'root'
})
export class SessionCritereService {

  public url =environment.api_url;

  constructor(private http: HttpClient) {}

  public createSessionCritere(sessionCritere : SessionCritere) : Observable<SessionCritere>
  {
    const routeQuery=this.url+"/createSessionCritere";
    return this.http.post<SessionCritere>(routeQuery,sessionCritere);
  }

  public updateSessionCritere(id:number,height:number,seuil:number, value:number) : Observable<any>
  {
    const routeQuery=this.url+"/updateSessionCritere/"+id+"/"+height+"/"+seuil+"/"+value;
    return this.http.get<any>(routeQuery); 
  }

  public getSessionCritereById(sessionCritereId : number) : Observable<SessionCritere>
  {
    const routeQuery=this.url+"/sessionCritereId/"+sessionCritereId;
    return this.http.get<SessionCritere>(routeQuery);
  }

  public getAllSessionCritere() : Observable<Array<SessionCritere>>
  {
    const routeQuery=this.url+"/getAllSessionCritere";
    return this.http.get<Array<SessionCritere>>(routeQuery);
  }

  public deleteSessionCritere(sessionId:number,sessionCritereId : number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteSessionCritere/"+sessionId+"/"+sessionCritereId;
    return this.http.delete<any>(routeQuery); 
  }
  
}
