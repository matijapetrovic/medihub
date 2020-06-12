package org.medihub.application.services.clinic_room.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.GetRoomAvailableTimeQuery;
import org.medihub.application.ports.outgoing.clinic_room.LoadRoomDailySchedulePort;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetRoomAvailableTimesService implements GetRoomAvailableTimeQuery {
    private final Integer WORKING_HOURS = 24;
    private final LoadRoomDailySchedulePort loadRoomDailySchedulePort;

    @Override
    public List<String> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date) {
        DailySchedule<ClinicRoomScheduleItem> dailySchedule =
                loadRoomDailySchedulePort.loadClinicRoomDailySchedule(clinicRoomId, date);

        return buildAvailableTimesList(dailySchedule);
    }

    private List<String> buildAvailableTimesList(DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
        LocalTime currentTime = LocalTime.parse("00:00");
        List<String> availableTimes = new ArrayList<>();
        for (int i = 0; i < WORKING_HOURS; i++) {
            if (dailySchedule.isAvailable(currentTime))
                availableTimes.add(currentTime.toString());
            currentTime = currentTime.plusHours(1L);
        }

        return availableTimes;
    }
}
