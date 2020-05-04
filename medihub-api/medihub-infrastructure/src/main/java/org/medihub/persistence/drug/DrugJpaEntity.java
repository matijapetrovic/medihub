package org.medihub.persistence.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="drug")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
