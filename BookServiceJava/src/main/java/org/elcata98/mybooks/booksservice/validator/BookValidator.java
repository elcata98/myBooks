package org.elcata98.mybooks.booksservice.validator;

import org.elcata98.mybooks.booksservice.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public final class BookValidator {

    public static final String CREATE_BOOK_ERROR_MSG = "Cannot create Book using providing a bookId";
    public static final String UPDATE_BOOK_ERROR_MSG = "Cannot change bookId";

    public Book validateCreateBook(final Book book) {

        Assert.isNull(book.getId(), CREATE_BOOK_ERROR_MSG);
        return book;
    }

    public Book validateUpdateBook(final String id, final Book book) {

        Assert.isTrue(id.equals(book.getId()), UPDATE_BOOK_ERROR_MSG);
        return book;
    }
}
