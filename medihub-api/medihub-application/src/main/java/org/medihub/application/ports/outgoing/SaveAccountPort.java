package org.medihub.application.ports.outgoing;

import org.medihub.domain.identity.Account;

public interface SaveAccountPort {
    void saveAccount(Account account);
}