package com.devexperts.config;

import com.devexperts.service.PersonService;
import com.devexperts.tx.SimpleTransactionManager;
import com.devexperts.tx.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ifedorenkov
 */
@Configuration
public class AppConfig {

    @Bean
    public TransactionManager txManager() {
        return new SimpleTransactionManager();
    }

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

}
