package org.medihub.domain.medical_doctor;

import java.time.LocalTime;

public class MedicalDoctorVacationScheduleItem extends MedicalDoctorScheduleItem {
    public MedicalDoctorVacationScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type) {
        super(id, time, type);
    }
}
