package org.elcata98.mybooks.booksservice.persistence;

import org.elcata98.mybooks.booksservice.model.Entity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public final class RepositoryResolver {

    private final List<EntityRepository<? extends Entity>> repositories;

    public RepositoryResolver(List<EntityRepository<? extends Entity>> repositories) {
        this.repositories = Collections.unmodifiableList(repositories);
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityRepository<T> resolveRepository(Class<T> clazz) {

        return (EntityRepository<T>) repositories.stream().filter(repository -> repository.willAccept(clazz)).findAny().orElseThrow();
    }

}
