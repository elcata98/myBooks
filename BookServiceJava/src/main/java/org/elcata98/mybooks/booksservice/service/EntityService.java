package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;
import org.elcata98.mybooks.booksservice.persistence.RepositoryResolver;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class EntityService {

    private final RepositoryResolver repositoryResolver;

    public EntityService(final RepositoryResolver repositoryResolver) {
        this.repositoryResolver = repositoryResolver;
    }

    public <T extends IdEntity> T create(final T entity) {

        entity.generateId();

        return ((EntityRepository<T>) repositoryResolver.resolveRepository(entity.getClass())).save(entity);
    }
}
