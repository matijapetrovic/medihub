package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.identity.Account;

@RequiredArgsConstructor
public class AddMedicalDoctorService implements AddMedicalDoctorUseCase {
    private final SaveDoctorPort saveDoctorPort;
    private final EncoderPort encoderPort;

    @Override
    public void addDoctor(AddMedicalDoctorCommand command) {
        MedicalDoctor entity = new MedicalDoctor(
                command.getId(),
                new Account(
                        command.getAccount().getId(),
                        command.getAccount().getEmail(),
                        encoderPort.encode(command.getAccount().getPassword()),
                        command.getAccount().getPersonalInfo(),
                        command.getAccount().isPasswordChanged(),
                        command.getAccount().getAuthorities()
                ),
                command.getWorkingCalendar(),
                command.getClinic(),
                command.getAppointments()
        );
        saveDoctorPort.saveDoctor(entity);
    }
}
