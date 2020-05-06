package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.medihub.domain.identity.Authority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AddMedicalDoctorService implements AddMedicalDoctorUseCase {
    private final SaveDoctorPort saveDoctorPort;
    private final EncoderPort encoderPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public void addDoctor(AddMedicalDoctorCommand command) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdmin(authenticated.getId());

        MedicalDoctor entity = new MedicalDoctor(
                command.getId(),
                new Account(
                        null,
                        command.getEmail(),
                        encoderPort.encode(command.getPassword()),
                        new PersonalInfo(
                                command.getFirstName(),
                                command.getLastName(),
                                new Address(
                                        command.getAddressLine(),
                                        command.getCity(),
                                        command.getCountry()
                                ),
                                command.getTelephoneNumber()
                        ),
                        command.isPasswordChanged(),
                        new ArrayList<Authority>()
                ),
                new WorkingCalendar(),
                clinicAdmin.getClinic(),
                new WorkingTime( command.getFrom(), command.getTo()),
                Set.of()
        );
        saveDoctorPort.saveDoctor(entity);
    }
}
