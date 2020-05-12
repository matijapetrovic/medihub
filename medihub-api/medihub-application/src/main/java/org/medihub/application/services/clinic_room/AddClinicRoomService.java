package org.medihub.application.services.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic.GetClinicByIDPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic_room.ClinicRoom;

@RequiredArgsConstructor
public class AddClinicRoomService implements AddClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final GetClinicByIDPort loadClinicPort;

    @Override
    public void addClinicRoom(AddClinicRoomCommand command) {
        Clinic clinic = loadClinicPort.getByID(command.getClinicId());
        ClinicRoom room = new ClinicRoom(null, command.getName(), command.getNumber(), clinic);
        saveClinicRoomPort.saveClinicRoom(room);
    }
}
