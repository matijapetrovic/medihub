package org.medihub.application.ports.incoming.clinic_room;

import org.medihub.application.ports.incoming.medical_doctor.schedule.GetScheduleOutput;

public interface GetClinicRoomScheduleQuery {
    GetScheduleOutput getClinicRoomSchedule(Long id);
}
