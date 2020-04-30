package org.medihub.persistence.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prescriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionJpaEntity {

    @Id
    @GeneratedValue
    Long id;
}
