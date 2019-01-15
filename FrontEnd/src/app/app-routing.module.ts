import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './authorization/auth.guard';
import { ProfileComponent } from './profile/profile.component';
import { ChangeProfileComponent } from './profile/changeprofile.component';
import { TeamComponent } from './team/team.component';
import { CreateTeamComponent } from './team/createteam.component';
import { FindTeamComponent } from './team/findteam.component';
import { AnotherTeamComponent } from './team/anotherteam.component';
import { PlayerListComponent } from './team/playerlist.component';
import { TeamRequestComponent } from './team/teamrequest.component';
import { TeamRequestRefreshComponent } from './team/teamrequestrefresh.component';
import { TeamPlayerRefreshComponent } from './team/teamplayerrefresh.component';
import { CaptainRefuseComponent } from './team/captainrefuse.component';



const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'signup',component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'changeprofile', component: ChangeProfileComponent, canActivate: [AuthGuard] },
  { path: 'team', component: TeamComponent, canActivate: [AuthGuard] },
  { path: 'createteam', component: CreateTeamComponent, canActivate: [AuthGuard] },
  { path: 'findteam', component: FindTeamComponent, canActivate: [AuthGuard]},
  { path: 'anotherteam/:id', component: AnotherTeamComponent, canActivate: [AuthGuard]},
  { path: 'playerlist/:id', component: PlayerListComponent, canActivate: [AuthGuard]},
  { path: 'teamrequest/:id', component: TeamRequestComponent, canActivate: [AuthGuard]},
  { path: 'teamrequestrefresh/:id', component: TeamRequestRefreshComponent, canActivate: [AuthGuard]},
  { path: 'teamplayerrefresh/:id', component: TeamPlayerRefreshComponent, canActivate: [AuthGuard]},
  { path: 'captainrefuse/:id', component: CaptainRefuseComponent, canActivate: [AuthGuard]},
  { path: '**', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
