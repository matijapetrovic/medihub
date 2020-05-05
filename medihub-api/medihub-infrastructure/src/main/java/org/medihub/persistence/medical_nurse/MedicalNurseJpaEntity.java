package org.medihub.persistence.medical_nurse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="medical_nirse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalNurseJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountJpaEntity account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "working_calendar", referencedColumnName="id", nullable=true)
    private WorkingCalendarJpaEntity workingCalendarJpaEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clinic", referencedColumnName="id", nullable=true)
    private ClinicJpaEntity clinic;
}
