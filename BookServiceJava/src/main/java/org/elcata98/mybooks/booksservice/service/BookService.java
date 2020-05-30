package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends EntityService<Book> {

    public BookService(EntityRepository<Book> entityRepository) {
        super(entityRepository);
    }
}
