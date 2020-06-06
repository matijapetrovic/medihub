package org.medihub.persistence.medical_doctor_schedule.vacation_item;

import lombok.Getter;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Getter
@Entity
@Table(name="medical_doctor_vacation_schedule_item")
public class MedicalDoctorVacationScheduleJpaItem extends MedicalDoctorScheduleItemJpaEntity {
    @Column(name="end_date")
    Date endDate;

    public MedicalDoctorVacationScheduleJpaItem() {
        super();
    }

    public MedicalDoctorVacationScheduleJpaItem(
            Long id,
            MedicalDoctorScheduleJpaEntity schedule,
            Time time,
            Integer type,
            Date endDate) {
        super(id, schedule, time, type);
        this.endDate = endDate;
    }
}
