import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';

import { BOOKS } from '../../model/mock-books';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books = BOOKS;

  selectedBook: Book;

  constructor() { }

  ngOnInit(): void {
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

}
