package org.medihub.application.ports.outgoing.account;

import org.medihub.domain.account.Account;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> loadAccount(String email);
    Optional<Account> loadAccount(Long accountId);
}
