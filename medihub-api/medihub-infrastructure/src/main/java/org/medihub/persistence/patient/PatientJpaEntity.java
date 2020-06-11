package org.medihub.persistence.patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_record.MedicalRecordJpaEntity;
import org.medihub.persistence.personal_info.PersonalInfoJpaEntity;

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

    @OneToOne
    @JoinColumn(name="personal_info_id", referencedColumnName = "id", nullable = false)
    private PersonalInfoJpaEntity personalInfo;
}
