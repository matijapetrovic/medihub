package org.medihub.application.ports.outgoing.scheduling.schedule_item;

import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;

public interface LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort {
    MedicalDoctorAppointmentScheduleItem loadById(Long id);
}
