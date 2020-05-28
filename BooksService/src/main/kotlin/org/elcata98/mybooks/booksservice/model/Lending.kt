package org.elcata98.mybooks.booksservice.model

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.Date
import java.util.UUID

class Lending {
    var lendingId: UUID = UUID.randomUUID()
        private set

    lateinit var who: User
    lateinit var book: Book
    lateinit var startDate: Date
    var endDate: Date? = null

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