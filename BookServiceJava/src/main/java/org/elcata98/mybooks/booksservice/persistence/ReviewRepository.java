package org.elcata98.mybooks.booksservice.persistence;

import org.elcata98.mybooks.booksservice.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends EntityRepository<Review> {
}
