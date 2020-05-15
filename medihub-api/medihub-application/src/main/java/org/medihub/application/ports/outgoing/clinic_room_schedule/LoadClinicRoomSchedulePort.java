package org.medihub.application.ports.outgoing.clinic_room_schedule;

import org.medihub.domain.clinic_room.ClinicRoomSchedule;

public interface LoadClinicRoomSchedulePort {
    ClinicRoomSchedule loadClinicRoomSchedule(Long clinicRoomId);
}
