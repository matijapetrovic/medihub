package org.medihub.domain.medical_nurse;

import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.domain.scheduling.Schedule;

import java.time.LocalDate;
import java.util.Map;

public class MedicalNurseSchedule extends Schedule<MedicalNurseScheduleItem> {

    public MedicalNurseSchedule(
            Map<LocalDate, DailySchedule<MedicalNurseScheduleItem>> dailySchedules) {
        super(dailySchedules);
    }
}
