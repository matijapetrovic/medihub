package org.medihub.persistence.medical_doctor_schedule;

import lombok.Getter;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name="medical_doctor_vacation_schedule_item")
public class MedicalDoctorVacationScheduleJpaItem extends MedicalDoctorScheduleItemJpaEntity{
    @Column(name="end_date")
    Timestamp endDate;

    public MedicalDoctorVacationScheduleJpaItem() {
        super();
    }

    public MedicalDoctorVacationScheduleJpaItem(
            Long id,
            MedicalDoctorJpaEntity doctor,
            Timestamp timestamp,
            Integer type,
            Timestamp endDate) {
        super(id, doctor, timestamp, type);
        this.endDate = endDate;
    }
}
