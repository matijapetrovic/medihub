package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import lombok.*;
import org.medihub.persistence.appointment.AbstractAppointmentJpaEntity;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="medical_doctor_appointment_schedule_item")
@Data
@NoArgsConstructor
public class MedicalDoctorAppointmentScheduleJpaItem extends MedicalDoctorScheduleItemJpaEntity {

    @OneToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "id", nullable = false)
    AbstractAppointmentJpaEntity appointment;

    public MedicalDoctorAppointmentScheduleJpaItem(
            Long id,
            MedicalDoctorJpaEntity doctor,
            Timestamp timestamp,
            Integer type,
            AbstractAppointmentJpaEntity appointment) {
        super(id, doctor, timestamp, type);
        this.appointment = appointment;
    }
}
