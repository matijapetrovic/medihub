package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoomSchedule;

public interface GetClinicRoomSchedulePort {
    ClinicRoomSchedule getClinicRoomSchedule(Long id);
}
