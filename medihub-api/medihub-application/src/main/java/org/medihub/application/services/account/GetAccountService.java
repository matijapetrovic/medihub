package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.GetAccountQuery;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;

import java.util.Optional;

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
