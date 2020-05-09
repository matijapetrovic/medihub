package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic.GetClinicByIDPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.Clinic;
import org.medihub.domain.ClinicRoom;

@RequiredArgsConstructor
public class AddClinicRoomService implements AddClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final GetClinicByIDPort loadClinicPort;

    @Override
    public void addClinicRoom(AddClinicRoomCommand command) {
        Clinic clinic = loadClinicPort.getByID(command.getClinicId());
        ClinicRoom room = new ClinicRoom(null, command.getName(), clinic);
        saveClinicRoomPort.saveClinicRoom(room);
    }
}
