package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AddAppointmentToMedicalDoctorSchedulePort {
    void addAppointmentToSchedule(MedicalDoctor doctor,
                                  LocalDate date,
                                  LocalTime time,
                                  Long appointmentId);
}
