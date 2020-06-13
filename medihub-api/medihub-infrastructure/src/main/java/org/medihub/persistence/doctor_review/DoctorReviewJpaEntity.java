package org.medihub.persistence.doctor_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.finished_appointment.FinishedAppointmentJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doctor_review", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "doctor_id", "patient_id" })
})
public class DoctorReviewJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rating")
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private MedicalDoctorJpaEntity doctor;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private PatientJpaEntity patient;

    @Column(name="can_review", nullable = false)
    private Boolean canReview;
}