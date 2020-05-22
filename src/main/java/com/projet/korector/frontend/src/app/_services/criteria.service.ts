import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Criteria} from "../classes/criteria";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class CriteriaService {
  private  baseUrl = environment.api_url;
  constructor(private http: HttpClient) { }

  public  createCriteria (criteria: Object) {
    return  this.http.post(`${this.baseUrl }/Criteria/createCriteria`,criteria, { responseType: 'text'});
  }
  public  updateCriteria(id: number, value: Criteria) : Observable<Criteria>{
    return this.http.put<Criteria>(`${this.baseUrl}/Criteria/updateCriteria/${id}`,value);
  }
  public  deleteCriteria(id:number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/Criteria/deleteCriteria/${id}`,{responseType: 'text'});
  }
  public getCriteriaList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/Criteria/allCriteria`);
  }
  public searchCriteria(name: string, type: string): Observable<Object> {
    return this.http.get(`${this.baseUrl}/Criteria/researchCriteria/${name}&${type}`);
  }

  getCriteriaById(id: number) {
    return this.http.get(`${this.baseUrl}/Criteria/id=${id}`);
  }

  searchCriteriaByType(type: string) : Observable<Array<Criteria>> {
    return this.http.get<Array<Criteria>>(`${this.baseUrl}/Criteria/researchCriteria/type=${type}`);
  }

  searchCriteriaByName(name: string) {
    return this.http.get(`${this.baseUrl}/Criteria/researchCriteria/name=${name}`);
  }
}
