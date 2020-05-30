package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.User;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends EntityService<User> {

    public UserService(EntityRepository<User> entityRepository) {
        super(entityRepository);
    }
}
