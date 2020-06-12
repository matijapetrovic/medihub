package org.medihub.persistence.medical_nurse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.personal_info.PersonalInfoJpaEntity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="medical_nurse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalNurseJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="personal_info_id", referencedColumnName = "id", nullable = false)
    private PersonalInfoJpaEntity personalInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clinic", referencedColumnName="id", nullable=true)
    private ClinicJpaEntity clinic;

    @Column(name = "working_time_from")
    private Time from;

    @Column(name = "working_time_to")
    private Time to;
}
