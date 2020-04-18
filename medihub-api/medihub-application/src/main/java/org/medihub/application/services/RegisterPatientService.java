package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.SaveRegistrationRequestPort;
import org.medihub.domain.patient.Address;
import org.medihub.domain.patient.RegistrationRequest;

@RequiredArgsConstructor
public class RegisterPatientService implements RegisterPatientUseCase {
    private final SaveRegistrationRequestPort saveRegistrationRequestPort;

    @Override
    public RegistrationRequest registerPatient(RegisterPatientCommand command) {
        RegistrationRequest request =
                new RegistrationRequest(
                        null,
                        command.getEmail(),
                        command.getPassword(),
                        command.getFirstName(),
                        command.getLastName(),
                        new Address(
                                command.getAddress(),
                                command.getCity(),
                                command.getCountry()),
                        command.getTelephoneNum(),
                        command.getSecurityNum());

        return saveRegistrationRequestPort.saveRegistrationRequest(request);
    }
}
