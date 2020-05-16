package org.medihub.persistence.medical_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.diagnosis.DiagnosisJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="medical_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id", nullable = false)
    private PatientJpaEntity patient;

    @Column(name="height")
    private Integer height;

    @Column(name="weight")
    private Integer weight;

    @Column(name="blood_type")
    private String bloodType;

    @Column(name="rh_positive")
    private Boolean rhPositive;

    @Column(name="left_dioptry")
    private Double leftDioptry;

    @Column(name="right_dioptry")
    private Double rightDioptry;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="diagnosis_id", referencedColumnName = "id")
    private Set<DiagnosisJpaEntity> diagnosis;

    @ElementCollection
    @CollectionTable(name="medical_record_allergy_mapping",
            joinColumns = {@JoinColumn(name="medical_record_id", referencedColumnName = "id")})
    @MapKeyColumn(name="allergy_name")
    @Column(name="allergy_level")
    private Map<String, String> allergies;

}
