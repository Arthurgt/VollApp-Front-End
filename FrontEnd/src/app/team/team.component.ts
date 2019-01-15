import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { TeamService } from './team.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: string;
  public players: any = [];

  constructor(private authService: LoginAuthService, private userService: UserService, private teamService: TeamService, private router: Router) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
      this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
      this.id = user.team.id;
    });
  }
  leaveTeam(user:any){
    if(user.captain=='0' || !user.captain){
    user.team=null;
    this.userService.updateUser(user,this.loginuser.token).subscribe((response) => {
      if(response) {
        console.log(response);
      }
      });}
    else {
      this.router.navigate(['/captainrefuse/' + this.id]);
    }    
  }
}
