import { Component, OnInit } from '@angular/core';
import {Criteria} from "../classes/criteria";
import {ActivatedRoute, Router} from "@angular/router";
import {CriteriaService} from "../_services/criteria.service";

@Component({
  selector: 'app-update-criteria',
  templateUrl: './update-criteria.component.html',
  styleUrls: ['./update-criteria.component.scss']
})
export class UpdateCriteriaComponent implements OnInit {
  id: number;
  criteria: any;
  submitted: any;

  constructor(private route: ActivatedRoute,private router: Router, private service: CriteriaService) { }

  ngOnInit(): void {
    this.criteria = new Criteria();
    this.id = this.route.snapshot.params['id'];
    this.service.getCriteriaById(this.id)
      .subscribe(data => {
        console.log(data)
        this.criteria = data;
        (document.getElementById('type') as HTMLInputElement).value=this.criteria.type;
        if(this.criteria.type  == "Statique") {
          (document.getElementById("url") as HTMLInputElement).disabled = false;
        }else {
          (document.getElementById("url") as HTMLInputElement).disabled = true;
        }
      }, error => console.log(error));
  }

  changeCriteriaType(type: string) {
    this.criteria.type = type;
  }


  onSubmit() {
    this.submitted = true;
    this.criteria.name= (document.getElementById("name") as HTMLInputElement).value;
    this.criteria.type =(document.getElementById("type") as HTMLInputElement).value;
    this.criteria.url=(document.getElementById("url") as HTMLInputElement).value;
    this.service.updateCriteria(this.id,this.criteria).subscribe(
      data=> console.log(data),
      error => console.log(error)
    );
  }

  list() {
    this.router.navigate(['Criteria']).then(r => console.log("Error update") );
  }

  verifyInput(url: string) {
    if((document.getElementById("type") as HTMLInputElement).value == "Statique") {
      (document.getElementById(url) as HTMLInputElement).disabled = false;
    }else {
      (document.getElementById(url) as HTMLInputElement).disabled = true;
    }
  }
}
