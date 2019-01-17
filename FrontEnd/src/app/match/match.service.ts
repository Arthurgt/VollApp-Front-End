import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class MatchService {

  constructor(private http: HttpClient) { 
    this.http = http;  
  }
  saveMatch(user_id:string, id: string, match: any, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/saveMatch/" + id + "/" + user_id, match, {headers: headers});
  }
  updateMatch(match: any, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/updateMatch", match, {headers: headers});
  }
  getActiveMatches(token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/activematches", {headers: headers});
  }
  savePlayRequest(id: string, token: any, matchid: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/savePlayRequest/" + id, matchid, {headers: headers});
  }
  getPlayRequests(id: string, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/playrequests/" + id, {headers: headers});
  }
  deletePlayRequest(token: any, requestId: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.delete("http://localhost:8080/deletePlayRequest/" + requestId, {headers: headers});
  }
  acceptPlayRequest(token: any, requestId: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.delete("http://localhost:8080/acceptPlayRequest/" + requestId, {headers: headers});
  }
  getMatches(sender_id:any ,token: any, teamid:any){
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/getmatches/" + teamid + "/" + sender_id, {headers: headers});
  }
}