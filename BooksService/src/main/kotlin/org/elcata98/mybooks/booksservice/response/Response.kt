package org.elcata98.mybooks.booksservice.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Response<T> () {

    val errors: MutableList<String> = mutableListOf()

    var response: T? = null
        private set

    constructor(response: T) : this() {
        this.response = response
    }

    fun addError(error: String): Response<T> {
        errors.add(error)
        return this
    }
}