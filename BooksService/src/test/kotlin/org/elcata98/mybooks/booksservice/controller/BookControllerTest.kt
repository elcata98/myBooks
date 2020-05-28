//package org.elcata98.mybooks.booksservice.controller
//
//import org.elcata98.mybooks.booksservice.model.Book
//import org.elcata98.mybooks.booksservice.service.BookService
//import org.junit.jupiter.api.Assertions.assertNotNull
//import org.junit.jupiter.api.Assertions.assertNull
//import org.junit.jupiter.api.Assertions.assertSame
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.ArgumentMatchers
//import org.mockito.ArgumentMatchers.*
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.any
//import org.mockito.Mockito.anyString
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.http.HttpStatus
//import javax.servlet.http.HttpServletRequest
//
//
//@ExtendWith(MockitoExtension::class)
//class BookControllerTest {
//
//    @InjectMocks
//    private val bookController: BookController = BookController()
//
//    @Mock
//    private val bookService: BookService? = null
//
//    @Mock
//    private val httpRequest: HttpServletRequest? = null
//
//    private val book: Book = Book()
//
//    @BeforeEach
//    fun setUp() {
//
//        book.title = "Title"
//        book.author = "Author"
//        book.language = "Catalan"
//    }
//
//    @Test
//    fun testCreate() {
//
//        `when`(httpRequest!!.requestURI).thenReturn("/")
//        `when`(bookService!!.create(book)).thenReturn(book)
//
//        val responseEntity = bookController.create(book, httpRequest)
//
//        assertNotNull(responseEntity)
//        assertSame(HttpStatus.CREATED, responseEntity.statusCode)
//        assertNotNull(responseEntity.body)
//    }
//
//    @Test
//    fun testGet() {
//
//        `when`(bookService!!.get(ArgumentMatchers.anyString())).thenReturn(book)
//
//        val responseEntity = bookController.get("")
//
//        assertNotNull(responseEntity)
//        assertSame(HttpStatus.OK, responseEntity.statusCode)
//        assertNotNull(responseEntity.body)
//    }
//
//    @Test
//    fun testUpdate() {
//
//        `when`(bookService!!.update(book)).thenReturn(book)
//
//        val responseEntity = bookController.update("", book)
//
//        assertNotNull(responseEntity)
//        assertSame(HttpStatus.OK, responseEntity.statusCode)
//        assertNotNull(responseEntity.body)
//    }
//
//    @Test
//    fun testDelete() {
//
//        `when`(bookService!!.delete(ArgumentMatchers.anyString())).thenReturn(true)
//
//        val responseEntity = bookController.delete("")
//
//        assertNotNull(responseEntity)
//        assertSame(HttpStatus.NO_CONTENT, responseEntity.statusCode)
//        assertNull(responseEntity.body)
//    }
//}