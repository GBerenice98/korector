import {Component, OnInit} from '@angular/core';
import {SectionService} from '../_services/section.service';
import {Router} from '@angular/router';
import {Section} from '../classes/section';
import {NgForm} from '@angular/forms';


@Component({
  selector: 'app-createSection',
  templateUrl: './createSection.Component.html',
  styleUrls: ['./createSection.component.scss']
})

export class CreateSectionComponent  implements OnInit {
  private section: Section;
  submitted = false;
  result: any;
  year2: any;
  errorYear =false;
  errorName= false;

  constructor(private sectionService: SectionService,
              private router: Router) { }

  ngOnInit(): void {
  }

  gotoList() {
    this.router.navigate(['/section']);
  }

  resetForm(sectionForm: NgForm) {
    sectionForm.resetForm();
  }

  onSubmit(sectionForm: NgForm) {
    this.errorName = false;
    this.errorYear=false;
    if(sectionForm.controls.name.value ==''){
      this.errorName = true;
     // document.getElementById('errorName').classList.remove("hidden");
    }
    else if(sectionForm.controls.year1.value ==''){
      this.errorYear=true;
    }
    else {
      console.log(sectionForm.controls.year1.value);
      console.log(sectionForm.controls.year2.value);
      this.section = new Section(sectionForm.controls.name.value, sectionForm.controls.year1.value, sectionForm.controls.year2.value);
      let resp = this.sectionService.createSection(this.section);
      resp.subscribe((data) => this.result = data);
      console.log('Résultat :', this.section);
      this.submitted = true;

    }


  }

  changeValue() {
    let year2 = document.getElementById("year1")['value'];
    // tslint:disable-next-line:radix
    this.year2 = parseInt(year2)+1;
  }
}
