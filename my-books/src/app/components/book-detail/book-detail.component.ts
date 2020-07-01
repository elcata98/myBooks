import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { BookService } from '../../services/book.service';

import { Book } from '../../model/book';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  @Input() book: Book;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.loadBook();
  }

  loadBook(): void {
    const id = this.route.snapshot.paramMap.get('id');

    this.bookService.getBook(id)
      .subscribe(book => this.book = book);
  }

  goBack(): void {
    this.location.back();
  }
}
