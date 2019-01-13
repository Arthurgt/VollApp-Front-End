import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-changeprofile',
  templateUrl: './changeprofile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ChangeProfileComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public clicked: boolean;

  constructor(private authService: LoginAuthService, private userService: UserService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
      this.clicked = false;
    });
  }

  saveUser(user:any, changeForm:any){
    this.userService.updateUser(user,this.loginuser.token).subscribe((response) => {
    if(response) {
      console.log(response);
      this.clicked=true;
    }
    });  
  }
}