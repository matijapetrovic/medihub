package org.medihub.persistence.clinic_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="clinic_review", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "clinic_id", "patient_id" })
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicReviewJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rating")
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name="clinic_id", referencedColumnName = "id")
    private ClinicJpaEntity clinic;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private PatientJpaEntity patient;

    @Column(name="can_review", nullable = false)
    private Boolean canReview;
}
