package org.elcata98.mybooks.booksservice.controller

import org.elcata98.mybooks.booksservice.model.Book
import org.elcata98.mybooks.booksservice.response.Response
import org.elcata98.mybooks.booksservice.response.ResponseEntityBuilder
import org.elcata98.mybooks.booksservice.service.BookService
import org.elcata98.mybooks.booksservice.validator.BookValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    final lateinit var bookService: BookService

    @Autowired
    final lateinit var bookValidator: BookValidator

    @Autowired
    final lateinit var bookResponseEntityBuilder: ResponseEntityBuilder<Book>

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody book: Book, httpRequest: HttpServletRequest): ResponseEntity<Response<Book>> {

        return bookResponseEntityBuilder.buildCreateResponseEntity(bookService.create(bookValidator.validateCreateBook(book)), httpRequest)
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: String): ResponseEntity<Response<Book>> {

        return bookResponseEntityBuilder.buildGetResponseEntity(bookService.get(id))
    }

    @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: String, @RequestBody book: Book): ResponseEntity<Response<Book>> {

        return bookResponseEntityBuilder.buildUpdateResponseEntity(bookService.update(bookValidator.validateUpdateBook(id, book)))
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable id: String): ResponseEntity<Response<Book>> {

        return bookResponseEntityBuilder.buildDeleteResponseEntity(bookService.delete(id))
    }
}