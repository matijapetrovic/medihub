package org.medihub.persistence.medical_doctor_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="medical_doctor_schedule_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDoctorScheduleItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="schedule_id", referencedColumnName = "id", nullable = false)
    MedicalDoctorScheduleJpaEntity schedule;

    @Column(name="time")
    Time time;
    @Column(name="schedule_item_type")
    Integer type;
}
