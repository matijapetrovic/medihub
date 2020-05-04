package org.medihub.persistence.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.drug.DrugJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="prescriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="drug_id")
    private DrugJpaEntity drugJpaEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="medical_nurse_id")
    private MedicalNurseJpaEntity medicalNurseJpaEntity;
}
