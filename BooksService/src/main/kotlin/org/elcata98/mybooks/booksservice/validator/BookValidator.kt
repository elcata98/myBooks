package org.elcata98.mybooks.booksservice.validator

import org.elcata98.mybooks.booksservice.model.Book
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class BookValidator {

    fun validateCreateBook(book: Book): Book {

        Assert.isNull(book.bookId, "Cannot create Book using providing a bookId")

        return book
    }

    fun validateUpdateBook(id: String, book: Book): Book {

        Assert.isTrue(id == book.bookId, "Cannot change bookId")

        return book
    }
}