package org.medihub.application.services.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class ChangePasswordService implements ChangePasswordUseCase {
    private final LoadAccountPort loadAccountPort;
    private final EncoderPort encoderPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public boolean changePassword(ChangePasswordCommand command) throws AccountNotFoundException {
        Account account = loadAccountPort
                .loadAccount(command.getEmail())
                .orElseThrow(AccountNotFoundException::new);

        if (!account.changePassword(encoderPort.encode(command.getNewPassword())))
            return false;

        saveAccountPort.saveAccount(account);
        return true;
    }
}
