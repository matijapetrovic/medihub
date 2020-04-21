package org.medihub.application.ports.outgoing;

import org.medihub.domain.identity.Account;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> loadAccount(String email);
}
