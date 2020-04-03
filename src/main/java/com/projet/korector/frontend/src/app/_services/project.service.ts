import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
const AUTH_API = 'http://localhost:8080/api/ressource';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getProjectById(id: number): Observable<any> {
    return this.http.get(`${AUTH_API}/project/${id}`);
  }

  getProject(id: string): Observable<any> {
    return this.http.get(`${AUTH_API}/getProject/${id}`);
  }

  updateProject(id: number, value: any): Observable<Object> {
    return this.http.put(`${AUTH_API}/${id}`, value);
  }

  deleteProject(id: number): Observable<any> {
    return this.http.delete(`${AUTH_API}/deleteProject/${id}`, { responseType: 'text' });
  }

  getProjectList(): Observable<any> {
    return this.http.get(`${AUTH_API}/allProjects`);
  }

}
