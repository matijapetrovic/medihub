package org.medihub.application.services.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.ScheduleClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic_room_schedule.ScheduleClinicRoomPort;

@RequiredArgsConstructor
public class ScheduleClinicRoomService implements ScheduleClinicRoomUseCase {
    private final ScheduleClinicRoomPort scheduleClinicRoomPort;

    @Override
    public void scheduleClinicRoom(ScheduleClinicRoomCommand command) {
        scheduleClinicRoomPort.scheduleClinicRoom(command.getId(), command.getDate(), command.getTime());
    }
}