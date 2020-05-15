package org.medihub.application.services.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.profile.GetProfileOutput;
import org.medihub.application.ports.incoming.account.profile.GetProfileQuery;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.domain.account.Account;

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
