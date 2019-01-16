import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-calendarrefresh',
  templateUrl: './calendarrefresh.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarRefreshComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
  }

}
