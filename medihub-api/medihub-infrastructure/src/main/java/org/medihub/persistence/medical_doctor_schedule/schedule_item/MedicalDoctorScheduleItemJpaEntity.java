package org.medihub.persistence.medical_doctor_schedule.schedule_item;

import lombok.*;

import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.sql.Timestamp;


import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class MedicalDoctorScheduleItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = false)
    MedicalDoctorJpaEntity doctor;

    @Column(name="start_time")
    Timestamp startTime;

    @Column(name="schedule_item_type")
    Integer type;
}
