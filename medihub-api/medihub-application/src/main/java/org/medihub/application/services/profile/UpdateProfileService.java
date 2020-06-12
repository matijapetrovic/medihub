package org.medihub.application.services.profile;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.profile.UpdateProfileUseCase;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.profile.LoadPersonalInfoPort;
import org.medihub.application.ports.outgoing.profile.SavePersonalInfoPort;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.account.Account;

@RequiredArgsConstructor
public class UpdateProfileService implements UpdateProfileUseCase {
    private final LoadAccountPort loadAccountPort;
    private final LoadPersonalInfoPort loadPersonalInfoPort;
    private final SavePersonalInfoPort savePersonalInfoPort;

    @Override
    public void updateProfile(UpdateProfileCommand command) throws AccountNotFoundException, NotFoundException {
        Account account = loadAccountPort
                .loadAccount(command.getEmail())
                .orElseThrow(AccountNotFoundException::new);

        PersonalInfo personalInfo = loadPersonalInfoPort.loadPersonalInfoByAccountId(account.getId());
        personalInfo = new PersonalInfo(
                personalInfo.getId(),
                command.getFirstName(),
                command.getLastName(),
                new Address(
                        command.getAddress(),
                        command.getCity(),
                        command.getCountry()),
                command.getTelephoneNumber(),
                account);

        savePersonalInfoPort.savePersonalInfo(personalInfo);
    }
}
