package org.medihub.persistence.clinic_room_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @JoinColumn(name="clinic_room_id", referencedColumnName = "id", nullable = false)
    ClinicRoomJpaEntity clinicRoom;

    @Column(name="start_time")
    Timestamp startTime;
}
