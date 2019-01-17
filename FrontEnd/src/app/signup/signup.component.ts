import { Component, OnInit } from '@angular/core';
import { UserService } from '../user/user.service';
import { LoginAuthService } from '../authorization/login-auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public registerForm: FormGroup;
  public submitted = false;
  public user: any = {};
  public loginuser: any = {};

  constructor(private userService: UserService, private router: Router , private authService: LoginAuthService, private formBuilder: FormBuilder) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required,Validators.minLength(2),Validators.pattern('^[A-Z]{1}[a-z]+')]],
      lastName: ['',[Validators.required,Validators.minLength(2),Validators.pattern('^[A-Z]{1}[a-z]+')]],
      email: ['',[Validators.required,Validators.email]],
      password: ['', [Validators.required,Validators.minLength(6),Validators.pattern('^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$')]]
  });
  }

  saveUser(user:any, registerForm:any){
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
  }
    user.enabled = true;
    user.role = 'ROLE_USERR';
    this.userService.saveUser(user).subscribe((response) => {
    if(response) {
      console.log(response);
      registerForm.reset();
      alert('UDANA REJESTRACJA');
      this.router.navigate(['/login']);
    }
    });  
  }
  get f() { return this.registerForm.controls; }
}
