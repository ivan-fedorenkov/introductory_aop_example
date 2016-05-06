package com.devexperts.tx;

/**
 * An example of a transaction object.
 *
 * @author ifedorenkov
 */
public interface Transaction {

    /**
     * Begin the transaction.
     */
    void begin();

    /**
     * Commit the transaction.
     */
    void commit();

    /**
     * Rollback the transaction.
     */
    void rollback();

}
