package com.devexperts.tx;

/**
 * An example of a transaction manager.
 *
 * @author ifedorenkov
 */
public interface TransactionManager {

    /**
     * Obtains transaction from somewhere (e.g. pool) and returns it.
     */
    Transaction getTransaction();

}
