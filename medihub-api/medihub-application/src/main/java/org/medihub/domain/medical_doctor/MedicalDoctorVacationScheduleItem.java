package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class MedicalDoctorVacationScheduleItem extends MedicalDoctorScheduleItem {
    private Date endDate;

    public MedicalDoctorVacationScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type, Date endDate) {
        super(id, time, type);
        this.endDate = endDate;
    }
}
