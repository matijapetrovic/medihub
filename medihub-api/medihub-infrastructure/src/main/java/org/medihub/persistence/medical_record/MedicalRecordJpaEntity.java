package org.medihub.persistence.medical_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.diagnosis.DiagnosisJpaEntity;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="diagnosis_id", referencedColumnName = "id", nullable=true)
    private Set<DiagnosisJpaEntity> diagnosis;
}
