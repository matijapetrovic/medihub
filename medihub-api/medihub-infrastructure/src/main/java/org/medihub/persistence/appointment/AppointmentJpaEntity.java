package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="appointment")
@Data
@NoArgsConstructor
public class AppointmentJpaEntity extends  AbstractAppointmentJpaEntity {
    public AppointmentJpaEntity(Long id,
                                Timestamp startTime,
                                PatientJpaEntity patient,
                                MedicalDoctorJpaEntity doctor,
                                ClinicRoomJpaEntity clinicRoom,
                                BigDecimal price) {
        super(id, startTime, patient, doctor, clinicRoom, price);
    }
}
