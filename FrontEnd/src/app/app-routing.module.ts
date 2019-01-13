import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { AuthGuard } from './authorization/auth.guard';
import { ProfileComponent } from './profile/profile.component';
import { ChangeProfileComponent } from './profile/changeprofile.component';
import { TeamComponent } from './team/team.component';
import { CreateTeamComponent } from './team/createteam.component';
import { FindTeamComponent } from './team/findteam.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'signup',component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admindashboard', component: AdmindashboardComponent, canActivate: [AuthGuard] },
  { path: 'userdashboard', component: UserdashboardComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'changeprofile', component: ChangeProfileComponent, canActivate: [AuthGuard] },
  { path: 'team', component: TeamComponent, canActivate: [AuthGuard] },
  { path: 'createteam', component: CreateTeamComponent, canActivate: [AuthGuard] },
  { path: 'findteam', component: FindTeamComponent, canActivate: [AuthGuard]},
  { path: '**', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
