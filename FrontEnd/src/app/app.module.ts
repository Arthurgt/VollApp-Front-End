import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './user/user.service';
import { MatchService } from './match/match.service';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './authorization/auth.guard';
import { LoginAuthService } from './authorization/login-auth.service';
import { ProfileComponent } from './profile/profile.component';
import { ChangeProfileComponent } from './profile/changeprofile.component';
import { TeamComponent } from './team/team.component';
import { CreateTeamComponent } from './team/createteam.component';
import { FindTeamComponent } from './team/findteam.component';
import { AnotherTeamComponent } from './team/anotherteam.component';
import { TeamService } from './team/team.service';
import { PlayerListComponent } from './team/playerlist.component';
import { TeamRequestComponent } from './team/teamrequest.component';
import { TeamRequestRefreshComponent } from './team/teamrequestrefresh.component';
import { TeamPlayerRefreshComponent } from './team/teamplayerrefresh.component';
import { CaptainRefuseComponent } from './team/captainrefuse.component';
import { CalendarComponent } from './calendar/calendar.component';
import { MatchComponent } from './match/match.component';
import { AddMatchComponent } from './match/addMatch.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker'
import { MatchesComponent } from './match/matches.component';
import { OperationComponent } from './match/operation.component';
import { PlayRequestsComponent } from './calendar/playrequests.component';
import { CalendarRefreshComponent } from './calendar/calendarrefresh.component';
import { SchedulerComponent } from './calendar/scheduler.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    ChangeProfileComponent,
    TeamComponent,
    CreateTeamComponent,
    FindTeamComponent,
    AnotherTeamComponent,
    PlayerListComponent,
    TeamRequestComponent,
    TeamPlayerRefreshComponent,
    TeamRequestRefreshComponent,
    CaptainRefuseComponent,
    CalendarComponent,
    MatchComponent,
    AddMatchComponent,
    MatchesComponent,
    OperationComponent,
    PlayRequestsComponent,
    CalendarRefreshComponent,
    SchedulerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [UserService,AuthGuard,LoginAuthService,TeamService,MatchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
