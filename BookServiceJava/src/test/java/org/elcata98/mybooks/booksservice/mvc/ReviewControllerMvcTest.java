package org.elcata98.mybooks.booksservice.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.model.Review;
import org.elcata98.mybooks.booksservice.model.User;
import org.elcata98.mybooks.booksservice.response.Response;
import org.elcata98.mybooks.booksservice.validator.EntityValidator;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
public class ReviewControllerMvcTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Review review;

    private final TypeReference<Response<Review>> responseTypeReference = new TypeReference<>() {
    };
    private final TypeReference<Response<Book>> bookResponseTypeReference = new TypeReference<>() {
    };
    private final TypeReference<Response<User>> userResponseTypeReference = new TypeReference<>() {
    };


    @BeforeEach
    void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Book book = createBook();
        User user = createUser();

        review = new Review();
        review.setBook(book);
        review.setWho(user);
    }

    @Test
    void testCreate() throws Exception {

        createReview();
    }

    @Test
    void testCreateNoMandatoryFields() throws Exception {

        review = new Review();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/reviews")
                                .content(objectMapper.writeValueAsString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateWithId() throws Exception {

        review.generateId();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/reviews")
                                .content(objectMapper.writeValueAsString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(mvcResponse ->
                        assertTrue(mvcResponse.getResponse().getContentAsString().contains(EntityValidator.CREATE_ERROR_MSG))
                );
    }

    @Test
    void testGet() throws Exception {

        review = createReview();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/reviews/" + review.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                review,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testGetNotFound() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/reviews/not_exists")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdate() throws Exception {

        review = createReview();
        review.setDummy("Dummy");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/reviews/" + review.getId())
                                .content(objectMapper.writeValueAsString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                review,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testUpdateMismatch() throws Exception {

        review = createReview();
        review.setDummy("Dummy");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/reviews/mismatch")
                                .content(objectMapper.writeValueAsString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {

        review = createReview();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/reviews/" + review.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteError() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/reviews/error"))
                .andExpect(status().is5xxServerError());
    }

    private Review createReview() throws Exception {

        Review[] reviews = new Review[1];

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/reviews")
                                .content(objectMapper.writeValueAsString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        reviews[0] =
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                );

        return reviews[0];
    }

    private Book createBook() throws Exception {

        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        Book book = new Book();
        book.setTitle("Title - " + now);
        book.setAuthor("Author - " + now);
        book.setLanguage("Catalan - " + now);

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
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), bookResponseTypeReference).getResponse()
                );

        return books[0];
    }

    private User createUser() throws Exception {

        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        User user = new User();
        user.setUserName("UserName - " + now);

        User[] users = new User[1];

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/users")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        users[0] =
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), userResponseTypeReference).getResponse()
                );

        return users[0];
    }
}
