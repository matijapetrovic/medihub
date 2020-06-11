package org.medihub.transactions;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.transactions.TransactionsPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@RequiredArgsConstructor
public class TransactionsAdapter implements TransactionsPort {
    private final PlatformTransactionManager transactionManager;
    private TransactionStatus status;

    @Override
    public void startTransaction() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        status = transactionManager.getTransaction(definition);
    }

    @Override
    public void commitTransaction() {
        transactionManager.commit(status);
    }

    @Override
    public void rollbackTransaction() {
        transactionManager.rollback(status);
    }
}
