package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;
import org.medihub.persistence.prescription.PrescriptionJpaEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient", referencedColumnName="id", nullable=true)
    private PatientJpaEntity patientJpaEntity;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private double duration;

    @ManyToOne
    @JoinColumn(name="clinic_room", referencedColumnName="id", nullable=true)
    private ClinicRoomJpaEntity clinicRoomJpaEntity;

    @ManyToOne
    @JoinColumn(name="appointment_type", referencedColumnName="id", nullable=true)
    private AppointmentTypeJpaEntity appointmentTypeJpaEntity;

    @OneToMany
    @JoinColumn(name="prescriptions", referencedColumnName="id", nullable=true)
    private Set<PrescriptionJpaEntity> prescriptions;
}
