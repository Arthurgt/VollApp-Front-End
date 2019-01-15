import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) { 
    this.http=http;
  }

  saveTeam(team: any, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/saveTeam", team, {headers: headers});
  }

  getTeams(token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/teams", {headers: headers});
  }

  getTeam(id: string, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/getTeam/" + id, {headers: headers});
  }

  getPlayers(id: string, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/getPlayers/" + id, {headers: headers});
  }

  addPlayer(id: string, token: any, user: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/addPlayer/" + id, user, {headers: headers});
  }

  removePlayer(id: string, token: any, user: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/removePlayer/" + id, user, {headers: headers});
  }

  saveTeamRequest(id: string, token: any, user: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post("http://localhost:8080/saveJoinRequest/" + id, user.id, {headers: headers});
  }

  getTeamRequests(id: string, token: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get("http://localhost:8080/teamrequests/" + id, {headers: headers});
  }

  deleteTeamRequest(token: any, requestId: any): Observable<any>{
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.delete("http://localhost:8080/updateTeamRequest/" + requestId, {headers: headers});
  }
}
