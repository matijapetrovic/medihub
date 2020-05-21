package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
import org.medihub.domain.appointment.Appointment;

public interface AddAppointmentToMedicalDoctorScheduleUseCase {
    void addAppointmentToMedicalDoctorSchedule(AddAppointmentCommand command, Appointment appointment);
}
