package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.Review;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends EntityService<Review> {

    public ReviewService(EntityRepository<Review> entityRepository) {
        super(entityRepository);
    }
}
