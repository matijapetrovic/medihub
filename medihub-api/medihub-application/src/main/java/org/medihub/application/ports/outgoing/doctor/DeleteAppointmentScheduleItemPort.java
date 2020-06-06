package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;

public interface DeleteAppointmentScheduleItemPort {
    void deleteAppointmentItem(Long id);
    void deleteByAppointmentId(Long appointmentId);
}
