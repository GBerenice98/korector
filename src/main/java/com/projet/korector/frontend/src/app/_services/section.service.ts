import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/ressource';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class SectionService {

  constructor(private http: HttpClient) { }

  getSectionById(id: number): Observable<any> {
    return this.http.get(`${AUTH_API}/sectionById/${id}`);
  }

  createSection(section: Object): Observable<Object> {
    return this.http.post(`${AUTH_API}/createSection`, section);
  }

  updateSection(id: number, value: any): Observable<Object> {
    return this.http.put(`${AUTH_API}/${id}`, value);
  }

  deleteSection(id: number): Observable<any> {
    return this.http.delete(`${AUTH_API}/deleteSection/${id}`, { responseType: 'text' });
  }

  getSectionList(): Observable<any> {
    return this.http.get(`${AUTH_API}/allSections`);
  }

}
