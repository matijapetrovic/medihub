package org.medihub.persistence.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "working_calendar", referencedColumnName="id", nullable=true)
    private WorkingCalendarJpaEntity workingCalendarJpaEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clinic", referencedColumnName="id", nullable=true)
    private ClinicJpaEntity clinic;

    @Column(name = "working_time_from")
    private Time from;

    @Column(name = "working_time_to")
    private Time to;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private Set<AppointmentJpaEntity> appointments;

}
