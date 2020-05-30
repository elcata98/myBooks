package org.elcata98.mybooks.booksservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.model.Review;
import org.elcata98.mybooks.booksservice.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateJsonDeserializerTest {

    @Test
    void testDeserialize() throws JsonProcessingException {

        LocalDate now = LocalDate.now();

        Review review = new Review();
        review.setWho(new User());
        review.setBook(new Book());
        review.setWhen(now);

        ObjectMapper objectMapper = new ObjectMapper();

        String serialized = objectMapper.writeValueAsString(review);

        assertEquals(review, objectMapper.readValue(serialized, Review.class));
    }
}