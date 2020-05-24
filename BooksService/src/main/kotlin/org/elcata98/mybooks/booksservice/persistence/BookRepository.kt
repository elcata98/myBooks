package org.elcata98.mybooks.booksservice.persistence

import org.elcata98.mybooks.booksservice.model.Book
import org.springframework.data.repository.Repository
import java.util.*

interface BookRepository : Repository<Book, UUID> {

    fun save(book: Book): Book
    fun findByBookId(bookId: UUID): Book
    fun delete(book: Book)
}