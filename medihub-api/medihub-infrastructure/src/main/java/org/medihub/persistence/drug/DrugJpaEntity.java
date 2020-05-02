package org.medihub.persistence.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drug")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugJpaEntity {

    @Id
    @GeneratedValue
    Long id;
}
