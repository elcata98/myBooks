package org.elcata98.mybooks.booksservice.persistence

import org.elcata98.mybooks.booksservice.model.Review
import org.springframework.data.repository.Repository
import java.util.UUID

interface ReviewRepository : Repository<Review, UUID> {

    fun save(review: Review): Review
    fun findByReviewId(reviewId: UUID): Review
    fun delete(review: Review)
}