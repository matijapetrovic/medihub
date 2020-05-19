package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalTime;

public abstract class MedicalDoctorScheduleItem extends DailyScheduleItem {
    @Getter
    private MedicalDoctorScheduleItemType type;

    public MedicalDoctorScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type) {
        super(id, time);
        this.type = type;
    }

    public enum MedicalDoctorScheduleItemType {
        APPOINTMENT(1), OPERATION(2), VACATION(3), LEAVE(4);
        @Getter
        private int ordinal;
        MedicalDoctorScheduleItemType(int ordinal) {
            this.ordinal = ordinal;
        }
    }
}
