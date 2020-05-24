import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';



import { AppComponent } from './app.component';
import { SessionComponent } from './session/session.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';

import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {SessionDetailComponent} from './session-detail/session-detail.component';
import {HomeComponent} from './home/home.component';
import {CreateSessionComponent} from './create-session/create-session.component';

import {StatsComponent} from './stats/stats.component';
import {AddStudentComponent} from './add-student/add-student.component';
import {AddTeacherComponent} from './add-teacher/add-teacher.component';
import {StudentStatsComponent} from './student-stats/student-stats.component';
import {ProfsStatsComponent} from './profs-stats/profs-stats.component';
import {ClassesStatsComponent} from './classes-stats/classes-stats.component';

import {NgxSpinnerModule} from 'ngx-spinner';


import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {ProfileComponent} from './profile/profile.component';
import {ProjectComponent} from './project/project.component';
import {SectionComponent} from './section/section.component';
//import {SectionDetailComponent} from './section-details/section-details.component';
import {CreateProjetComponent} from './createProjet/createProjet.component';
import {ProjetDetailComponent} from './projet-details/projet-detail.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CreateCriteriaComponent } from './create-criteria/create-criteria.component';
import { SearchCriteriaComponent } from './search-criteria/search-criteria.component';
import { CriteriaListComponent } from './criteria-list/criteria-list.component';
import { CriteriaDetailsComponent } from './criteria-details/criteria-details.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';

import {CreateSectionComponent} from './createSection/createSection.component';

import { CreateCriteriaGroupComponent } from './create-criteria-group/create-criteria-group.component';
import { CriteriaGroupDetailsComponent } from './criteria-group-details/criteria-group-details.component';
import { CriteriaGroupListComponent } from './criteria-group-list/criteria-group-list.component';
import { UpdateCriteriaGroupComponent } from './update-criteria-group/update-criteria-group.component';
import { Oauth2RedirectHandlerComponent } from './oauth2-redirect-handler/oauth2-redirect-handler.component';
import { UpdateCriteriaComponent } from './update-criteria/update-criteria.component';
import { DatePipe } from '@angular/common';
import { ValidationModalComponent } from './validation-modal/validation-modal.component';
import { AjoutSessionCritereComponent } from './ajout-session-critere/ajout-session-critere.component';


import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {SectionDetailComponent} from "./section-details/section-details.component";
import { MesResultatsComponent } from './mes-resultats/mes-resultats.component';
@NgModule({
  declarations: [
    AppComponent,
    SessionComponent,
    NavComponent,
    FooterComponent,
    NoPageFoundComponent,
    SessionDetailComponent,
    CreateSessionComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BoardAdminComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    ProfileComponent,
    StatsComponent,
    AddStudentComponent,
    AddTeacherComponent,
    StudentStatsComponent,
    ProfsStatsComponent,
    ClassesStatsComponent,
    ProjectComponent,
    SectionComponent,
    SectionDetailComponent,
    CreateProjetComponent,
    ProjetDetailComponent,
    CreateSectionComponent,
    CreateCriteriaComponent,
    SearchCriteriaComponent,
    CriteriaListComponent,
    CriteriaDetailsComponent,
    CreateCriteriaGroupComponent,
    CriteriaGroupDetailsComponent,
    CriteriaGroupListComponent,
    UpdateCriteriaGroupComponent,
    Oauth2RedirectHandlerComponent,
    UpdateCriteriaGroupComponent,
    UpdateCriteriaComponent,
    ValidationModalComponent,
    AjoutSessionCritereComponent,
    //MesResultatsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    NgxSpinnerModule, /* for using spinner */
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule,
    ReactiveFormsModule
   ],
  providers: [
    authInterceptorProviders,
    MatDatepickerModule,
    MatCardModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DatePipe,
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'}
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
