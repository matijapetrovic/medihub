package org.medihub.application.services.clinic.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.domain.account.Address;
import org.medihub.domain.clinic.Clinic;

@RequiredArgsConstructor
public class AddClinicService implements AddClinicUseCase {
    private final SaveClinicPort saveClinicPort;

    @Override
    public Clinic addClinic(AddClinicCommand command) {
        Clinic clinic = new Clinic(
                command.getName(),
                new Address(
                        command.getAddress(),
                        command.getCity(),
                        command.getCountry()),
                command.getDescription());
        return saveClinicPort.saveClinic(clinic);
    }
}
