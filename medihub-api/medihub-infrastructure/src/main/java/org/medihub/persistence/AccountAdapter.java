package org.medihub.persistence;

import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;
import org.medihub.domain.identity.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountAdapter implements LoadAccountPort {
    @Override
    public Account loadAccount(String email) {
        return new Account(
                "admin@admin.com",
                "$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu",
                List.of(Authority.of("ROLE_CLINICAL_CENTRE_ADMIN")));
    }
}
