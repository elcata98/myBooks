package org.elcata98.mybooks.booksservice.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.validator.BookValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
public class BookControllerMvcTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Book book;

    @BeforeEach
    void setUp() {

        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        book = new Book();
        book.setTitle("Title - " + now);
        book.setAuthor("Author - " + now);
        book.setLanguage("Catalan - " + now);
    }

    @Test
    void testCreate() throws Exception {

        createBook();
    }

    @Test
    void testCreateNoMandatoryFields() throws Exception {

        book = new Book();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/books")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateWithId() throws Exception {

        book.generateId();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/books")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(mvcResponse -> assertTrue(mvcResponse.getResponse().getContentAsString().contains(BookValidator.CREATE_BOOK_ERROR_MSG)));
    }

    private Book createBook() throws Exception {

        Book[] books = new Book[1];

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/books")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        books[0] = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Response<Book>>() {
                        }).getResponse());

        return books[0];
    }
}
