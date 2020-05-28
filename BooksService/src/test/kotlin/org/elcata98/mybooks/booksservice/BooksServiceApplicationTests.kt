package org.elcata98.mybooks.booksservice

import org.elcata98.mybooks.booksservice.persistence.BookRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BooksServiceApplicationTests {

    @Autowired
    var bookRepository: BookRepository? = null

    @Test
    fun testBookRepository() {
        assertNotNull(bookRepository)

//		val book = Book()
//		book.title = "Title"
//		book.author = "Author"
//		book.language = "Catalan"
//
//		val saved = bookRepository!!.save(book)
//		val found = bookRepository!!.findByBookId(saved.bookId)
//
//		assertEquals(saved, found)
    }
}
