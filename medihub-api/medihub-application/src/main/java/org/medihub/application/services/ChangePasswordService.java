package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.ChangePasswordUseCase;
import org.medihub.application.ports.outgoing.EncoderPort;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class ChangePasswordService implements ChangePasswordUseCase {
    private final LoadAccountPort loadAccountPort;
    private final EncoderPort encoderPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public boolean changePassword(ChangePasswordCommand command) {
        Account account = loadAccountPort.loadAccount(command.getEmail());
        if (!account.changePassword(encoderPort.encode(command.getNewPassword()))) {
            return false;
        }

        saveAccountPort.saveAccount(account);
        return true;
    }
}
