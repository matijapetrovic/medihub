package org.medihub.application.ports.incoming.account;

import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.domain.account.Account;

public interface GetAccountQuery {
    Account getAccount(String email) throws AccountNotFoundException;
}
