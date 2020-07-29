package org.elcata98.mybooks.booksservice.controller;


import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.response.ResponseEntityBuilder;
import org.elcata98.mybooks.booksservice.service.EntityService;
import org.elcata98.mybooks.booksservice.validator.EntityValidator;
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
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final EntityService<Book> entityService;
    private final ResponseEntityBuilder<Book> responseEntityBuilder;
    private final EntityValidator<Book> entityValidator;

    public BookController(final EntityService<Book> entityService, final ResponseEntityBuilder<Book> responseEntityBuilder,
                          final EntityValidator<Book> entityValidator) {
        this.entityService = entityService;
        this.responseEntityBuilder = responseEntityBuilder;
        this.entityValidator = entityValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> create(@RequestBody @Valid Book book, HttpServletRequest httpServletRequest) throws URISyntaxException {

        return responseEntityBuilder.buildCreateResponseEntity(entityService.create(entityValidator.validateCreate(book)), httpServletRequest);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> get(@PathVariable String id) {

        return responseEntityBuilder.buildGetResponseEntity(entityService.get(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Book>> update(@PathVariable String id, @RequestBody @Valid Book book) {

        return responseEntityBuilder.buildUpdateResponseEntity(entityService.update(entityValidator.validateUpdate(id, book)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<Book>> delete(@PathVariable String id) {

        entityService.delete(id);
        return responseEntityBuilder.buildDeleteResponseEntity();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Book>>> findAll() {

        return responseEntityBuilder.buildListResponse(entityService.findAll());
    }
}
