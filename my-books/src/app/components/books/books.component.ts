import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book.service';

import { Book } from '../../model/book';

import { BOOKS } from '../../model/mock-books';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: Book[];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void {
    this.bookService.getBooks()
      .subscribe(books => this.books = books);
  }
}
