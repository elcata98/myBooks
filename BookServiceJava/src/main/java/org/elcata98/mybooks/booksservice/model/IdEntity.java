package org.elcata98.mybooks.booksservice.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public abstract class IdEntity implements Entity {

    @Id
    private String id;

    public final void generateId() {

        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }
}
