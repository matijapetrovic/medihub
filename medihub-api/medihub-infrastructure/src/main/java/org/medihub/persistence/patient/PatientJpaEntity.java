package org.medihub.persistence.patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private String insuranceNumber;

    @OneToOne
    @JoinColumn(name="account_id")
    private AccountJpaEntity account;
}
