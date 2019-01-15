import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { ActivatedRoute } from "@angular/router";
import { TeamService } from './team.service';

@Component({
  selector: 'app-captainrefuse',
  templateUrl: './captainrefuse.component.html',
  styleUrls: ['./team.component.css']
})
export class CaptainRefuseComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: any = {};
  public players: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService, private route: ActivatedRoute, private teamService: TeamService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
      this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    }); 
    this.teamService.getPlayers(this.id,this.loginuser.token).subscribe(players => {
      this.players = players;
  });
  }
  refuse(player:any){
    player.captain=1;
    this.user.captain=0;
    this.user.team = null;
    this.userService.updateUser(this.user,this.loginuser.token).subscribe((response) => {
        if(response) {
          console.log(response);
        }
        });  
    this.userService.updateUser(player,this.loginuser.token).subscribe((response) => {
      if(response) {
        console.log(response);
      }
      });
  }
  leaveTeam(){
    this.user.captain=0;
    this.user.team = null;
    this.userService.updateUser(this.user,this.loginuser.token).subscribe((response) => {
        if(response) {
          console.log(response);
        }
        });  
  }
}