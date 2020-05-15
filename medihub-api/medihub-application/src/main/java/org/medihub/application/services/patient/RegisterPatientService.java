package org.medihub.application.services.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.patient.SaveRegistrationRequestPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Address;
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
