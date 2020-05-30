package org.elcata98.mybooks.booksservice.persistence;


import org.elcata98.mybooks.booksservice.model.Entity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends Entity> extends EntityAcceptRepository<T> {

    T save(final T entity);
}
