package org.medihub.persistence.medical_doctor_schedule.appointment_item;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleJpaEntity;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name="medical_doctor_appointment_schedule_item")
@Data
@NoArgsConstructor
public class MedicalDoctorAppointmentScheduleJpaItem extends MedicalDoctorScheduleItemJpaEntity {

    @OneToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "id", nullable = false)
    AppointmentJpaEntity appointment;

    public MedicalDoctorAppointmentScheduleJpaItem(
            Long id,
            MedicalDoctorScheduleJpaEntity schedule,
            Time time,
            Integer type,
            AppointmentJpaEntity appointment) {
        super(id, schedule, time, type);
        this.appointment = appointment;
    }
}
