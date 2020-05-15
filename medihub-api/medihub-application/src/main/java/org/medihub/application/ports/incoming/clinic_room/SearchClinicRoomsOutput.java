package org.medihub.application.ports.incoming.clinic_room;

import lombok.Value;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
public class SearchClinicRoomsOutput {
    String name;
    Integer number;
    String firstFree;
    ClinicRoomSchedule schedule;

    public SearchClinicRoomsOutput(
            String name,
            Integer number,
            ClinicRoomSchedule schedule,
            LocalDate date){
        this.name = name;
        this.number = number;
        this.schedule = schedule;
        this.firstFree = schedule.getFirstDailySchedule(date).toString();
    }


}
