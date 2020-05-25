package org.elcata98.mybooks.booksservice.model

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.UUID

class Book {

    var bookId: String? = null
        private set

    lateinit var title: String
    lateinit var author: String
    lateinit var language: String

    var year: Short? = null
    var isbn: String? = null
    var editorial: String? = null
    var collection: String? = null
    var edition: String? = null
    var numVolumes: Byte? = null
    var volume: Byte = 1
    var location: String? = null
    var summary: String? = null

    fun generateId() {
        if (bookId == null){
            bookId = UUID.randomUUID().toString()
        }
    }

    override fun equals(other: Any?): Boolean {

        return EqualsBuilder.reflectionEquals(this, other, false)
    }

    override fun hashCode(): Int {

        return HashCodeBuilder.reflectionHashCode(this, false)
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
    }
}