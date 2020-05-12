package org.medihub.persistence.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="medical_doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorJpaEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountJpaEntity account;

    @ManyToOne
    @JoinColumn(name="clinic", referencedColumnName="id")
    private ClinicJpaEntity clinic;

    @Column(name = "working_time_from")
    private Time from;

    @Column(name = "working_time_to")
    private Time to;

    @ManyToOne
    @JoinColumn(name="specialization", referencedColumnName = "id", nullable = false)
    private AppointmentTypeJpaEntity specialization;
}
