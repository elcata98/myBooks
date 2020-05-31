package org.elcata98.mybooks.booksservice;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@ContextConfiguration
public class BookServiceApplicationTest {

    @Test
    void contextLoads() {

    }
}
