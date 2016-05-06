package com.devexperts.service;

import com.devexperts.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author ifedorenkov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void testCreate() {
        String personName = "TEST_PERSON_NAME";

        Iterable<Person> people = personService.findAll();
        Optional<Person> foundExisting = StreamSupport.stream(people.spliterator(), false)
                .filter(p -> Objects.equals(personName, p.getName()))
                .findFirst();
        Assert.assertFalse(foundExisting.isPresent());


        Person person = new Person();
        person.setName(personName);
        Person created = personService.save(person);

        Assert.assertNotNull(created.getId());
        Assert.assertEquals(personName, created.getName());

        people = personService.findAll();
        Optional<Person> foundCreated = StreamSupport.stream(people.spliterator(), false)
            .filter(p -> Objects.equals(personName, p.getName()))
            .findFirst();
        Assert.assertTrue(foundCreated.isPresent());
    }

}
