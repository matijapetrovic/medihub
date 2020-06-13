package org.medihub.persistence.clinic_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.personal_info.PersonalInfoJpaEntity;

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

    @ManyToOne
    @JoinColumn(name="clinic", referencedColumnName = "id", nullable = false)
    private ClinicJpaEntity clinic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="personal_info_id", referencedColumnName = "id", nullable = false)
    private PersonalInfoJpaEntity personalInfo;
}
