import { Component, OnInit } from '@angular/core';
import { UserService } from '../user/user.service';
import { Router } from '@angular/router';
import { LoginAuthService } from '../authorization/login-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user : any = {};
  public loginuser: any = {};

  constructor(private userService: UserService, private router: Router ,private authService: LoginAuthService) {
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
   }

  ngOnInit() {
  }

  loginUser(user:any){
    this.userService.loginUser(user).subscribe((response) => {
    if(response) {
      if(response.token){
        localStorage.setItem('currentUser', JSON.stringify(response));
          this.router.navigate(['/profile']);
      }
    }
    });  
  }
}
