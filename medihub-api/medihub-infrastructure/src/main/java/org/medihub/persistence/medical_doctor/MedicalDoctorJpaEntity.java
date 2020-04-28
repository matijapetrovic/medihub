package org.medihub.persistence.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToOne
    @JoinColumn(name = "working_calendar", referencedColumnName="id", nullable=true)
    private WorkingCalendar workingCalendar;

    @ManyToOne
    @JoinColumn(name="clinic", referencedColumnName="id", nullable=true)
    private Clinic clinic;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="appointments",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))
    private Set<Appointment> appointments;

}
