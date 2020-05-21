package org.medihub.persistence.clinic_room_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleJpaEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="clinic_room_schedule_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomScheduleItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="schedule_id", referencedColumnName = "id", nullable = false)
    ClinicRoomScheduleJpaEntity schedule;

    @Column(name="time")
    Time time;
}
