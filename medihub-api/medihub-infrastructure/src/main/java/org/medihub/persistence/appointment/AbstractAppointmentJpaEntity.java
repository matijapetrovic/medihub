package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract  class AbstractAppointmentJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "start_time")
    private Timestamp startTime;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private PatientJpaEntity patient;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private MedicalDoctorJpaEntity doctor;

    @ManyToOne
    @JoinColumn(name="clinic_room_id", referencedColumnName = "id")
    private ClinicRoomJpaEntity clinicRoom;
}
