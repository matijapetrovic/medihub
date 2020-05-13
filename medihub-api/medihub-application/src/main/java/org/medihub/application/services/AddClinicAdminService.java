package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.domain.account.Address;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Authority;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AddClinicAdminService implements AddClinicAdminUseCase {
    private final AddClinicAdminPort dbPort;
    private final LoadAccountPort loadAccountPort;
    private final EncoderPort encoderPort;
    private final LoadClinicPort loadClinicPort;

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
            encoderPort.encode(command.getPassword()),
            personalInfo,
            false,
            List.of(new Authority(4L, "ROLE_CLINIC_ADMIN"))
        );

        Clinic clinic = loadClinicPort.loadClinic(command.getClinic());

        ClinicAdmin clinicAdmin = new ClinicAdmin(
                null,
                account,
                clinic
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
