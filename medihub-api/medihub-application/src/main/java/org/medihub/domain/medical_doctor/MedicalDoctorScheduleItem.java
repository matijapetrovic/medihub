package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

public abstract class MedicalDoctorScheduleItem extends DailyScheduleItem {
    @Getter
    private MedicalDoctorScheduleItemType type;

    public MedicalDoctorScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type) {
        super(id, time);
        this.type = type;
    }

    public enum MedicalDoctorScheduleItemType {
        APPOINTMENT(1), OPERATION(2), VACATION(3), LEAVE(4), PREDEFINED_APPOINTMENT(5);
        @Getter
        private int ordinal;
        MedicalDoctorScheduleItemType(int ordinal) {
            this.ordinal = ordinal;
        }
        public static Optional<MedicalDoctorScheduleItemType> valueOf(int value) {
            return Arrays.stream(values())
                    .filter(type -> type.ordinal == value)
                    .findFirst();
        }
    }
}
