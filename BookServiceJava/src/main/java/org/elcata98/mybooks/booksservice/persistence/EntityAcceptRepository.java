package org.elcata98.mybooks.booksservice.persistence;


import org.elcata98.mybooks.booksservice.model.Entity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface EntityAcceptRepository<T extends Entity> extends Repository<T, String> {

    boolean willAccept(Class<?> clazz);
}