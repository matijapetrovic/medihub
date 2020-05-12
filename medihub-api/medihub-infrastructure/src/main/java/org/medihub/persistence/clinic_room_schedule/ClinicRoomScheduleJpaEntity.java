package org.medihub.persistence.clinic_room_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="clinic_room_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomScheduleJpaEntity {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id", nullable = false)
    ClinicRoomJpaEntity clinicRoomJpaEntity;

    @OneToMany
    @JoinColumn(name="schedule_id")
    Set<ClinicRoomScheduleItemJpaEntity> scheduleItems;
}
