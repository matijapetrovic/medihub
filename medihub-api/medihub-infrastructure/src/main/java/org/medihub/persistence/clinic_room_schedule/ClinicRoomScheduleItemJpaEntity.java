package org.medihub.persistence.clinic_room_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    Long id;

    @Column(name="date")
    Date date;
    @Column(name="time")
    Time time;
}
