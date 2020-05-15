package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.domain.scheduling.Schedule;

import java.time.LocalDate;
import java.util.Map;

public class MedicalDoctorSchedule extends Schedule<MedicalDoctorScheduleItem> {

    public MedicalDoctorSchedule(
            Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules) {
        super(dailySchedules);
    }
}
