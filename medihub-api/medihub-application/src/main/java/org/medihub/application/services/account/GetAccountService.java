package org.medihub.application.services.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.GetAccountQuery;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class GetAccountService implements GetAccountQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Account getAccount(String email) throws AccountNotFoundException {
        return loadAccountPort
                .loadAccount(email)
                .orElseThrow(AccountNotFoundException::new);
    }
}
