package org.elcata98.mybooks.booksservice.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcata98.mybooks.booksservice.model.User;
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
public class UserControllerMvcTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private User user;

    private final TypeReference<Response<User>> responseTypeReference = new TypeReference<>() {};

    @BeforeEach
    void setUp() {

        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        user = new User();
        user.setUserName("UserName - " + now);
    }

    @Test
    void testCreate() throws Exception {

        createUser();
    }

    @Test
    void testCreateNoMandatoryFields() throws Exception {

        user = new User();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/users")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateWithId() throws Exception {

        user.generateId();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/users")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(mvcResponse ->
                        assertTrue(mvcResponse.getResponse().getContentAsString().contains(EntityValidator.CREATE_ERROR_MSG))
                );
    }

    @Test
    void testGet() throws Exception {

        user = createUser();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/" + user.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                user,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testGetNotFound() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/not_exists")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdate() throws Exception {

        user = createUser();
        user.setRelationship("U know");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/users/" + user.getId())
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mvcResult -> assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()))
                .andDo(mvcResult ->
                        assertEquals(
                                user,
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                        )
                );
    }

    @Test
    void testUpdateMismatch() throws Exception {

        user = createUser();
        user.setRelationship("U know");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/users/mismatch")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {

        user = createUser();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/users/" + user.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNotFound() throws Exception {

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/users/error"))
                .andExpect(status().isNoContent());
    }

    private User createUser() throws Exception {

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
                                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), responseTypeReference).getResponse()
                );

        return users[0];
    }
}
