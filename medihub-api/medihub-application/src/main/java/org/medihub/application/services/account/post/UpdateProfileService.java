package org.medihub.application.services.account.post;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.profile.UpdateProfileUseCase;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.account.Account;

@RequiredArgsConstructor
public class UpdateProfileService implements UpdateProfileUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public void updateProfile(UpdateProfileCommand command) throws AccountNotFoundException {
        Account account = loadAccountPort
                .loadAccount(command.getEmail())
                .orElseThrow(AccountNotFoundException::new);

        account.updateProfile(
                new PersonalInfo(
                        command.getFirstName(),
                        command.getLastName(),
                        new Address(
                                command.getAddress(),
                                command.getCity(),
                                command.getCountry()),
                        command.getTelephoneNumber()));

        saveAccountPort.saveAccount(account);
    }
}