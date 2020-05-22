import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../_services/project.service';
import {Project} from '../classes/project';

@Component({
  selector: 'app-projet-detail',
  templateUrl: './projet-detail.component.html',
  styleUrls: ['./projet-detail.component.scss']
})
export class ProjetDetailComponent implements OnInit {

  projectId: number;
  projet: Project;

  constructor(private route: ActivatedRoute, private router: Router,
              private projetService: ProjectService) {
    this.projectId = this.route.snapshot.params.id;
    console.log(this.route.snapshot.params.id);
  }

  ngOnInit(): void {

    this.projetService.getProjectById(this.projectId)
      .subscribe(data => {
        this.projet = data;
      }, error => console.log(error));
  }

  reload(){
    this.router.navigate(['projet-detail', this.projectId]);
  }



}
