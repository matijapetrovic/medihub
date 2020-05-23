package org.medihub.persistence.medical_doctor_schedule;

import org.medihub.persistence.appointment.AppointmentJpaEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="medical_doctor_vacation_schedule_item")
public class MedicalDoctorVacationScheduleJpaItem extends  MedicalDoctorScheduleItemJpaEntity{
    public MedicalDoctorVacationScheduleJpaItem() {
        super();
    }

    public MedicalDoctorVacationScheduleJpaItem(
            Long id,
            MedicalDoctorScheduleJpaEntity schedule,
            Time time,
            Integer type) {
        super(id, schedule, time, type);
    }
}
