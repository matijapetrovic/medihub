package org.medihub.application.ports.outgoing.scheduling;

import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;

public interface LoadDoctorDailySchedulePort {
    DailySchedule<MedicalDoctorScheduleItem> loadDailySchedule(Long doctorId, LocalDate date);
}
