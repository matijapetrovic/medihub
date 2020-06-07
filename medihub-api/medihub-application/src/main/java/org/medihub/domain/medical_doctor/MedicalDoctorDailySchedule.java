package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.scheduling.DailySchedule;

@Getter
public class MedicalDoctorDailySchedule extends DailySchedule<MedicalDoctorScheduleItem> {
    private MedicalDoctor doctor;

    public MedicalDoctorDailySchedule(Long id, MedicalDoctor doctor) {
        super(id);
        this.doctor = doctor;
    }
}
