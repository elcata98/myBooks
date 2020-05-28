package org.elcata98.mybooks.booksservice.model

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.UUID

class User {
    var userId: UUID = UUID.randomUUID()
        private set

    var userName: String = ""
    var fullName: String = ""
    var relationship: String = ""

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