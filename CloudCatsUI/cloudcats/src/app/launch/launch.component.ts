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
  
  
  errorMessage = '';
  sub!: Subscription;

  private _listFilter = '';
  get listFilter(): string {
    return this._listFilter;
  }
  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredList = this.performFilter(value);
  }
  places: IList[] = [];
  filteredList: IList[] = [];

  constructor(private launchService: LaunchService) { }

  performFilter(filterBy: string): IList[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.places.filter((place: IList) =>
      place.businessName.toLocaleLowerCase().includes(filterBy));
  }

  ngOnInit(): void {
    this.sub = this.launchService.getLists().subscribe({
      next: places => {
        this.places = places;
        this.filteredList = this.places;
      },
      error: err => this.errorMessage = err
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
