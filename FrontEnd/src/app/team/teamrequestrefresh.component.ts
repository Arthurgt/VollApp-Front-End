import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { ActivatedRoute } from "@angular/router";
import { TeamService } from './team.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teamrequestrefresh',
  templateUrl: './teamrequestrefresh.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamRequestRefreshComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public id: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService, private route: ActivatedRoute, private teamService: TeamService, private router: Router) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
  }
}