package com.devexperts.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ifedorenkov
 */
public class SimpleTransactionManager implements TransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);

    private final TransactionImpl tx = new TransactionImpl();

    @Override
    public Transaction getTransaction() {
        return tx;
    }

    private class TransactionImpl implements Transaction {

        @Override
        public void begin() {
            logger.info("starting transaction");
        }

        @Override
        public void commit() {
            logger.info("committing transaction");
        }

        @Override
        public void rollback() {
            logger.info("rolling back transaction");
        }

    }

}
