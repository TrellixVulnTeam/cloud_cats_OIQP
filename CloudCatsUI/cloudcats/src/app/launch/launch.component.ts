import { Component, OnDestroy, OnInit } from '@angular/core';
import { IList } from './launch';
import { LaunchService } from './launch.http.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-launch',
  templateUrl: './launch.component.html',
  styleUrls: ['./launch.component.css']
})
export class LaunchComponent implements OnInit, OnDestroy {
  
  places: IList[] = [];
  errorMessage = '';
  sub!: Subscription;
  constructor(private launchService: LaunchService) { }

  ngOnInit(): void {
    this.sub = this.launchService.getLists().subscribe({
      next: places => {
        this.places = places;
      },
      error: err => this.errorMessage = err
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
