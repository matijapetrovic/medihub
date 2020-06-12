package org.medihub.domain.medical_nurse;

import lombok.Getter;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.util.Set;

public class MedicalNurseDailySchedule extends DailySchedule<MedicalNurseScheduleItem> {

    @Getter
    private MedicalNurse nurse;

    public MedicalNurseDailySchedule(Long id, MedicalNurse nurse) {
        super(id);
        this.nurse = nurse;
    }
}
