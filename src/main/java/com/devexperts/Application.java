package com.devexperts;

import com.devexperts.domain.Person;
import com.devexperts.service.CrudService;
import com.devexperts.service.PersonService;
import com.devexperts.tx.Transaction;
import com.devexperts.tx.TransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.stream.StreamSupport;

/**
 * @author ifedorenkov
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
