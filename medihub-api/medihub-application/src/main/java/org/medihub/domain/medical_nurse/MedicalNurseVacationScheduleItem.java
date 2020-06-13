package org.medihub.domain.medical_nurse;

import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MedicalNurseVacationScheduleItem extends MedicalNurseScheduleItem {
    private LocalDate endDate;

    public MedicalNurseVacationScheduleItem(Long id,
                                            LocalTime time,
                                            MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type,
                                            LocalDate endDate) {
        super(id, time, type);
        this.endDate = endDate;
    }
}
