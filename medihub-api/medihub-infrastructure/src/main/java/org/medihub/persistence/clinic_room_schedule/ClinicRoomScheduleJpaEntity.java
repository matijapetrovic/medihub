package org.medihub.persistence.clinic_room_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="clinic_room_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomScheduleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="clinic_room_id", referencedColumnName = "id", nullable = false)
    ClinicRoomJpaEntity clinicRoom;

    @Column(name="date")
    Date date;
}
