package org.elcata98.mybooks.booksservice.validator;

import org.elcata98.mybooks.booksservice.model.IdEntity;
import org.springframework.util.Assert;


public abstract class EntityValidator<T extends IdEntity> {

    public static final String CREATE_ERROR_MSG = "Cannot create entity using providing an id";
    public static final String UPDATE_ERROR_MSG = "Cannot change id";

    public T validateCreate(final T entity) {

        Assert.isNull(entity.getId(), CREATE_ERROR_MSG);
        return entity;
    }

    public T validateUpdate(final String id, final T entity) {

        Assert.isTrue(id.equals(entity.getId()), UPDATE_ERROR_MSG);
        return entity;
    }
}
