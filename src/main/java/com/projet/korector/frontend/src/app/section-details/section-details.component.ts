import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Section} from "../classes/section";
import {SectionService} from "../_services/section.service";
import {Observable} from "rxjs";
import {User} from "../classes/user";
import {Project} from "../classes/project";
import {Session} from "../classes/session";
import {any} from "codelyzer/util/function";

@Component({
  selector: 'app-section-detail',
  templateUrl: './section-details.component.html',
  styleUrls: ['./section-details.component.scss']
})
export class SectionDetailComponent implements OnInit {

  sectionId: number;
  section: Section;
  name: string;
  teachers:  Array<User> =[];
  students: Array<User> =[];
  users: Array<User> =[];
  nbTeachers : number;

  constructor(private actRoute: ActivatedRoute, private  service: SectionService) {
  }

  ngOnInit(): void {
    this.sectionId = this.actRoute.snapshot.params.id;
    let sec;
    this.service.getSectionById(this.sectionId).subscribe(data => {
      this.section = data;
      console.log(this.section);
    }, error => console.log(error));


      this.service.getTeachers(this.sectionId).subscribe(data=>{
        this.teachers = data;
        this.nbTeachers = this.users.length;

      });


      this.service.getStudents(this.sectionId).subscribe(data=>{
        this.students = data;

      });

  }

}
