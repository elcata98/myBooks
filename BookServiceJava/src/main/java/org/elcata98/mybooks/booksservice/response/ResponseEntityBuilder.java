package org.elcata98.mybooks.booksservice.response;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


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

        return
                Optional.ofNullable(instances)
                        .map(Response::new)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity<Response<T>> buildInstanceBasedResponseEntity(final T instance) {

        return
                Optional.ofNullable(instance)
                        .map(Response::new)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
}