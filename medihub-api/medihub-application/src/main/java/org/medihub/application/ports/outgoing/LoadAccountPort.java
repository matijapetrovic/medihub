package org.medihub.application.ports.outgoing;

import org.medihub.domain.identity.Account;

public interface LoadAccountPort {
    Account loadAccount(String email);
}
