package org.elcata98.mybooks.booksservice.validator;

import org.elcata98.mybooks.booksservice.model.Lending;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class LendingEntityValidator extends EntityValidator<Lending> {

    @Override
    public Lending validateUpdate(final String id, final Lending entity) {

        Assert.notNull(entity, "Not Found");

        return entity;
    }
}
