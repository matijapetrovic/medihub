package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.appointment.Operation;

import java.time.LocalTime;

@Getter
@Setter
public class MedicalDoctorOperationScheduleItem extends MedicalDoctorScheduleItem {
    private Operation operation;

    public MedicalDoctorOperationScheduleItem(Long id, LocalTime time, MedicalDoctorScheduleItemType type, Operation operation) {
        super(id, time, type);
        this.operation = operation;
    }
}
