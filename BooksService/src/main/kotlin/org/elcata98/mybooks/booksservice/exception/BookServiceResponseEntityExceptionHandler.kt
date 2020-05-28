package org.elcata98.mybooks.booksservice.exception

import org.elcata98.mybooks.booksservice.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Optional

@ControllerAdvice
class BookServiceResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Response<String>> {

        return ResponseEntity(Response<String>().addError(Optional.ofNullable(ex.message).orElse("Generic Error")), null, HttpStatus.BAD_REQUEST)
    }
}