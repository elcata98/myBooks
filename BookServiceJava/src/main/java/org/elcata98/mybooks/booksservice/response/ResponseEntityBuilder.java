package org.elcata98.mybooks.booksservice.response;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


public abstract class ResponseEntityBuilder<T extends IdEntity> {

    public ResponseEntity<Response<T>> buildCreateResponseEntity(final T instance, final HttpServletRequest httpServletRequest) throws URISyntaxException {

        return ResponseEntity.created(new URI(httpServletRequest.getRequestURI() + "/" + instance.getId())).body(new Response<>(instance));
    }

    public ResponseEntity<Response<T>> buildUpdateResponseEntity(final T instance) {

        return buildInstanceBasedResponseEntity(instance);
    }

    public ResponseEntity<Response<T>> buildDeleteResponseEntity() {

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Response<T>> buildGetResponseEntity(final T instance) {

        return buildInstanceBasedResponseEntity(instance);
    }

    public ResponseEntity<Response<List<T>>> buildListResponse(final List<T> instances) {
        if (instances == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new Response<>(instances));
        }
    }

    private ResponseEntity<Response<T>> buildInstanceBasedResponseEntity(final T instance) {

        if (instance == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new Response<>(instance));
        }
    }
}