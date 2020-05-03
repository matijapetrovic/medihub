package org.medihub.persistence.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="diagnosis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisJpaEntity {

    @Id
    @GeneratedValue
    Long id;
}
