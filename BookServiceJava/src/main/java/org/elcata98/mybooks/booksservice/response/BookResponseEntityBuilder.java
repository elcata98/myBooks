package org.elcata98.mybooks.booksservice.response;

import org.elcata98.mybooks.booksservice.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;


@Component
public class BookResponseEntityBuilder implements ResponseEntityBuilder<Book> {

    @Override
    public ResponseEntity<Response<Book>> buildCreateResponseEntity(final Book instance, final HttpServletRequest httpServletRequest) throws URISyntaxException {

        return ResponseEntity.created(new URI(httpServletRequest.getRequestURI() + "/" + instance.getId())).body(new Response<>(instance));
    }

    @Override
    public ResponseEntity<Response<Book>> buildUpdateResponseEntity(final Book instance) {

        return buildInstanceBasedResponseEntity(instance);
    }

    @Override
    public ResponseEntity<Response<Book>> buildDeleteResponseEntity(final boolean success) {

        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Response<Book>> buildGetResponseEntity(final Book instance) {

        return buildInstanceBasedResponseEntity(instance);
    }


    private ResponseEntity<Response<Book>> buildInstanceBasedResponseEntity(final Book instance) {

        if (instance == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new Response<>(instance));
        }
    }
}