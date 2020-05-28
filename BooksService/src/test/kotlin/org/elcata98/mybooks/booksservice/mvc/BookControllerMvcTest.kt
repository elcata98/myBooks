package org.elcata98.mybooks.booksservice.mvc

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.elcata98.mybooks.booksservice.model.Book
import org.elcata98.mybooks.booksservice.response.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDateTime
import java.time.ZoneOffset


@SpringBootTest
@ContextConfiguration
class BookControllerMvcTest {

    @Autowired
    private val context: WebApplicationContext? = null

    private var mockMvc: MockMvc? = null

    private val objectMapper: ObjectMapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context!!).build();
    }

    @Test
    fun testCreate() {

        createBook()
    }

    @Test
    fun testCreateWithId() {

        val book = Book()
        val now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        book.title = "Title - $now"
        book.author = "Author - $now"
        book.language = "Catalan - $now"
        book.generateId()

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .post("/books")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun testGet() {

        val book = createBook()

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/" + book.bookId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isNotEmpty() })
                .andDo { mvcResult ->
                    assertEquals(book, objectMapper.readValue(mvcResult.response.contentAsString, object : TypeReference<Response<Book>>() {}).response!!)
                }
    }

    @Test
    fun testGetNotFound() {

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/not_exists")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }

    @Test
    fun testUpdate() {

        val book = createBook()
        book.numVolumes = 10

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .put("/books/" + book.bookId)
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo { mvcResult -> println(mvcResult.response.contentAsString) }
                .andExpect(status().isOk)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isEmpty() })
                .andDo { mvcResult ->
                    assertEquals(book, objectMapper.readValue(mvcResult.response.contentAsString, object : TypeReference<Response<Book>>() {}).response!!)
                }
    }

    @Test
    fun testUpdateIdMismatch() {

        val book = createBook()
        book.numVolumes = 10

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .put("/books/mismatch")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo { mvcResult -> println(mvcResult.response.contentAsString) }
                .andExpect(status().isBadRequest)
    }

    @Test
    fun testDelete() {

        val book = createBook()

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .delete("/books/" + book.bookId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isEmpty() })
    }

    @Test
    fun testDeleteError() {

        val book = createBook()

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .delete("/books/error")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError)
    }

    private fun createBook(): Book {

        var book = Book()
        val now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        book.title = "Title - $now"
        book.author = "Author - $now"
        book.language = "Catalan - $now"

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .post("/books")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andExpect(header().exists("Location"))
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isNotEmpty() })
                .andDo { mvcResult ->
                    book = objectMapper.readValue(mvcResult.response.contentAsString, object : TypeReference<Response<Book>>() {}).response!!
                }

        return book;
    }
}