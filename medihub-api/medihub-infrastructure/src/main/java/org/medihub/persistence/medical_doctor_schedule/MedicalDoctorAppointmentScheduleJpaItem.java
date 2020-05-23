package org.medihub.persistence.medical_doctor_schedule;

import lombok.*;
import org.medihub.domain.appointment.Appointment;
import org.medihub.persistence.appointment.AppointmentJpaEntity;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name="medical_doctor_appointment_schedule_item")
@Data
@NoArgsConstructor
public class MedicalDoctorAppointmentScheduleJpaItem extends  MedicalDoctorScheduleItemJpaEntity {

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
