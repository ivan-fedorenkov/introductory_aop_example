package com.devexperts.service;

import com.devexperts.aop.Transactional;
import com.devexperts.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ifedorenkov
 */
public class PersonService extends CrudServiceBase<Person, Long> {

    private final List<Person> people = new ArrayList<>();

    @Transactional
    @Override
    public Iterable<Person> findAll() {
        if (isDebugEnabled())
            debug("PersonService#findAll");
        try {
            List<Person> result = new ArrayList<>(people);
            if (isDebugEnabled())
                debug("PersonService#findAll=" + result);
            return result;
        } catch (Throwable t) {
            error("PersonService#findAll=failed");
            throw t;
        }
    }

    @Transactional
    @Override
    public Optional<Person> findOne(Long id) {
        if (isDebugEnabled())
            debug("PersonService#findOne[" + id + "]");
        try {
            Optional<Person> result = people.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
            if (isDebugEnabled())
                debug("PersonService#findOne=" + result);
            return result;
        } catch (Throwable t) {
            error("PersonService#findOne=failed");
            throw t;
        }
    }

    @Transactional
    @Override
    public Person save(Person person) {
        if (isInfoEnabled())
            info("PersonService#save[" + person + "]");
        try {
            Person result;
            if (person.getId() != 0) {
                Optional<Person> existing = findOne(person.getId());
                if (!existing.isPresent())
                    throw new IllegalArgumentException("Can not update a person. Entity not found.");
                result = existing.get();
                result.setName(person.getName());
            } else {
                long lastId = people.stream()
                    .mapToLong(Person::getId)
                    .max()
                    .orElse(0L);
                person.setId(lastId + 1);
                people.add(person);
                result = person;
            }
            if (isInfoEnabled())
                info("PersonService#save=" + result);
            return result;
        } catch (Throwable t) {
            error("PersonService#save=failed");
            throw t;
        }
    }

    @Transactional
    @Override
    public void remove(Person person) {
        if (isInfoEnabled())
            info("PersonService#remove[" + person + "]");
        try {
            Optional<Person> existing = findOne(person.getId());
            if (!existing.isPresent())
                throw new IllegalArgumentException("Can not remove a person. Entity not found.");
            people.remove(existing.get());
            if (isInfoEnabled())
                info("PersonService#remove=ok");
        } catch (Throwable t) {
            error("PersonService#remove=failed");
            throw t;
        }
    }

}
