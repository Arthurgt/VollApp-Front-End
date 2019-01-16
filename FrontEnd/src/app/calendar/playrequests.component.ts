import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';
import { UserService } from '../user/user.service';
import { MatchService } from '../match/match.service';
import { ActivatedRoute } from "@angular/router";
@Component({
  selector: 'app-playrequests',
  templateUrl: './playrequests.component.html',
  styleUrls: ['./calendar.component.css']
})
export class PlayRequestsComponent implements OnInit {

  public loginuser: any = {};
  public user: any = {};
  public requests: any = {};
  public id: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService, private matchService: MatchService, private route: ActivatedRoute) { 
    this.authService.isLoggedIn();
    this.loginuser = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.userService.getUser(this.loginuser.token).subscribe(user => {
      this.user = user;
    });
    this.matchService.getPlayRequests(this.id,this.loginuser.token).subscribe(requests => {
        this.requests = requests;
      });
  }
  deletePlayRequest(request_id:any){
    this.matchService.deletePlayRequest(this.loginuser.token,request_id).subscribe((response) => {
        if(response) {
          console.log(response);
        }
    });
  }
  acceptPlayRequest(request_id:any){
    this.matchService.acceptPlayRequest(this.loginuser.token,request_id).subscribe((response) => {
        if(response) {
          console.log(response);
        }
    }); 
  }
}