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

        Book book = bookEntityRepository.findById(entity.getBook().getId());
        Assert.notNull(book, "Invalid book");

        User user = userEntityRepository.findById(entity.getWho().getId());
        Assert.notNull(user, "Invalid user");

        entity.setBook(book);
        entity.setWho(user);

        return super.create(entity);
    }

    @Override
    public Lending update(final Lending entity) {

        entity.setEndDate(LocalDate.now());

        return super.update(entity);
    }
}
