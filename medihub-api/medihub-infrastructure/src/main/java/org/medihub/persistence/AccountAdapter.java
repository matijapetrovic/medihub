package org.medihub.persistence;

import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter implements LoadAccountPort {
    @Override
    public Account loadAccount(String username) {
        return null;
    }
}
