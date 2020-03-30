import { Component, OnInit } from '@angular/core';
import { Project } from "../classes/project";
import {ProjectService} from '../_services/project.service';
import {Observable} from "rxjs";

import {Router} from "@angular/router";

@Component({
  selector: 'app-projet',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})

export class ProjectComponent implements OnInit {

  projets: Observable<Project[]>;

  constructor(private projetService: ProjectService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();

  }

  reloadData() {
    this.projets = this.projetService.getProjectList();
  }

  deleteProject(id: number) {
    this.projetService.deleteProject(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
    this.reloadData();
  }

  projetDetails(id: number){
    this.router.navigate(['projet-detail', id]);
  }

  updateProject(id: number){
    //this.router.navigate(['update', id]);
  }
}
