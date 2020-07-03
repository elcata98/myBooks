import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Book } from '../model/book';
import { Response } from '../model/response';


// import { BOOKS } from '../model/mock-books';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksUrl = 'http://localhost:8080/booksService/books';

  private emptyListResponse: Response<Book[]> = {
    response: []
  }

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Response<Book[]>> {
    return this.http.get<Response<Book[]>>(this.booksUrl)
      .pipe(
        catchError(this.handleError<Response<Book[]>>('getBooks', this.emptyListResponse))
      );
  }

  getBook(id: string): Observable<Response<Book>> {
    return this.http.get<Response<Book>>(this.booksUrl + "/" + id)
      .pipe(
        catchError(this.handleError<Response<Book>>('getBook', null))
      );
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error("AAA" + error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
