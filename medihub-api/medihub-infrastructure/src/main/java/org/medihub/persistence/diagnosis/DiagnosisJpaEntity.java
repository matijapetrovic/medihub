package org.medihub.persistence.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="diagnosis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;
}
