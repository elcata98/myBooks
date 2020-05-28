package org.elcata98.mybooks.booksservice.response

import org.elcata98.mybooks.booksservice.model.Book
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.net.URI
import javax.servlet.http.HttpServletRequest


@Component
class BookResponseEntityBuilder : ResponseEntityBuilder<Book> {

    override fun buildCreateResponseEntity(instance: Book, httpServletRequest: HttpServletRequest): ResponseEntity<Response<Book>> {

        return ResponseEntity.created(URI(httpServletRequest.requestURI + "/" + instance.bookId)).body(Response(instance))
    }

    override fun buildUpdateResponseEntity(instance: Book?): ResponseEntity<Response<Book>> {

        return buildInstanceBasedResponseEntity(instance)
    }

    override fun buildDeleteResponseEntity(success: Boolean): ResponseEntity<Response<Book>> {

        return if (success) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    override fun buildGetResponseEntity(instance: Book?): ResponseEntity<Response<Book>> {

        return buildInstanceBasedResponseEntity(instance)
    }

    private fun buildInstanceBasedResponseEntity(instance: Book?): ResponseEntity<Response<Book>> {

        return if (instance == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(Response(instance))
        }
    }
}