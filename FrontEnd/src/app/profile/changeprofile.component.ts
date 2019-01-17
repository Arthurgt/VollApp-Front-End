import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-changeprofile',
  templateUrl: './changeprofile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ChangeProfileComponent implements OnInit {
  public registerForm: FormGroup;
  public submitted = false;
  public loginuser: any = {};
  public user: any = {};
  public clicked: boolean;

  constructor(private authService: LoginAuthService, private userService: UserService, private formBuilder: FormBuilder) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
      this.clicked = false;
    });
    this.registerForm = this.formBuilder.group({
      nick: ['', [Validators.minLength(3)]],
      position: [''],
      info: ['', [Validators.minLength(10)]]
  });
  }

  saveUser(user:any){
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
  }
    this.userService.updateUser(user,this.loginuser.token).subscribe((response) => {
    if(response) {
      console.log(response);
      this.clicked=true;
    }
    });
  }

  get f() { return this.registerForm.controls; }
}
