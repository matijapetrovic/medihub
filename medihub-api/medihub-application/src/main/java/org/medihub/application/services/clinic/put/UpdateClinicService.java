package org.medihub.application.services.clinic.put;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.UpdateClinicUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Address;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;

@RequiredArgsConstructor
public class UpdateClinicService implements UpdateClinicUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadClinicPort loadClinicPort;
    private final SaveClinicPort saveClinicPort;

    @Override
    public void updateClinic(UpdateClinicCommand command) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Clinic clinic = loadClinicPort.loadClinic(clinicAdmin.getClinic().getId());
        saveClinicPort.saveClinic(setCommand(command, clinic));
    }

    private Clinic setCommand(UpdateClinicCommand command, Clinic clinic) {
        clinic.setName(command.getName());
        clinic.setAddress(new Address(
           command.getAddressLine(),
           command.getCity(),
           command.getCountry()
        ));
        clinic.setDescription(command.getDescription());
        return clinic;
    }
}
