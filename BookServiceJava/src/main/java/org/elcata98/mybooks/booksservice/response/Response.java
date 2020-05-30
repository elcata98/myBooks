package org.elcata98.mybooks.booksservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> errors = new ArrayList<>();

    private final T response;

    public Response() {
//        for deserialization
        response = null;
    }

    public Response(final T response) {
        this.response = response;
    }

    public Response<T> addError(final String error) {
        errors.add(error);
        return this;
    }

    public T getResponse() {
        return response;
    }

    public List<String> getErrors() {
        //        for serialization
        return Collections.unmodifiableList(errors);
    }
}
