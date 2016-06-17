package com.devexperts.aop;

import com.devexperts.tx.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ifedorenkov
 */
public abstract class TransactionalAspectBase {

    protected TransactionManager txManager;

    @Autowired
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

}
