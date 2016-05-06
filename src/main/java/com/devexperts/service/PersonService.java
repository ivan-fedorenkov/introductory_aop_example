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
        return new ArrayList<>(people);
    }

    @Logged
    @Override
    public Optional<Person> findOne(Long id) {
        return people.stream()
            .filter(p -> Objects.equals(p.getId(), id))
            .findFirst();
    }

    @Logged(level = LogLevel.INFO)
    @Override
    public Person save(Person person) {
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
        return result;
    }

    @Logged(level = LogLevel.INFO)
    @Override
    public void remove(Person person) {
        Optional<Person> existing = findOne(person.getId());
        if (!existing.isPresent())
            throw new IllegalArgumentException("Can not remove a person. Entity not found.");
        people.remove(existing.get());
    }

}
