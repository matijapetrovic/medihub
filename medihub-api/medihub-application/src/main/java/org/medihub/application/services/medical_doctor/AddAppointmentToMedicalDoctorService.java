package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddAppointmentToMedicalDoctorScheduleUseCase;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.domain.appointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
public class AddAppointmentToMedicalDoctorService implements AddAppointmentToMedicalDoctorScheduleUseCase {
    private final AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort;
    private final GetDoctorsPort getDoctorsPort;


    @Override
    public void addAppointmentToMedicalDoctorSchedule(
            AddAppointmentUseCase.AddAppointmentCommand command,
            Appointment appointment) {
        addAppointmentToMedicalDoctorSchedulePort.addAppointmentToSchedule(
                getDoctorsPort.getMedicalDoctorById(command.getDoctorId()),
                LocalDate.parse(command.getDate()),
                LocalTime.parse(command.getTime()),
                appointment.getId());
    }
}
