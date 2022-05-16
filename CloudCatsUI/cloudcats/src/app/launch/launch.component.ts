import { Component, OnDestroy, OnInit } from '@angular/core';
import { BusinessDiversityInfo, BusinessInfo } from './launch';
import { LaunchService } from './launch.http.component';
import { Subscription } from 'rxjs';
import { ViewChild, HostListener, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable, NgModule} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatFormField } from '@angular/material/form-field';
import { FormControl } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import * as XLSX from 'xlsx';


@Component({
  selector: 'app-launch',
  templateUrl: './launch.component.html',
  styleUrls: ['./launch.component.css']
})
export class LaunchComponent implements OnInit, AfterViewInit, OnDestroy {
 
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild('empTbSortWithObject') empTbSortWithObject = new MatSort();
 




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

  spinnerDisplay : boolean = true;
  businessInfo: BusinessInfo[] = [];
  filteredList: BusinessInfo[] = [];

  displayedColumns: string[] =["businessName", "businessState", "businessFEIN","businessOwners", "minorityOwned", "womenOwned", "smallBusiness"];
  dataSource = new MatTableDataSource<BusinessInfo>(this.filteredList);

  constructor(private launchService: LaunchService) {}

  performFilter(filterBy: string): BusinessInfo[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.businessInfo.filter((info: BusinessInfo) =>
      info.businessName.toLocaleLowerCase().includes(filterBy));
  }

  


  ngOnInit(): void {
    // this.sub = this.launchService.getLists().subscribe({
    //   next: places => {
    //     this.places = places;
    //     this.filteredList = this.places;
    //   },
    //   error: err => this.errorMessage = err
    // });
      this.dataSource.paginator = this.paginator;
      this.sub = this.launchService.getInfo().subscribe({
      next: places => {
        this.businessInfo = places.businessInfoList;
        this.filteredList = this.businessInfo;
        this.spinnerDisplay = false;
        this.dataSource.data = this.filteredList;  
      },
      error: err => this.errorMessage = err
    });
                                                                                                                                                                                                                                                                                                                                                                                                                                  
  }

  ngAfterViewInit(){
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.empTbSortWithObject;
  }
  
  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }



}
