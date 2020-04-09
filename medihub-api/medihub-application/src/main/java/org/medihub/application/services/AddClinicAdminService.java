package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicAdminUseCase;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;

@RequiredArgsConstructor
public class AddClinicAdminService implements AddClinicAdminUseCase {
    private final AddClinicAdminPort dbPort;

    @Override
    public String addClinicAdmin() {
        return dbPort.addClinicAdmin();
    }
}
