import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Section} from "../classes/section";
import {environment} from "../../environments/environment";


const AUTH_API = 'http://localhost:8085/api/ressource';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class SectionService {
  public url =environment.api_url;
  constructor(private http: HttpClient) { }

  getSectionById(id: number): Observable<any> {
    return this.http.get(this.url+"/SectionById/"+id);
  }

  createSection(section: Section) {
    console.log("Dans le service");
    return this.http.post(this.url+"/createSection", section, { responseType: 'text'});
  }

  updateSection(id: number, value: any): Observable<Object> {
    return this.http.put(`${AUTH_API}/${id}`, value);
  }

  deleteSection(id: number) {
    return this.http.delete(this.url+"/deleteSection/" + id, { responseType: 'text'});
  }

  getSectionList(): Observable<any> {
    return this.http.get(this.url+"/allSections");
  }

  getTeachers(): Observable<any> {
    return this.http.get("http://localhost:8080/api/user/findAllProf");
  }

  getStudents(): Observable<any> {
    return this.http.get("http://localhost:8080/api/user/findAllStudent");
  }
}
