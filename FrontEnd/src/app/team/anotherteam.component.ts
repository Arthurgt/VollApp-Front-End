import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { ActivatedRoute } from "@angular/router";
import { TeamService } from './team.service';

@Component({
  selector: 'app-anotherteam',
  templateUrl: './anotherteam.component.html',
  styleUrls: ['./team.component.css']
})
export class AnotherTeamComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: any = {};
  public team: any = {};
  public players: any = [];
  public clicked: boolean;

  constructor(private authService: LoginAuthService, private userService: UserService, private route: ActivatedRoute, private teamService: TeamService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
      this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    }); 
    this.teamService.getTeam(this.id,this.loginuser.token).subscribe(team => {
      this.team = team;
  });
  this.teamService.getPlayers(this.id,this.loginuser.token).subscribe(players => {
    this.players = players;
  });
  this.clicked = false;
  }

  joinTeam() {
    this.clicked = true;
    this.teamService.saveTeamRequest(this.id,this.loginuser.token,this.user).subscribe((response) => {
      if(response) {
        console.log(response);
      }
    });
  }
}