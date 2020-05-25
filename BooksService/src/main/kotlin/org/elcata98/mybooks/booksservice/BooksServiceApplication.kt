package org.elcata98.mybooks.booksservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooksServiceApplication

fun main(args: Array<String>) {
    runApplication<BooksServiceApplication>(*args)
}
