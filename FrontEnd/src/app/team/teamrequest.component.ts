import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { ActivatedRoute } from "@angular/router";
import { TeamService } from './team.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teamrequest',
  templateUrl: './teamrequest.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamRequestComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: any = {};
  public team: any = {};
  public requests: any = [];

  constructor(private authService: LoginAuthService, private userService: UserService, private route: ActivatedRoute, private teamService: TeamService, private router: Router) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
    this.teamService.getTeamRequests(this.id,this.loginuser.token).subscribe (requests => {
        this.requests = requests;
    });
    this.teamService.getTeam(this.id,this.loginuser.token).subscribe(team => {
        this.team = team;
    });
  }
  acceptPlayer(player:any, request_id:any){
    player.team=this.team;
    this.userService.updateUser(player,this.loginuser.token).subscribe((response) => {
      if(response) {
        console.log(response);
      }
    });
    this.teamService.deleteTeamRequest(this.loginuser.token,request_id).subscribe((response) => {
        if(response) {
          console.log(response);
        }
    });
  }
  deleteRequest(request_id:any){
    this.teamService.deleteTeamRequest(this.loginuser.token,request_id).subscribe((response) => {
        if(response) {
          console.log(response);
        }
    });
  }
}