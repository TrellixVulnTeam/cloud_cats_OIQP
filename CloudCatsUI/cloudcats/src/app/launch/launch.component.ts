import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-launch',
  templateUrl: './launch.component.html',
  styleUrls: ['./launch.component.css']
})
export class LaunchComponent implements OnInit {
  public pageTitle = 'Cloud Cats: We Hear You Cloud & Clear';
  constructor() { }

  ngOnInit(): void {
  }

}
