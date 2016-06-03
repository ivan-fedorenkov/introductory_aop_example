package com.devexperts.service;

import com.devexperts.aop.LogLevel;
import com.devexperts.aop.Logged;
import com.devexperts.aop.Transactional;
import com.devexperts.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ifedorenkov
 */
@Transactional
public class PersonService extends CrudServiceBase<Person, Long> {

    private final List<Person> people = new ArrayList<>();

    @Logged
    @Override
    public Iterable<Person> findAll() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        ArrayList<Person> people = new ArrayList<>(this.people);

        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        return people;
    }

    @Logged
    @Override
    public Optional<Person> findOne(Long id) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        Optional<Person> person = people.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();

        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        return person;
    }

    @Logged(level = LogLevel.INFO)
    @Override
    public Person save(Person person) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

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

        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        return result;
    }

    @Logged(level = LogLevel.INFO)
    @Override
    public void remove(Person person) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        Optional<Person> existing = findOne(person.getId());
        if (!existing.isPresent())
            throw new IllegalArgumentException("Can not remove a person. Entity not found.");
        people.remove(existing.get());

        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

}
