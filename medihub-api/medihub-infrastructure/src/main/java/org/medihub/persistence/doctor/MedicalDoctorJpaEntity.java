package org.medihub.persistence.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.medihub.domain.Appointment;
import org.medihub.domain.Clinic;
import org.medihub.domain.WorkingCalendar;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorJpaEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "working_calendar")
    private WorkingCalendar workingCalendar;

    @Column(name="clinic")
    @OneToMany
    private Clinic clinic;

    @OneToMany
    @Column(name = "appointments")
    private Set<Appointment> appointments;

}
