import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  book: Book = {
    id: 'Book Id',
    
    title: 'Book Title',
    author: 'Book Author',
    language: 'Book Language'    
  }

  constructor() { }

  ngOnInit(): void {
  }

}
