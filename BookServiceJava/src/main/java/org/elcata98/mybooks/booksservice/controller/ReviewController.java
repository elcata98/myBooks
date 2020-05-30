package org.elcata98.mybooks.booksservice.controller;


import org.elcata98.mybooks.booksservice.model.Review;
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

@RestController
@RequestMapping(path = "/reviews")
public class ReviewController {

    private final EntityService<Review> entityService;
    private final ResponseEntityBuilder<Review> responseEntityBuilder;
    private final EntityValidator<Review> entityValidator;


    public ReviewController(final EntityService<Review> entityService, final ResponseEntityBuilder<Review> responseEntityBuilder,
                            final EntityValidator<Review> entityValidator) {
        this.entityService = entityService;
        this.responseEntityBuilder = responseEntityBuilder;
        this.entityValidator = entityValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Review>> create(@RequestBody @Valid Review review, HttpServletRequest httpServletRequest) throws URISyntaxException {

        return responseEntityBuilder.buildCreateResponseEntity(entityService.create(entityValidator.validateCreate(review)), httpServletRequest);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Review>> get(@PathVariable String id) {

        return responseEntityBuilder.buildGetResponseEntity(entityService.get(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Review>> update(@PathVariable String id, @RequestBody @Valid Review review) {

        return responseEntityBuilder.buildUpdateResponseEntity(entityService.update(entityValidator.validateUpdate(id, review)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<Review>> delete(@PathVariable String id) {

        return responseEntityBuilder.buildDeleteResponseEntity(entityService.delete(id));
    }
}
