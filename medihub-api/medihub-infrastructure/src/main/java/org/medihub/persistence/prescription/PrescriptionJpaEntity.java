package org.medihub.persistence.prescription;

import lombok.*;
import org.medihub.persistence.drug.DrugJpaEntity;
import org.medihub.persistence.finished_appointment.FinishedAppointmentJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="prescriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="drug_id")
    private DrugJpaEntity drugJpaEntity;

    @OneToOne
    @JoinColumn(name="medical_nurse_id", nullable = true)
    private MedicalNurseJpaEntity medicalNurseJpaEntity;

    @ManyToOne
    @JoinColumn(name="finished_appointment_id")
    private FinishedAppointmentJpaEntity finishedAppointment;

    @Version
    private Long version;
}
