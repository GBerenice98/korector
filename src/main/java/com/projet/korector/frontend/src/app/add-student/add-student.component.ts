import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import {User} from '../classes/user';
import { UserService } from '../_services/user.service';
import { AdminService } from '../_services/admin.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {

   model = new User(); 

   submitted = false;
   public sections: String [];
   public actionButton: string = 'Save';



   constructor(private route: ActivatedRoute, 
          private router: Router,private userService: UserService,private adminService : AdminService, private spinner: NgxSpinnerService) { 

 
   }
   ngOnInit(): void {
    let letSections: Array <String> = [];
    this.adminService.getAllSections().subscribe(sections =>{
      sections.forEach(element => {
        letSections.push(element["name"]);
      console.log(element["name"]);
    });

  });

  this.sections = letSections;
  console.log("Element de obj section" );
  console.log(this.sections);
  }

   /**
  * Method that save in the Backend server,
  *  a new customer data retreived from the form
  * @param addCustomerForm
  */




   createStudent() : void {
    this.userService.saveUser(this.model,1)
      .subscribe( data => {
        console.log(data);
        alert("User created successfully.");
      });
      
          this.submitted = true; 
        
      }  
  
    // TODO: Remove this when we're done
    get diagnostic() { return JSON.stringify(this.model); }

}



