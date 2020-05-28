package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.domain.*;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Authority;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.reviewing.Rating;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class AddMedicalDoctorService implements AddMedicalDoctorUseCase {
    private final SaveDoctorPort saveDoctorPort;
    private final EncoderPort encoderPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadAppointmentTypePort loadAppointmentTypePort;

    @Override
    public void addDoctor(AddMedicalDoctorCommand command) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(authenticated.getId());

        AppointmentType specialization = loadAppointmentTypePort.loadAppointmentType(command.getAppointmentTypeId());

        MedicalDoctor entity = new MedicalDoctor(
                null,
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
                                        command.getCountry()),
                                command.getTelephoneNumber()),
                        false,
                        List.of(new Authority(2L, "ROLE_DOCTOR"))),
                clinicAdmin.getClinic(),
                new WorkingTime(LocalTime.parse(command.getFrom()),
                                LocalTime.parse(command.getTo())),
                specialization,
                new Rating(BigDecimal.ZERO,0));
        saveDoctorPort.saveDoctor(entity);
    }
}
