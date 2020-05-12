package org.medihub.persistence.medical_doctor_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="medical_doctor_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorScheduleJpaEntity {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = false)
    MedicalDoctorJpaEntity doctorJpaEntity;

    @OneToMany
    @JoinColumn(name="schedule_id")
    Set<MedicalDoctorScheduleItemJpaEntity> scheduleItems;
}
