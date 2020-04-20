package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.EncoderPort;
import org.medihub.application.ports.outgoing.SaveRegistrationRequestPort;
import org.medihub.domain.patient.Address;
import org.medihub.domain.patient.RegistrationRequest;

@RequiredArgsConstructor
public class RegisterPatientService implements RegisterPatientUseCase {
    private final SaveRegistrationRequestPort saveRegistrationRequestPort;
    private final EncoderPort encoderPort;

    @Override
    public RegistrationRequest registerPatient(RegisterPatientCommand command) {
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
}
