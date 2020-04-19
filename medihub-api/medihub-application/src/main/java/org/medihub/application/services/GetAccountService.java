package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.GetAccountQuery;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class GetAccountService implements GetAccountQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Account getAccount(String email) {
        return loadAccountPort.loadAccount(email);
    }
}
