package org.elcata98.mybooks.booksservice.service;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.elcata98.mybooks.booksservice.persistence.EntityRepository;

import java.util.List;

public abstract class EntityService<T extends IdEntity> {

    private final EntityRepository<T> entityRepository;

    public EntityService(final EntityRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public T create(final T entity) {

        entity.generateId();

        return entityRepository.save(entity);
    }

    public T get(final String id) {

        return entityRepository.findById(id);
    }

    public T update(final T entity) {

        T existingEntity = entityRepository.findById(entity.getId());

        if (existingEntity != null) {
            existingEntity = entityRepository.save(entity);
        }

        return existingEntity;
    }

    public void delete(final String id) {

        if (entityRepository.existsById(id)) {
            entityRepository.deleteById(id);
        }
    }

    public List<T> findAll(){

        return entityRepository.findAll();
    }
}
