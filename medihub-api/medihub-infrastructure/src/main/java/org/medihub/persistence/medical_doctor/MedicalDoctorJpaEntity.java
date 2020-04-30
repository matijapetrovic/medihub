package org.medihub.persistence.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.domain.Appointment;
import org.medihub.domain.Clinic;
import org.medihub.domain.WorkingCalendar;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="medical_doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorJpaEntity {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "working_calendar", referencedColumnName="id", nullable=true)
    private WorkingCalendarJpaEntity workingCalendarJpaEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clinic", referencedColumnName="id", nullable=true)
    private ClinicJpaEntity clinicJpaEntity;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="appointments",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))
    private Set<AppointmentJpaEntity> appointments;

}
