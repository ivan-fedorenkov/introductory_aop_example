package com.devexperts.service;

import com.devexperts.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A base CRUD service class that provides a number of services such as logging, transaction management.
 *
 * @author ifedorenkov
 */
public abstract class CrudServiceBase<T, ID> implements CrudService<T, ID> {

    private final Logger logger;

    private TransactionManager txManager;

    public CrudServiceBase() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @Autowired
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * Returns the service layer transaction manager.
     */
    protected TransactionManager getTxManager() {
        if (txManager == null)
            throw new IllegalStateException("Tx manager has not been configured.");
        return txManager;
    }

    /**
     * Checks if logger level allows debug messages.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Debug message to logger.
     */
    public void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * Checks if logger level allows info messages.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Info message to logger.
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * Checks if logger level allows error messages.
     */
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * Error message to logger.
     */
    public void error(String msg) {
        logger.error(msg);
    }

}
