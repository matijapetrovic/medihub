package org.medihub.persistence.medical_doctor_schedule;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="medical_doctor_vacation_schedule_item")
public class MedicalDoctorVacationScheduleJpaItem extends  MedicalDoctorScheduleItemJpaEntity{
    public MedicalDoctorVacationScheduleJpaItem() {
        super();
    }
}
