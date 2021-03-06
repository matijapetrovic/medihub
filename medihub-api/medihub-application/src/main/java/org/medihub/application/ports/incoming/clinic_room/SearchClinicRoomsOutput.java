package org.medihub.application.ports.incoming.clinic_room;

import lombok.Value;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class SearchClinicRoomsOutput {
    Long id;
    String name;
    Integer number;
    String freeDate;
    String freeTime;
    List<ClinicRoomSimpleScheduleItem> simpleSchedule;
    ClinicRoomSchedule schedule;

    public SearchClinicRoomsOutput(
            Long id,
            String name,
            Integer number,
            ClinicRoomSchedule schedule,
            LocalDateTime datetime){
        this.id = id;
        this.name = name;
        this.number = number;
        this.schedule = schedule;
        LocalDateTime first = schedule.getFirstDailySchedule(datetime);
        this.freeDate = first == null ? "N/A" : first.toLocalDate().toString();
        this.freeTime = first == null ? "N/A" : first.toLocalTime().toString();
        this.simpleSchedule = mapToList(schedule.getDailySchedules());
    }

    private List<ClinicRoomSimpleScheduleItem> mapToList(Map<LocalDate,DailySchedule<ClinicRoomScheduleItem>> dailySchedule) {
        List<ClinicRoomSimpleScheduleItem> simpleScheduleItems = new ArrayList<>();
        dailySchedule.forEach((localDate, dailyScheduleItems) ->
                    dailyScheduleItems
                            .getScheduleItems().forEach(item -> simpleScheduleItems.add(new ClinicRoomSimpleScheduleItem(
                            localDate.toString(),
                            item.getTime().toString()))
                ));

        return simpleScheduleItems;
    }
}
