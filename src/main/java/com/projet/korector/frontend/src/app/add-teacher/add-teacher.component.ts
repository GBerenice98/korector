import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { NgForm } from "@angular/forms";
import {User} from '../classes/user';
//import {SectionName} from '../classes/section-name';

import { UserService } from '../_services/user.service';
import { AdminService } from '../_services/admin.service';


@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.scss']
})
export class AddTeacherComponent implements OnInit {
   model = new User();
   submitted = false;
   public actionButton: string = 'Save';
   public sections: String [];


   constructor( private router: Router,private teacherService : UserService,private adminService : AdminService) {

   }

   ngOnInit(): void {

    let letSections: Array <String> = [];
    this.adminService.getAllSections().subscribe(sections =>{
      sections.forEach(element => {
        letSections.push(element["name"]);
       // return;

      console.log(element["name"]);
      });

    });

    this.sections = letSections;
    console.log("Element de obj section" );
    console.log(this.sections);
  }

   createTeacher() : void {
      this.teacherService.saveUser(this.model,2)
        .subscribe( data => {
          console.log(data);
          alert("User created successfully.");
        });
      this.submitted = true;


   }

    // TODO: Remove this when we're done
   get diagnostic() {
      return JSON.stringify(this.model);
     }

}


