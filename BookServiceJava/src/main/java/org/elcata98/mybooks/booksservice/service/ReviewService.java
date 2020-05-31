package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.model.Review;
import org.elcata98.mybooks.booksservice.model.User;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ReviewService extends EntityService<Review> {

    private final EntityRepository<Book> bookEntityRepository;
    private final EntityRepository<User> userEntityRepository;

    public ReviewService(EntityRepository<Review> entityRepository, EntityRepository<Book> bookEntityRepository,
                         EntityRepository<User> userEntityRepository) {
        super(entityRepository);
        this.bookEntityRepository = bookEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Review create(final Review entity) {
        return super.create(preProcessReview(entity));
    }

    @Override
    public Review update(final Review entity) {
        return super.update(preProcessReview(entity));
    }

    private Review preProcessReview(final Review review) {

        Book book = bookEntityRepository.findById(review.getBook().getId());
        Assert.notNull(book, "Invalid book");

        User user = userEntityRepository.findById(review.getWho().getId());
        Assert.notNull(user, "Invalid user");

        review.setBook(book);
        review.setWho(user);

        return review;
    }
}
