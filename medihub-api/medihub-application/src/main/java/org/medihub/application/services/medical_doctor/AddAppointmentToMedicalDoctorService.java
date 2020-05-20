package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddAppointmentToMedicalDoctorScheduleUseCase;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.domain.appointment.Appointment;

@RequiredArgsConstructor
public class AddAppointmentToMedicalDoctorService implements AddAppointmentToMedicalDoctorScheduleUseCase {
    private final AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort;

    @Override
    public void addAppointmentToMedicalDoctorSchedule(AddScheduleCommand command) {
        addAppointmentToMedicalDoctorSchedulePort.addAppointmentToSchedule(
                new Appointment());
    }
}
