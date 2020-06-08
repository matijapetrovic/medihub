package org.medihub.persistence.medical_doctor_schedule;

import lombok.*;
import org.medihub.domain.appointment.Appointment;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
            MedicalDoctorJpaEntity doctor,
            Timestamp timestamp,
            Integer type,
        AppointmentJpaEntity appointment) {
        super(id, doctor, timestamp, type);
        this.appointment = appointment;
    }
}
