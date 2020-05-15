package org.medihub.application.ports.outgoing.authentication;

import org.medihub.domain.account.Account;

public interface GetAuthenticatedPort {
    Account getAuthenticated();
}
