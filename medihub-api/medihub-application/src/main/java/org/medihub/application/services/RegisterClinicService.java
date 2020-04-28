package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.RegisterClinicUseCase;
import org.medihub.application.ports.outgoing.SaveClinicPort;
import org.medihub.domain.Address;
import org.medihub.domain.Clinic;

@RequiredArgsConstructor
public class RegisterClinicService implements RegisterClinicUseCase {
    private final SaveClinicPort saveClinicPort;

    @Override
    public void registerClinic(RegisterClinicCommand command) {
        Clinic clinic = new Clinic(
                command.getName(),
                new Address(
                        command.getAddress(),
                        command.getCity(),
                        command.getCountry()),
                command.getDescription());
        saveClinicPort.saveClinic(clinic);
    }
}
