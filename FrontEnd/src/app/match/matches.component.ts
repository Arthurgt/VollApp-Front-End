import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { MatchService } from './match.service';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchesComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public matches: any = [];
  public message: string;

  constructor(private authService: LoginAuthService, private userService: UserService, private matchService: MatchService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
    this.matchService.getActiveMatches(this.loginuser.token).subscribe(matches => {
        this.matches = matches;
      });
  }
  saveMatchRequest(matchid: any){
    this.matchService.savePlayRequest(this.user.team.id,this.loginuser.token,matchid).subscribe((response) => {
        if(response) {
          console.log(response);
        }
    });  
  }
}