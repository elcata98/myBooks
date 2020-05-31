package org.elcata98.mybooks.booksservice.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.validator.EntityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@ContextConfiguration
public class BookControllerMvcTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Book book;

    private final TypeReference<Response<Book>> responseTypeReference = new TypeReference<>() {};

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
                .andDo(mvcResponse ->
                        assertTrue(mvcResponse.getResponse().getContentAsString().contains(EntityValidator.CREATE_ERROR_MSG))
                );
    }

    @Test
    void testGet() throws Exception {

        book = createBook();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/" + book.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                book,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testGetNotFound() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/not_exists")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdate() throws Exception {

        book = createBook();
        book.setNumVolumes((byte) 10);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/books/" + book.getId())
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                book,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testUpdateMismatch() throws Exception {

        book = createBook();
        book.setNumVolumes((byte) 10);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/books/mismatch")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {

        book = createBook();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/books/" + book.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteError() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/books/error"))
                .andExpect(status().is5xxServerError());
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
                        books[0] =
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                );

        return books[0];
    }
}
