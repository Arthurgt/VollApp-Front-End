import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { TeamService } from './team.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-createteam',
  templateUrl: './createteam.component.html',
  styleUrls: ['./team.component.css']
})
export class CreateTeamComponent implements OnInit {
  public registerForm: FormGroup;
  public submitted = false;
  public loginuser: any = {};
  public user: any = {};
  public team : any = {};
  public clicked: boolean;

  constructor(private authService: LoginAuthService, private userService: UserService, private teamService: TeamService, private formBuilder: FormBuilder) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
      this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
      this.clicked = false;
    });
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required,Validators.minLength(2)]],
      info: ['',[Validators.required,Validators.minLength(5)]]
  });
  }

  saveTeam(user:any, team:any){
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
  }
    user.team=team;
    user.captain='1';
    this.userService.updateUser(user,this.loginuser.token).subscribe((response) => {
      if(response) {
        console.log(response);   
      }
      }); 
      this.clicked=true; 
  }
  get f() { return this.registerForm.controls; }
}