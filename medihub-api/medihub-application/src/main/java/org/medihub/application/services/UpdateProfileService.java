package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.profile.UpdateProfileUseCase;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.domain.Address;
import org.medihub.domain.PersonalInfo;
import org.medihub.domain.identity.Account;

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
