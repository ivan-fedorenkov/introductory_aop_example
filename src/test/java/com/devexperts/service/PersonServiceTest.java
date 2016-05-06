package com.devexperts.service;

import com.devexperts.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author ifedorenkov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
public class PersonServiceTest {

    private static final String TEST_PERSON_NAME = "TEST_PERSON_NAME";

    @Autowired
    private PersonService personService;

    @Test
    public void testCreate() {

        Iterable<Person> people = personService.findAll();
        Optional<Person> foundExisting = StreamSupport.stream(people.spliterator(), false)
                .filter(p -> Objects.equals(TEST_PERSON_NAME, p.getName()))
                .findFirst();
        Assert.assertFalse(foundExisting.isPresent());


        Person person = createTestPerson(TEST_PERSON_NAME);
        Person created = personService.save(person);

        Assert.assertNotNull(created.getId());
        Assert.assertEquals(TEST_PERSON_NAME, created.getName());

        people = personService.findAll();
        Optional<Person> foundCreated = StreamSupport.stream(people.spliterator(), false)
            .filter(p -> Objects.equals(TEST_PERSON_NAME, p.getName()))
            .findFirst();
        Assert.assertTrue(foundCreated.isPresent());
    }

    @Test
    public void testUpdate() {
        Person person = createTestPerson(TEST_PERSON_NAME);
        person = personService.save(person);

        String updatedName = "UPDATED_PERSON_NAME";
        person.setName(updatedName);
        personService.save(person);

        Person updatedPerson = personService.findOne(person.getId()).orElseThrow(RuntimeException::new);
        Assert.assertEquals(updatedName, updatedPerson.getName());
    }

    @Test
    public void testRemove() {
        Person person = createTestPerson(TEST_PERSON_NAME);
        person = personService.save(person);

        personService.remove(person);

        Optional<Person> removed = personService.findOne(person.getId());
        Assert.assertFalse(removed.isPresent());
    }

    private static Person createTestPerson(String name) {
        Person p = new Person();
        p.setName(name);
        return p;
    }

}
