import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './user/user.service';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './authorization/auth.guard';
import { LoginAuthService } from './authorization/login-auth.service';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AdmindashboardComponent,
    UserdashboardComponent,
    SignupComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [UserService,AuthGuard, LoginAuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
