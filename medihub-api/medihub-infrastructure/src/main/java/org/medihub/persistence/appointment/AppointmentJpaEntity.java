package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name="time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id", nullable = false)
    private PatientJpaEntity patient;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private MedicalDoctorJpaEntity doctor;

    @ManyToOne
    @JoinColumn(name="clinic_room_id", referencedColumnName = "id", nullable = false)
    private ClinicRoomJpaEntity clinicRoom;
}
