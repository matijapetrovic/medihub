package org.medihub.application.services.clinic_room.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AlreadyExistException;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic_room.ClinicRoom;

@RequiredArgsConstructor
public class AddClinicRoomService implements AddClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final LoadClinicPort loadClinicPort;

    @Override
    public void addClinicRoom(AddClinicRoomCommand command) throws AlreadyExistException {
        Clinic clinic = loadClinicPort.loadClinic(command.getClinicId());
        ClinicRoom room = new ClinicRoom(null, command.getName(), command.getNumber(), clinic, false);
        saveClinicRoomPort.saveClinicRoom(room);
    }
}
