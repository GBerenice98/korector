import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-section-detail',
  templateUrl: './section-details.component.html',
  styleUrls: ['./section-details.component.scss']
})
export class SectionDetailComponent implements OnInit {

  sectionName: string;

  constructor(private actRoute: ActivatedRoute) {
    this.sectionName = this.actRoute.snapshot.params.name;
  }

  ngOnInit(): void {
  }

}
