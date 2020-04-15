package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.SaveAccountUseCase;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class SaveAccountService implements SaveAccountUseCase {
    private final SaveAccountPort saveAccountPort;

    @Override
    public void saveAccount(Account account) {
        saveAccountPort.saveAccount(account);
    }
}
