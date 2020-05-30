package org.elcata98.mybooks.booksservice.response;

import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface ResponseEntityBuilder<T> {

    ResponseEntity<Response<T>> buildCreateResponseEntity(T instance, HttpServletRequest httpServletRequest) throws URISyntaxException;
    ResponseEntity<Response<T>> buildUpdateResponseEntity(T instance);
    ResponseEntity<Response<T>> buildDeleteResponseEntity(boolean success);
    ResponseEntity<Response<T>> buildGetResponseEntity(T instance);
}
