package org.elcata98.mybooks.booksservice.persistence;

import org.elcata98.mybooks.booksservice.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends EntityRepository<Book> {

    @Override
    default boolean willAccept(Class<?> clazz) {
        return Book.class == clazz;
    }
}
