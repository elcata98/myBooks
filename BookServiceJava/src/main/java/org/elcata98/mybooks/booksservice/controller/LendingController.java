package org.elcata98.mybooks.booksservice.controller;


import org.elcata98.mybooks.booksservice.model.Lending;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.response.ResponseEntityBuilder;
import org.elcata98.mybooks.booksservice.service.EntityService;
import org.elcata98.mybooks.booksservice.validator.EntityValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/lendings")
public class LendingController {

    private final EntityService<Lending> entityService;
    private final ResponseEntityBuilder<Lending> responseEntityBuilder;
    private final EntityValidator<Lending> entityValidator;


    public LendingController(final EntityService<Lending> entityService, final ResponseEntityBuilder<Lending> responseEntityBuilder,
                             final EntityValidator<Lending> entityValidator) {
        this.entityService = entityService;
        this.responseEntityBuilder = responseEntityBuilder;
        this.entityValidator = entityValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Lending>> create(@RequestBody @Valid Lending lending, HttpServletRequest httpServletRequest) throws URISyntaxException {

        return responseEntityBuilder.buildCreateResponseEntity(entityService.create(entityValidator.validateCreate(lending)), httpServletRequest);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Lending>> get(@PathVariable String id) {

        return responseEntityBuilder.buildGetResponseEntity(entityService.get(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Lending>> update(@PathVariable String id, @RequestBody @Valid Lending lending) {

        return responseEntityBuilder.buildUpdateResponseEntity(entityService.update(entityValidator.validateUpdate(id, lending)));
    }
}
