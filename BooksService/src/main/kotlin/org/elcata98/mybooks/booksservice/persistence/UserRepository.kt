package org.elcata98.mybooks.booksservice.persistence

import org.elcata98.mybooks.booksservice.model.User
import org.springframework.data.repository.Repository
import java.util.UUID

interface UserRepository : Repository<User, UUID> {

    fun save(user: User): User
    fun findByUserId(userId: UUID): User
    fun delete(user: User)
}