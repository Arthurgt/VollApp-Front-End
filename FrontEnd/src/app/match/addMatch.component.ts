import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { MatchService } from './match.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addmatch',
  templateUrl: './addMatch.component.html',
  styleUrls: ['./match.component.css']
})
export class AddMatchComponent implements OnInit {
  public registerForm: FormGroup;
  public submitted = false;
  public loginuser: any = {};
  public user: any = {};
  public match:any = {};

  constructor(private authService: LoginAuthService, private router: Router, private userService: UserService, private matchService: MatchService, private formBuilder: FormBuilder) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));  
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;    
    });
    this.registerForm = this.formBuilder.group({
      city: ['',[Validators.required,Validators.minLength(2),Validators.pattern('^[A-Z]{1}[a-z]+')]],
      street: ['',[Validators.required,Validators.minLength(2)]],
      nr: ['', [Validators.required,Validators.pattern('^[0-9]+[/]?[0-9]*[A-Za-z]?')]],
      hour:['', [Validators.required]],
      minute:['', [Validators.required]],
      date:['', [Validators.required]]
  });
  }

  saveMatch(match:any){
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
  }
    match.status="ACTIVE";
    this.matchService.saveMatch(this.user.id,this.user.team.id, match,this.loginuser.token).subscribe((response) => {
    if(response) {
      console.log(response);
      this.router.navigate(['/match']);
    }
    });  
  }
  get f() { return this.registerForm.controls; }
}
