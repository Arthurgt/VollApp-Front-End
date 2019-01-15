import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { TeamService } from './team.service';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'appfindteam-parent',
  templateUrl: './findteam.component.html',
  styleUrls: ['./team.component.css']
})
export class FindTeamComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public teams: any = [];
  public message: string;

  constructor(private authService: LoginAuthService, private userService: UserService, private teamService: TeamService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
      this.teamService.getTeams(this.loginuser.token).subscribe(teams => {
      this.teams = teams;
      this.message = null;
    });
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
  }
}