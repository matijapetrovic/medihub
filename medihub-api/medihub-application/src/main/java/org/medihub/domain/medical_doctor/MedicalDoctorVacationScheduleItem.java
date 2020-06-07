package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MedicalDoctorVacationScheduleItem extends MedicalDoctorScheduleItem {
    private LocalDate endDate;

    public MedicalDoctorVacationScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type, LocalDate endDate) {
        super(id, time, type);
        this.endDate = endDate;
    }
}
