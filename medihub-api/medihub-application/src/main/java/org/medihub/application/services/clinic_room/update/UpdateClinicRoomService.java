package org.medihub.application.services.clinic_room.update;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.clinic_room.UpdateClinicRoomUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomFutureScheduleCountPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.clinic_room.ClinicRoom;

@RequiredArgsConstructor
public class UpdateClinicRoomService implements UpdateClinicRoomUseCase {
    private final LoadClinicRoomPort loadClinicRoomPort;
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final GetClinicRoomFutureScheduleCountPort getClinicRoomFutureScheduleCountPort;

    @Override
    public void updateClinicRoom(UpdateClinicRoomCommand updateClinicRoomCommand) throws ForbiddenException {
        ClinicRoom clinicRoom = loadClinicRoomPort.loadClinicRoom(updateClinicRoomCommand.getId());
        if(getClinicRoomFutureScheduleCountPort.countClinicRoomSchedule(clinicRoom.getId()) > 0)
            throw new ForbiddenException();
        clinicRoom = updateClinicRoom(updateClinicRoomCommand, clinicRoom);
        saveClinicRoomPort.saveClinicRoom(clinicRoom);
    }

    private ClinicRoom updateClinicRoom(UpdateClinicRoomCommand updateClinicRoomCommand, ClinicRoom clinicRoom) {
        clinicRoom.setName(updateClinicRoomCommand.getName());
        clinicRoom.setNumber(updateClinicRoomCommand.getNumber());

        return clinicRoom;
    }
}
