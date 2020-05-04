package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.domain.Address;
import org.medihub.domain.Clinic;
import org.medihub.domain.ClinicAdmin;
import org.medihub.domain.PersonalInfo;
import org.medihub.domain.identity.Account;

import java.util.Optional;

@RequiredArgsConstructor
public class AddClinicAdminService implements AddClinicAdminUseCase {
    private final AddClinicAdminPort dbPort;
    private final LoadAccountPort loadAccountPort;
    private final EncoderPort encoderPort;

    @Override
    public ClinicAdmin addClinicAdmin(AddClinicAdminCommand command) throws AccountNotFoundException{
        ensureEmailNotTaken(command.getEmail());

        Address address = new Address(
            command.getAddress(),
            command.getCity(),
            command.getCountry()
        );

        PersonalInfo personalInfo = new PersonalInfo(
            command.getFirstName(),
            command.getLastName(),
            address,
            command.getTelephoneNumber()
        );

        Account account = new Account(
            null,
            command.getEmail(),
            command.getPassword(),
            personalInfo,
            false,
            null
        );

        ClinicAdmin clinicAdmin = new ClinicAdmin(
                null,
                account,
                new Clinic()
        );

        return dbPort.addClinicAdmin(clinicAdmin);
    }

    private void ensureEmailNotTaken(String email) throws AccountNotFoundException {
        Optional<Account> account = loadAccountPort.loadAccount(email);
        if (account.isPresent()) {
            throw new AccountNotFoundException();
        }
    }

}
