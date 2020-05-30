package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.elcata98.mybooks.booksservice.persistence.RepositoryResolver;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    private final RepositoryResolver repositoryResolver;

    public EntityService(final RepositoryResolver repositoryResolver) {
        this.repositoryResolver = repositoryResolver;
    }

    public <T extends IdEntity> T create(final T entity, final Class<T> clazz) {

        entity.generateId();

        return repositoryResolver.resolveRepository(clazz).save(entity);
    }

    public <T extends IdEntity> T get(final String id, final Class<T> clazz) {

        return repositoryResolver.resolveRepository(clazz).findById(id);
    }

    public <T extends IdEntity> T update(final T entity, final Class<T> clazz) {

        T existingEntity = repositoryResolver.resolveRepository(clazz).findById(entity.getId());

        if (existingEntity != null) {
            existingEntity = repositoryResolver.resolveRepository(clazz).save(entity);
        }

        return existingEntity;
    }

    public <T extends IdEntity> boolean delete(final String id, Class<T> clazz) {

        boolean success = false;

        T existingEntity = repositoryResolver.resolveRepository(clazz).findById(id);

        if (existingEntity != null) {
            repositoryResolver.resolveRepository(clazz).deleteById(id);
            success = true;
        }

        return success;
    }
}
