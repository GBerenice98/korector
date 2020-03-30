import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import {SectionDetailComponent} from "./section-details/section-detail.component";
import {ProjetDetailComponent} from "./projet-details/projet-detail.component";
import {CreateProjetComponent} from "./createProjet/createProjet.component";
import {CreateSectionComponent} from "./createSection/createSection.component";
import {ProjectComponent} from "./project/project.component";
import {SectionComponent} from "./section/section.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'section-detail/id', component: SectionDetailComponent },
  { path: 'projet-detail/:id', component: ProjetDetailComponent },
  { path: 'createProjet', component: CreateProjetComponent },
  { path: 'createSection', component: CreateSectionComponent },
  { path: 'projet', component: ProjectComponent },
  { path: 'section', component: SectionComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
