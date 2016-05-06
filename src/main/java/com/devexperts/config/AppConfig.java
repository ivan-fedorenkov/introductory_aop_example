package com.devexperts.config;

import com.devexperts.aop.TransactionalAspect;
import com.devexperts.domain.Person;
import com.devexperts.service.CrudService;
import com.devexperts.service.PersonService;
import com.devexperts.tx.SimpleTransactionManager;
import com.devexperts.tx.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ifedorenkov
 */
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public TransactionManager txManager() {
        return new SimpleTransactionManager();
    }

    @Bean
    public TransactionalAspect txAspect() {
        return new TransactionalAspect(txManager());
    }

    @Bean
    public CrudService<Person, Long> personService() {
        return new PersonService();
    }

}
