package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.LoginUseCase;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;

import java.util.Collections;

@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final LoadAccountPort loadAccountPort;
    @Override
    public boolean login(LoginCommand command) {
        //Account account = loadAccountPort.loadAccount(command.getUsername());
        Account account = new Account(command.getEmail(), command.getPassword(), Collections.emptyList());
        return account.authenticate(command.getEmail(), command.getPassword());
    }
}
