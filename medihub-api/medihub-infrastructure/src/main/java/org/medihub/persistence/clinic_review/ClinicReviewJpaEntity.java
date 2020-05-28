package org.medihub.persistence.clinic_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.finished_appointment.FinishedAppointmentJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="clinic_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicReviewJpaEntity {
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
    @JoinColumn(name="clinic_id", referencedColumnName = "id")
    ClinicJpaEntity clinic;
}
