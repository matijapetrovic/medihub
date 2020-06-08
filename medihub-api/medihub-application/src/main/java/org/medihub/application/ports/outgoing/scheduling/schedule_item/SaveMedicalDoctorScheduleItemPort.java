package org.medihub.application.ports.outgoing.scheduling.schedule_item;

import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import java.time.LocalDate;

public interface SaveMedicalDoctorScheduleItemPort {
    void saveMedicalDoctorScheduleItem(
            MedicalDoctorScheduleItem scheduleItem,
            MedicalDoctor doctor,
            LocalDate date);
}
