package org.elcata98.mybooks.booksservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.model.Review;
import org.elcata98.mybooks.booksservice.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDateJsonSerializerTest {

    @Test
    void testSerialize() throws JsonProcessingException {

        String toParse = LocalDateJsonSerializer.DATE_FORMAT.format(LocalDate.now());

        Review review = new Review();
        review.setWho(new User());
        review.setBook(new Book());
        review.setWhen(LocalDate.now());

        assertTrue(new ObjectMapper().writeValueAsString(review).contains(toParse));
    }
}