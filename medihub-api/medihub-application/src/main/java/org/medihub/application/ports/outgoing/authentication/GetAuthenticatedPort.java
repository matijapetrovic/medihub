package org.medihub.application.ports.outgoing.authentication;

import org.medihub.domain.identity.Account;

public interface GetAuthenticatedPort {
    Account getAuthenticated();
}
