package org.medihub.persistence.medical_doctor_schedule.schedule_item;

import lombok.*;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleJpaEntity;

import javax.persistence.*;
import java.sql.Time;

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
