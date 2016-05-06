package com.devexperts.service;

import java.util.Optional;

/**
 * An example of a CRUD service.
 *
 * @author ifedorenkov
 */
public interface CrudService<T, ID> {

    /**
     * Finds all the entities.
     */
    Iterable<T> findAll();

    /**
     * Finds a single entity by the given identifier.
     */
    Optional<T> findOne(ID id);

    /**
     * Saves the entity.
     */
    T save(T entity);

    /**
     * Removes the entity.
     */
    void remove(T entity);

}
