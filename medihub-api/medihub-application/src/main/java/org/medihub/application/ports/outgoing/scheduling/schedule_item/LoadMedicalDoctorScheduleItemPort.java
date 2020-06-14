package org.medihub.application.ports.outgoing.scheduling.schedule_item;

import org.medihub.domain.medical_doctor.MedicalDoctorPredefinedAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

public interface LoadMedicalDoctorScheduleItemPort {
    MedicalDoctorPredefinedAppointmentScheduleItem loadByPredefinedAppointmentId(Long predefinedAppointmentId);
    Integer countFutureScheduleItems(Long doctorId);
}
