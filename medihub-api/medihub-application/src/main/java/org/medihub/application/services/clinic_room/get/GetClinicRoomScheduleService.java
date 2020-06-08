package org.medihub.application.services.clinic_room.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.ClinicRoomScheduleOutput;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomScheduleQuery;
import org.medihub.application.ports.incoming.medical_doctor.schedule.*;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomSchedulePort;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GetClinicRoomScheduleService implements GetClinicRoomScheduleQuery {
    private final GetClinicRoomSchedulePort getClinicRoomSchedulePort;

    @Override
    public GetScheduleOutput getClinicRoomSchedule(Long id) {
        ClinicRoomSchedule clinicRoomSchedule = getClinicRoomSchedulePort.getClinicRoomSchedule(id);
        GetScheduleOutput getScheduleOutput = createOutput(clinicRoomSchedule);
        return getScheduleOutput;
    }

    public GetScheduleOutput createOutput(ClinicRoomSchedule clinicRoomSchedule) {
        Map<String, DailyScheduleOutput> dailySchedules = new HashMap<String, DailyScheduleOutput>();
        DailyScheduleOutput dailyScheduleOutput = null;

        for(LocalDate date : clinicRoomSchedule.getDailySchedules().keySet()) {
            dailyScheduleOutput = new DailyScheduleOutput();
            dailyScheduleOutput.id = clinicRoomSchedule.getDailySchedules().get(date).getId();

            for(ClinicRoomScheduleItem item : clinicRoomSchedule.getDailySchedules().get(date).getScheduleItems()) {
                dailyScheduleOutput.scheduleItems.add(getItem(item));
            }
            dailySchedules.put(date.toString(), dailyScheduleOutput);
        }

        return new GetScheduleOutput(dailySchedules);
    }

    public DailyScheduleItemOutput getItem(ClinicRoomScheduleItem item) {
        return new ClinicRoomScheduleOutput(
                item.getId(),
                item.getTime().toString(),
                ""
        );
    }
}
