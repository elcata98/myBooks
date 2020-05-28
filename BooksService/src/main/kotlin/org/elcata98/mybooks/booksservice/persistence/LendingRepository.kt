package org.elcata98.mybooks.booksservice.persistence

import org.elcata98.mybooks.booksservice.model.Lending
import org.springframework.data.repository.Repository
import java.util.UUID

interface LendingRepository : Repository<Lending, UUID> {

    fun save(lending: Lending): Lending
    fun findByLendingId(lendingId: UUID): Lending
    fun delete(lending: Lending)
}