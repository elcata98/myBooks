package org.elcata98.mybooks.booksservice.controller;


import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.response.BookServiceResponseEntityBuilder;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.service.EntityService;
import org.elcata98.mybooks.booksservice.validator.BookValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final EntityService entityService;
    private final BookServiceResponseEntityBuilder<Book> bookResponseEntityBuilder;
    private final BookValidator bookValidator;

    public BookController(final EntityService entityService, final BookServiceResponseEntityBuilder<Book> bookResponseEntityBuilder,
                          final BookValidator bookValidator) {
        this.entityService = entityService;
        this.bookResponseEntityBuilder = bookResponseEntityBuilder;
        this.bookValidator = bookValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> create(@RequestBody @Valid Book book, HttpServletRequest httpServletRequest) throws URISyntaxException {

        return bookResponseEntityBuilder.buildCreateResponseEntity(entityService.create(bookValidator.validateCreateBook(book), Book.class), httpServletRequest);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> get(@PathVariable String id) {

        return bookResponseEntityBuilder.buildGetResponseEntity(entityService.get(id, Book.class));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> update(@PathVariable String id, @RequestBody @Valid Book book) {

        return bookResponseEntityBuilder.buildUpdateResponseEntity(entityService.update(bookValidator.validateUpdateBook(id, book), Book.class));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<Book>> delete(@PathVariable String id) {

        return bookResponseEntityBuilder.buildDeleteResponseEntity(entityService.delete(id, Book.class));
    }
}