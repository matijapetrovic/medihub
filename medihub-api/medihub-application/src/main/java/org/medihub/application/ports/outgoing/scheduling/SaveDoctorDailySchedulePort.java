package org.medihub.application.ports.outgoing.scheduling;

import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;

public interface SaveDoctorDailySchedulePort {
    void saveDoctorDailySchedule(MedicalDoctor doctor,
                                 LocalDate date,
                                 DailySchedule<MedicalDoctorScheduleItem> dailySchedule);
}
