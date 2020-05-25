package org.elcata98.mybooks.booksservice.controller

import org.elcata98.mybooks.booksservice.model.Book
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
import java.net.URI
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/books")
class BookController {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody book: Book, httpRequest: HttpServletRequest): ResponseEntity<Book> {

//        TODO: implement
        book.generateId()

        val createdURI = httpRequest.requestURI + "/" + book.bookId

        return ResponseEntity.created(URI(createdURI)).body(book)
    }

    @GetMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: String): ResponseEntity<Book> {

//        TODO: implement
        val book = Book()
        book.title = "Title"
        book.author = "Author"
        book.language = "Catalan"
        book.generateId()

        return ResponseEntity.ok(book)
    }

    @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: String, @RequestBody book: Book): ResponseEntity<Book> {

//        TODO: implement

        return ResponseEntity.ok(book)
    }

    @DeleteMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable id: String): ResponseEntity<Book> {

//        TODO: implement

        return ResponseEntity.ok().build()
    }
}