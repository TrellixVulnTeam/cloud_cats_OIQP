import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, map, tap } from 'rxjs/operators';
import { IList } from "./launch";

@Injectable({
  providedIn: 'root'
})
export class LaunchService {
  private myUrl = 'api/launch/DiversityInfo.json';

  constructor(private http: HttpClient) { }

  getLists(): Observable<IList[]> {
    return this.http.get<IList[]>(this.myUrl)
      .pipe(
        tap(data => console.log('All: ', JSON.stringify(data))),
        catchError(this.handleError)
      );
  }

  
  getList(id: number): Observable<IList | undefined> {
    return this.getLists()
      .pipe(
        map((places: IList[]) => places.find(x => x.ein === id))
      );
  }

  private handleError(err: HttpErrorResponse): Observable<never> {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }

}