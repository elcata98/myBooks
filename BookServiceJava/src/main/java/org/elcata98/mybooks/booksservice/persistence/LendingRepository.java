package org.elcata98.mybooks.booksservice.persistence;

import org.elcata98.mybooks.booksservice.model.Lending;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingRepository extends EntityRepository<Lending> {
}
