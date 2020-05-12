package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.domain.scheduling.Schedule;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class MedicalDoctorSchedule extends Schedule<MedicalDoctorScheduleItem> {
    private MedicalDoctor doctor;

    public MedicalDoctorSchedule(
            Long id,
            Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules,
            MedicalDoctor doctor) {
        super(id, dailySchedules);
        this.doctor = doctor;
    }
}
