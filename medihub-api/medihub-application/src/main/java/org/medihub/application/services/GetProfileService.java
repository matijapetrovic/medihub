package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.profile.GetProfileOutput;
import org.medihub.application.ports.incoming.profile.GetProfileQuery;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class GetProfileService implements GetProfileQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public GetProfileOutput getProfile(String email) throws AccountNotFoundException {
        Account account = loadAccountPort
                .loadAccount(email)
                .orElseThrow(AccountNotFoundException::new);

        return new GetProfileOutput(
                account.getFirstName(),
                account.getLastName(),
                account.getAddress(),
                account.getCity(),
                account.getCountry(),
                account.getTelephoneNumber());

    }
}
