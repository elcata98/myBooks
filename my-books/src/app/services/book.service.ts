import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Book } from '../model/book';

import { BOOKS } from '../model/mock-books';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }

  getBooks(): Observable<Book[]> {
    return of(BOOKS);
  }

  getBook(id: string): Observable<Book> {
    return of(BOOKS.find(book => book.id === id));
  }
}
