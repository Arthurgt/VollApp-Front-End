import { Component, OnInit } from '@angular/core';
import { UserService } from '../user/user.service';
import { LoginAuthService } from '../authorization/login-auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user: any = {};
  public loginuser: any = {};

  constructor(private userService: UserService, private authService: LoginAuthService) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
  }

  saveUser(user:any, userForm:any){
    user.enabled = true;
    user.role = 'USER';
    this.userService.saveUser(user).subscribe((response) => {
    if(response) {
      console.log(response);
      userForm.reset();
    }
    });  
  }
}
