import { Component, OnInit } from '@angular/core';
import {Section} from '../classes/section';
import {SectionService} from '../_services/section.service';
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.scss']
})

export class SectionComponent implements OnInit {
  sections: Observable<Section[]>;

  constructor(private sectionService: SectionService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
    console.log(this.sections);
  }

  reloadData() {
    this.sections = this.sectionService.getSectionList();
  }

  deleteSection(id: number) {
    this.sectionService.deleteSection(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  sectionDetails(id: number){
    this.router.navigate(['section-detail', id]);
  }

  updateSection(id: number){
    //this.router.navigate(['update', id]);
  }
}
