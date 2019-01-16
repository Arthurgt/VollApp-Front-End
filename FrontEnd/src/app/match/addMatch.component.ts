import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { MatchService } from './match.service';

@Component({
  selector: 'app-addmatch',
  templateUrl: './addMatch.component.html',
  styleUrls: ['./match.component.css']
})
export class AddMatchComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public clicked: boolean;
  public match:any = {};

  constructor(private authService: LoginAuthService, private userService: UserService, private matchService: MatchService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));  
  }

  ngOnInit() {
    this.clicked=false;
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;    
    });
  }

  saveMatch(match:any, matchForm:any){
    match.status="ACTIVE";
    this.matchService.saveMatch(this.user.team.id, match,this.loginuser.token).subscribe((response) => {
    if(response) {
      console.log(response);
      this.clicked=true;
      matchForm.reset();
    }
    });  
  }
}
