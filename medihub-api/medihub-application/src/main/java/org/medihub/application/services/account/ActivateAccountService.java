package org.medihub.application.services.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.account.ActivateAccountUseCase;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.domain.account.Account;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class ActivateAccountService implements ActivateAccountUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public void activateAccount(@NotNull Long accountId) throws NotFoundException, ForbiddenException {
        Account account = loadAccountPort.loadAccount(accountId)
                .orElseThrow(NotFoundException::new);

        if (account.isActivated())
            throw new ForbiddenException("Account already activated");

        account.activate();
        saveAccountPort.saveAccount(account);
    }
}
