package org.elcata98.mybooks.booksservice.mvc

import com.fasterxml.jackson.databind.ObjectMapper
import org.elcata98.mybooks.booksservice.model.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class BookControllerMvcTest {

    @Autowired
    private var mockMvc: MockMvc? = null

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun testCreate() {

        val book = Book()
        book.title = "Title"
        book.author = "Author"
        book.language = "Catalan"

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
    }

    @Test
    fun testGet() {

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/a")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isNotEmpty() })
    }

    @Test
    fun testUpdate() {

        val book = Book()
        book.title = "Title"
        book.author = "Author"
        book.language = "Catalan"

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .put("/books/a")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo { mvcResult -> println(mvcResult.response.contentAsString) }
                .andDo { mvcResult -> println(mvcResult.response.headerNames) }
                .andExpect(status().isOk)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isEmpty() })

    }

    @Test
    fun testDelete() {

        mockMvc!!
                .perform(
                        MockMvcRequestBuilders
                                .delete("/books/a")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(ResultMatcher { mvcResult -> mvcResult.response.contentAsString.isEmpty() })
    }
}