import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { ActivatedRoute } from "@angular/router";
import { MatchService } from '../match/match.service';

@Component({
  selector: 'app-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./calendar.component.css']
})
export class SchedulerComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: any = {};
  public matches: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService, private route: ActivatedRoute, private matchService: MatchService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
    
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;    
    });   
  }

  show(){
  this.matchService.getMatches(this.user.id,this.loginuser.token,this.id).subscribe(matches => {
    this.matches = matches;
  });}
}
