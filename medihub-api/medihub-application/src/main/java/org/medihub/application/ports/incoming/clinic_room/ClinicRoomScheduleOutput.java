package org.medihub.application.ports.incoming.clinic_room;

import org.medihub.application.ports.incoming.medical_doctor.schedule.DailyScheduleItemOutput;

public class ClinicRoomScheduleOutput extends DailyScheduleItemOutput {
    public ClinicRoomScheduleOutput(Long id, String time, String type) {
        super(id, time, type);
    }
}
