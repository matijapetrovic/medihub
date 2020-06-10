package org.medihub.application.ports.incoming.scheduling;

import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;

public interface GetAppointmentScheduleItemByAppointmentIdUseCase {
    MedicalDoctorAppointmentScheduleItem getItem(Long id);
}
