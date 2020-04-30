package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.AddClinicAdminUseCase;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.application.ports.outgoing.EncoderPort;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.domain.Address;
import org.medihub.domain.ClinicAdmin;
import org.medihub.domain.ClinicCenterAdmin;
import org.medihub.domain.PersonalInfo;
import org.medihub.domain.identity.Account;

import java.util.Optional;

@RequiredArgsConstructor
public class AddClinicAdminService implements AddClinicAdminUseCase {
    private final AddClinicAdminPort dbPort;

    @Override
    public ClinicAdmin addClinicAdmin(ClinicAdmin clinicAdmin) {
        return dbPort.addClinicAdmin(clinicAdmin);
    }
}
