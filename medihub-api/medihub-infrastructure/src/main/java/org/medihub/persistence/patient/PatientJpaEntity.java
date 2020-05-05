package org.medihub.persistence.patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_record.MedicalRecordJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insurance_number" ,unique = true)
    @NotNull
    private String insuranceNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountJpaEntity account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="medical_record_id")
    private MedicalRecordJpaEntity medicalRecordJpaEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="appointments")
    private Set<AppointmentJpaEntity> appointmentJpaEntitySet;
}
