import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { User } from '../classes/user';
import { Section} from '../classes/section';

const API_URL = 'http://localhost:8085/api/';
const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class AdminService {
user : User ;
  constructor(private http: HttpClient) {

   }

   getstatsResponse() : Observable <any>{
    return this.http.get(API_URL + 'resource/stats', { responseType: 'text' });

   }
  findAllStudent() : Observable <User[]> {
    return this.http.get<User[]>(API_URL  + 'user/findAllStudent',httpOptions);

  }
  findAllProf() : Observable <User[]> {
    return this.http.get<User[]>(API_URL  + 'user/findAllProf',httpOptions);

  }
  getAllSections(): Observable<Section[]> {
    console.log("getAllSections : ");
    return this.http.get<Section[]>(API_URL+"allSections",httpOptions);
  }

  public deleteUser(userId : Number) : Observable<any>{
    console.log("delete user  2: ",userId);
    console.log("url " + API_URL  + 'user/deleteUser/' + userId);

    return this.http.delete<any>(API_URL  + 'user/deleteUser/' + userId );
  }



}
