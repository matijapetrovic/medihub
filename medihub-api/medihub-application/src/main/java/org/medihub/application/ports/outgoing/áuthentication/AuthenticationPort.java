package org.medihub.application.ports.outgoing.áuthentication;

import org.medihub.domain.identity.Account;

public interface AuthenticationPort {
    Account authenticate(String email, String password);
    boolean reauthenticate(String email, String password);
}
