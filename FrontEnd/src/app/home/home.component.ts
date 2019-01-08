import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from '../authorization/login-auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: LoginAuthService) { 
    this.authService.isLoggedIn();
  }

  ngOnInit() {
  }

}
