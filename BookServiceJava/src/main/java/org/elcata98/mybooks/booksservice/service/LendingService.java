package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.Book;
import org.elcata98.mybooks.booksservice.model.Lending;
import org.elcata98.mybooks.booksservice.model.User;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
public class LendingService extends EntityService<Lending> {

    private final EntityRepository<Book> bookEntityRepository;
    private final EntityRepository<User> userEntityRepository;

    public LendingService(EntityRepository<Lending> entityRepository, EntityRepository<Book> bookEntityRepository,
                          EntityRepository<User> userEntityRepository) {
        super(entityRepository);
        this.bookEntityRepository = bookEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Lending create(final Lending entity) {

        return super.create(preProcessLending(entity));
    }

    @Override
    public Lending update(final Lending entity) {

        Lending existingLending = get(entity.getId());

        Assert.isTrue(entity.getBook().getId().equals(existingLending.getBook().getId()), "Cannot modify existing book");
        Assert.isTrue(entity.getWho().getId().equals(existingLending.getWho().getId()), "Cannot modify existing user");
        Assert.isTrue(entity.getStartDate().equals(existingLending.getStartDate()), "Cannot modify existing start date");
        Assert.isNull(entity.getEndDate(), "Cannot manually update end date");

        entity.setEndDate(LocalDate.now());

        return super.update(preProcessLending(entity));
    }

    private Lending preProcessLending(final Lending lending) {

        Book book = bookEntityRepository.findById(lending.getBook().getId());
        Assert.notNull(book, "Invalid book");

        User user = userEntityRepository.findById(lending.getWho().getId());
        Assert.notNull(user, "Invalid user");

        lending.setBook(book);
        lending.setWho(user);

        return lending;
    }
}
