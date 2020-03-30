import { Component, OnInit } from '@angular/core';
import {SectionService} from "../_services/section.service";
import {Router} from "@angular/router";
import {Section} from "../classes/section";

@Component({
  selector: 'app-createSection',
  templateUrl: './createSectioncomponent.html',
  styleUrls: ['./createSection.component.scss']
})
export class CreateSectionComponent implements OnInit {

  section: Section = new Section();
  submitted = false;
  liste: ["etudiant1", "etudiant2", "etudiant3" ];

  constructor(private sectionService: SectionService,
              private router: Router) { }

  ngOnInit(): void {
  }

  save() {
    this.section.students = this.liste;
    this.sectionService.createSection(this.section)
      .subscribe(data => console.log(data), error => console.log(error));
    this.section = new Section();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/section']);
  }


}
