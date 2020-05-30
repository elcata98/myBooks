package org.elcata98.mybooks.booksservice.controller;


import org.elcata98.mybooks.booksservice.model.User;
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
@RequestMapping(path = "/users")
public class UserController {

    private final EntityService<User> entityService;
    private final ResponseEntityBuilder<User> userResponseEntityBuilder;
    private final EntityValidator<User> userEntityValidator;


    public UserController(final EntityService<User> entityService, final ResponseEntityBuilder<User> userResponseEntityBuilder,
                          final EntityValidator<User> userEntityValidator) {
        this.entityService = entityService;
        this.userResponseEntityBuilder = userResponseEntityBuilder;
        this.userEntityValidator = userEntityValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<User>> create(@RequestBody @Valid User user, HttpServletRequest httpServletRequest) throws URISyntaxException {

        return userResponseEntityBuilder.buildCreateResponseEntity(entityService.create(userEntityValidator.validateCreate(user)), httpServletRequest);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<User>> get(@PathVariable String id) {

        return userResponseEntityBuilder.buildGetResponseEntity(entityService.get(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<User>> update(@PathVariable String id, @RequestBody @Valid User user) {

        return userResponseEntityBuilder.buildUpdateResponseEntity(entityService.update(userEntityValidator.validateUpdate(id, user)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<User>> delete(@PathVariable String id) {

        return userResponseEntityBuilder.buildDeleteResponseEntity(entityService.delete(id));
    }
}