package org.elcata98.mybooks.booksservice.exception;

import org.elcata98.mybooks.booksservice.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URISyntaxException;
import java.util.Optional;

@ControllerAdvice
public class BookServiceResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<String>> handleIllegalArgumentException(IllegalArgumentException ex) {

        return
                new ResponseEntity<>(
                        new Response<String>().addError(Optional.ofNullable(ex.getMessage()).orElse("Generic Error")),
                        null,
                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<Response<String>> handleURISyntaxException(URISyntaxException ex) {

        return
                new ResponseEntity<>(
                        new Response<String>().addError(Optional.ofNullable(ex.getMessage()).orElse("Error while creating URI")),
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
