package org.medihub.domain.medical_nurse;

import lombok.Getter;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalTime;

public abstract class MedicalNurseScheduleItem extends DailyScheduleItem {

    @Getter
    private MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type;

    public MedicalNurseScheduleItem(Long id,
                                    LocalTime time,
                                    MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type) {
        super(id, time);
        this.type = type;
    }
}
