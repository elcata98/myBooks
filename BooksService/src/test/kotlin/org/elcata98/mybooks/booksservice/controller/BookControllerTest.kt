package org.elcata98.mybooks.booksservice.controller

import org.elcata98.mybooks.booksservice.model.Book
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest

@ExtendWith(MockitoExtension::class)
class BookControllerTest {

    private val bookController: BookController = BookController()

    @Mock
    private val httpRequest: HttpServletRequest? = null

    @Test
    fun testCreate() {

        `when`(httpRequest!!.requestURI).thenReturn("/")

        val newBook = Book()

        val responseEntity = bookController.create(newBook, httpRequest)

        assertNotNull(responseEntity)
        assertSame(HttpStatus.CREATED, responseEntity.statusCode)
        assertNotNull(responseEntity.body)
    }

    @Test
    fun testGet() {

        val responseEntity = bookController.get("")

        assertNotNull(responseEntity)
        assertSame(HttpStatus.OK, responseEntity.statusCode)
        assertNotNull(responseEntity.body)
    }

    @Test
    fun testUpdate() {

        val book = Book()
        book.title = "Title"
        book.author = "Author"
        book.language = "Catalan"

        val responseEntity = bookController.update("", book)

        assertNotNull(responseEntity)
        assertSame(HttpStatus.OK, responseEntity.statusCode)
        assertNotNull(responseEntity.body)
    }

    @Test
    fun testDelete() {

        val responseEntity = bookController.delete("")

        assertNotNull(responseEntity)
        assertSame(HttpStatus.OK, responseEntity.statusCode)
        assertNull(responseEntity.body)
    }
}