package org.medihub.application.ports.outgoing.transactions;

public interface TransactionsPort {
    void startTransaction();
    void commitTransaction();
    void rollbackTransaction();
}
