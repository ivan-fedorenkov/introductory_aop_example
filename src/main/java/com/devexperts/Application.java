package com.devexperts;

import com.devexperts.domain.Person;
import com.devexperts.service.PersonService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * @author ifedorenkov
 */
public class Application {

    private static final String PERSON_NAME = "any name";

    @State(Scope.Benchmark)
    public static class Service {
        private final PersonService service;

        public Service() {
            service = new PersonService();
            service.setPersonService(service);
        }
    }

    @Benchmark
    public void serviceBenchmark(Service serviceHolder) {
        Person p = new Person();
        p.setName(PERSON_NAME);
        p = serviceHolder.service.save(p);
        serviceHolder.service.remove(p);
    }

}
