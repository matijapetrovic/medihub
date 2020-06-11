package org.medihub.persistence.appointment_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="appointment_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor", referencedColumnName = "id", nullable = false)
    private MedicalDoctorJpaEntity medicalDoctorJpaEntity;

    @ManyToOne
    @JoinColumn(name="patient", referencedColumnName = "id", nullable = false)
    private PatientJpaEntity patientJpaEntity;

    @Column(name="price", nullable = false)
    private BigDecimal money;

    @Column(name="start_time", nullable = false)
    private Timestamp startTime;
}
