package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.appointment.Appointment;

public interface AddAppointmentToMedicalDoctorSchedulePort {
    void addAppointmentToSchedule(Appointment appointment);
}
