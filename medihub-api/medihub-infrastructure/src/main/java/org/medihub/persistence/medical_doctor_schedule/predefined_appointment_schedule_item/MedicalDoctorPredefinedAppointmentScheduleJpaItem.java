package org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item;

import lombok.Getter;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.predefined_appointment.PredefinedAppointmentJpaEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name="medical_doctor_predefined_appointment_schedule_item")
public class MedicalDoctorPredefinedAppointmentScheduleJpaItem  extends MedicalDoctorScheduleItemJpaEntity {

    @OneToOne
    @JoinColumn(name="predefined_appointment_id", referencedColumnName = "id", nullable = false)
    PredefinedAppointmentJpaEntity predefinedAppointment;

    public MedicalDoctorPredefinedAppointmentScheduleJpaItem() {
        super();
    }

    public MedicalDoctorPredefinedAppointmentScheduleJpaItem(
            Long id,
            MedicalDoctorJpaEntity doctor,
            Timestamp time,
            Integer type,
            PredefinedAppointmentJpaEntity predefinedAppointment) {
        super(id, doctor, time, type);
        this.predefinedAppointment = predefinedAppointment;
    }

}
