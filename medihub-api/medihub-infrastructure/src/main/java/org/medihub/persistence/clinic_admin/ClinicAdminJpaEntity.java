package org.medihub.persistence.clinic_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="clinic", referencedColumnName = "id")
    private ClinicJpaEntity clinic;

}
