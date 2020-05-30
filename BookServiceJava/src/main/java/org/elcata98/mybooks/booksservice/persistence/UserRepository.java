package org.elcata98.mybooks.booksservice.persistence;

import org.elcata98.mybooks.booksservice.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends EntityRepository<User> {

    @Override
    default boolean willAccept(Class<?> clazz) {
        return User.class == clazz;
    }
}
