package org.medihub.persistence.clinic_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.domain.identity.Account;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="clinic_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicAdminJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="account")
    private AccountJpaEntity account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clinic", referencedColumnName = "id", nullable=true)
    private ClinicJpaEntity clinic;

}
