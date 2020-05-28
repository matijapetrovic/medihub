package org.medihub.persistence.doctor_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.finished_appointment.FinishedAppointmentJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="doctor_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReviewJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rating")
    BigDecimal rating;

    @OneToOne
    @JoinColumn(name="appointment_id", unique = true, referencedColumnName = "id")
    FinishedAppointmentJpaEntity appointment;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    MedicalDoctorJpaEntity doctor;
}