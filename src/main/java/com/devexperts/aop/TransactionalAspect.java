package com.devexperts.aop;

import com.devexperts.tx.Transaction;
import com.devexperts.tx.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ifedorenkov
 */
@Aspect
public class TransactionalAspect {

    private TransactionManager txManager;

    @Autowired
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Pointcut("@annotation(Transactional) || within(@Transactional *)")
    public void transactional() {}

    @Around("com.devexperts.aop.Pointcuts.serviceMethodExecution() && transactional()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Transaction tx = txManager.getTransaction();
        try {
            tx.begin();
            Object result = pjp.proceed();
            tx.commit();
            return result;
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }
    }

}
