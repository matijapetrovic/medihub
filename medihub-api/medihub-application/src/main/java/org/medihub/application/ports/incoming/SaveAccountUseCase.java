package org.medihub.application.ports.incoming;

import org.medihub.domain.identity.Account;

public interface SaveAccountUseCase {
    void saveAccount(Account account);
}
