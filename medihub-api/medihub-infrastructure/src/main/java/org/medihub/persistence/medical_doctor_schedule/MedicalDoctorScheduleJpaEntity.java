package org.medihub.persistence.medical_doctor_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="medical_doctor_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorScheduleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = false)
    MedicalDoctorJpaEntity doctor;

    @Column(name="date")
    Date date;
}
