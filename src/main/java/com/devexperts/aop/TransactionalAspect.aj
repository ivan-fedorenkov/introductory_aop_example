package com.devexperts.aop;

import com.devexperts.tx.Transaction;

/**
 * @author ifedorenkov
 */
public aspect TransactionalAspect extends TransactionalAspectBase {

    pointcut transactional() : @annotation(Transactional) || within(@Transactional *);

    Object around() : com.devexperts.aop.Pointcuts.serviceMethodExecution() && transactional() {
        Transaction tx = txManager.getTransaction();
        try {
            tx.begin();
            Object result = proceed();
            tx.commit();
            return result;
        } catch (Throwable t) {
            tx.rollback();
            throw new RuntimeException(t);
        }
    }

}
