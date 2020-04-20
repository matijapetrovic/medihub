package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.EncoderPort;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveRegistrationRequestPort;
import org.medihub.domain.identity.Account;
import org.medihub.domain.patient.Address;
import org.medihub.domain.patient.RegistrationRequest;

import java.util.Optional;

@RequiredArgsConstructor
public class RegisterPatientService implements RegisterPatientUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveRegistrationRequestPort saveRegistrationRequestPort;
    private final EncoderPort encoderPort;

    @Override
    public RegistrationRequest registerPatient(RegisterPatientCommand command) throws AccountNotFoundException {
        ensureEmailNotTaken(command.getEmail());

        RegistrationRequest request =
                new RegistrationRequest(
                        null,
                        command.getEmail(),
                        encoderPort.encode(command.getPassword()),
                        command.getFirstName(),
                        command.getLastName(),
                        new Address(
                                command.getAddress(),
                                command.getCity(),
                                command.getCountry()),
                        command.getTelephoneNumber(),
                        command.getInsuranceNumber());


        return saveRegistrationRequestPort.saveRegistrationRequest(request);
    }

    private void ensureEmailNotTaken(String email) throws AccountNotFoundException {
        Optional<Account> account = loadAccountPort.loadAccount(email);
        if (account.isPresent()) {
            throw new AccountNotFoundException();
        }
    }
}
