package org.elcata98.mybooks.booksservice.service

import org.elcata98.mybooks.booksservice.model.Book
import org.elcata98.mybooks.booksservice.persistence.BookRepository
import org.elcata98.mybooks.booksservice.validator.BookValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert

@Service
class BookService {

    @Autowired
    final lateinit var bookRepository: BookRepository

    fun create(book: Book): Book {

        book.generateId()

        return bookRepository.save(book)
    }

    fun update(book: Book): Book? {

        var existingBook: Book? = bookRepository.findByBookId(book.bookId)

        if (existingBook != null) {
            existingBook = bookRepository.save(book)
        }
        return existingBook
    }

    fun delete(id: String): Boolean {

        var success = false
        val existingBook: Book? = bookRepository.findByBookId(id)

        if (existingBook != null) {
            bookRepository.deleteByBookId(existingBook.bookId)
            success = true
        }
        return success
    }

    fun get(id: String): Book? {

        return bookRepository.findByBookId(id)
    }
}