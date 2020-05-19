package org.medihub.persistence.medical_doctor_schedule;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Getter
@Setter
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class MedicalDoctorScheduleItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id", unique=true, nullable=false)
    Long id;

    @ManyToOne
    @JoinColumn(name="schedule_id", referencedColumnName = "id", nullable = false)
    MedicalDoctorScheduleJpaEntity schedule;

    @Column(name="time")
    Time time;
    @Column(name="schedule_item_type")
    Integer type;
}
