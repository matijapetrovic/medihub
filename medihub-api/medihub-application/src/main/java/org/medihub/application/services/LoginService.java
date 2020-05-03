package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.outgoing.áuthentication.AuthenticationPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final AuthenticationPort authenticationPort;

    @Override
    public Account login(LoginCommand command) {
        return authenticationPort.authenticate(command.getEmail(), command.getPassword());
    }
}
