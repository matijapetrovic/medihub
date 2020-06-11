package org.medihub.persistence.medical_doctor_schedule.operation_schedule_item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment.OperationJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.predefined_appointment.PredefinedAppointmentJpaEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name="medical_doctor_operation_schedule_item")
public class MedicalDoctorOperationScheduleJpaItem extends MedicalDoctorScheduleItemJpaEntity {

    @OneToOne
    @JoinColumn(name="operationt_id", referencedColumnName = "id", nullable = false)
    OperationJpaEntity operation;

    public MedicalDoctorOperationScheduleJpaItem() {
        super();
    }

    public MedicalDoctorOperationScheduleJpaItem(
            Long id,
            MedicalDoctorJpaEntity doctor,
            Timestamp time,
            Integer type,
            OperationJpaEntity operation) {
        super(id, doctor, time, type);
        this.operation = operation;
    }
}
