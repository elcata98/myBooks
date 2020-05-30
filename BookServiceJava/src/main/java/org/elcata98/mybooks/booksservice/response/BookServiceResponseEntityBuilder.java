package org.elcata98.mybooks.booksservice.response;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;


@Component
public class BookServiceResponseEntityBuilder<T extends IdEntity> implements ResponseEntityBuilder<T> {

    @Override
    public ResponseEntity<Response<T>> buildCreateResponseEntity(final T instance, final HttpServletRequest httpServletRequest) throws URISyntaxException {

        return ResponseEntity.created(new URI(httpServletRequest.getRequestURI() + "/" + instance.getId())).body(new Response<>(instance));
    }

    @Override
    public ResponseEntity<Response<T>> buildUpdateResponseEntity(final T instance) {

        return buildInstanceBasedResponseEntity(instance);
    }

    @Override
    public ResponseEntity<Response<T>> buildDeleteResponseEntity(final boolean success) {

        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Response<T>> buildGetResponseEntity(final T instance) {

        return buildInstanceBasedResponseEntity(instance);
    }


    private ResponseEntity<Response<T>> buildInstanceBasedResponseEntity(final T instance) {

        if (instance == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new Response<>(instance));
        }
    }
}