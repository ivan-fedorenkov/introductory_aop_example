package com.devexperts.config;

import com.devexperts.aop.TransactionalAspect;
import com.devexperts.domain.Person;
import com.devexperts.service.CrudService;
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
    public TransactionalAspect txAspect() throws Throwable {
        TransactionalAspect txAspect = (TransactionalAspect) Class.forName("com.devexperts.aop.TransactionalAspect")
            .getMethod("aspectOf").invoke(null);
        txAspect.setTxManager(txManager());
        return txAspect;
    }

    @Bean
    public CrudService<Person, Long> personService() {
        return new PersonService();
    }

}
