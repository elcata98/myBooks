package org.elcata98.mybooks.booksservice.response

import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

interface ResponseEntityBuilder<T> {

    fun buildCreateResponseEntity(instance: T, httpServletRequest: HttpServletRequest): ResponseEntity<Response<T>>

    fun buildUpdateResponseEntity(instance: T?): ResponseEntity<Response<T>>

    fun buildDeleteResponseEntity(success: Boolean): ResponseEntity<Response<T>>

    fun buildGetResponseEntity(instance: T?): ResponseEntity<Response<T>>
}