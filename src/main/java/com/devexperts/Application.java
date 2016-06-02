package com.devexperts;

import com.devexperts.aop.AspectForBenchmark;
import com.devexperts.domain.Person;
import com.devexperts.service.CrudService;
import com.devexperts.service.PersonService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * @author ifedorenkov
 */
public class Application {

    private static final String PERSON_NAME = "any name";

    @State(Scope.Benchmark)
    public static class Service {
        private final CrudService<Person, Long> service;

        public Service() {
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory(new PersonService());
            proxyFactory.addAspect(new AspectForBenchmark());
            service = proxyFactory.getProxy();
            try {
                PersonService pService = (PersonService) proxyFactory.getTargetSource().getTarget();
                pService.setPersonService(service);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
