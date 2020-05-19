package org.medihub.persistence.finished_appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.diagnosis.DiagnosisJpaEntity;
import org.medihub.persistence.prescription.PrescriptionJpaEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="finished_appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinishedAppointmentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "id")
    private AppointmentJpaEntity appointment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PrescriptionJpaEntity> prescriptions;

    @OneToOne
    @JoinColumn(name="diagnosis_id", referencedColumnName = "id")
    private DiagnosisJpaEntity diagnosis;
}
