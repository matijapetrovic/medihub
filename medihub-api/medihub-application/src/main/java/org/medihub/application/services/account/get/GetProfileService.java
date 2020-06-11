package org.medihub.application.services.account.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.account.profile.GetProfileOutput;
import org.medihub.application.ports.incoming.account.profile.GetProfileQuery;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.profile.LoadPersonalInfoPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.PersonalInfo;

@RequiredArgsConstructor
public class GetProfileService implements GetProfileQuery {
    private final LoadAccountPort loadAccountPort;
    private final LoadPersonalInfoPort loadPersonalInfoPort;

    @Override
    public GetProfileOutput getProfile(String email) throws AccountNotFoundException, NotFoundException {
        Account account = loadAccountPort
                .loadAccount(email)
                .orElseThrow(AccountNotFoundException::new);

        PersonalInfo personalInfo =  loadPersonalInfoPort
                .loadPersonalInfoByAccountId(account.getId());

        return new GetProfileOutput(
                personalInfo.getFirstName(),
                personalInfo.getLastName(),
                personalInfo.getAddress(),
                personalInfo.getCity(),
                personalInfo.getCountry(),
                personalInfo.getTelephoneNumber());

    }
}
