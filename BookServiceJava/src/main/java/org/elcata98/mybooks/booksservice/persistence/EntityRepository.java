package org.elcata98.mybooks.booksservice.persistence;


import org.elcata98.mybooks.booksservice.model.Entity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T extends Entity> extends Repository<T, String> {

    T save(final T entity);
    T findById(final String id);
    void deleteById(final String id);
    List<T> findAll();
}
