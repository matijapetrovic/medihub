package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.persistence.account.AuthorityJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationJpaEntity extends  AbstractAppointmentJpaEntity{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="operation_doctor",
            joinColumns = @JoinColumn(name="operation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="doctor_id", referencedColumnName = "id"))
    private List<MedicalDoctorJpaEntity> presentDoctors;

    public OperationJpaEntity(
            Long id,
            Timestamp startTime,
            PatientJpaEntity patient,
            MedicalDoctorJpaEntity doctor,
            ClinicRoomJpaEntity clinicRoom,
            List<MedicalDoctorJpaEntity> presentDoctors,
            BigDecimal price
    ) {
        super(id, startTime, patient, doctor, clinicRoom, price);
        this.presentDoctors = presentDoctors;
    }
}
