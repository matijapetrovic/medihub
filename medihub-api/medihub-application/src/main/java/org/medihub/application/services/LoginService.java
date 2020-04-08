package org.medihub.application.services;

import org.medihub.application.ports.incoming.LoginUseCase;

public class LoginService implements LoginUseCase {
    @Override
    public boolean login(LoginCommand command) {
        return true;
    }
}
