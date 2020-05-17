package org.medihub.application.ports.incoming.medical_doctor;

import lombok.Value;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;
import java.util.Map;

@Value
public class GetDoctorScheduleOutput {
    Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules;
}
