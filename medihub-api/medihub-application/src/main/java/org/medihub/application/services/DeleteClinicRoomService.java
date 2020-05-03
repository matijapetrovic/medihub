package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;

@RequiredArgsConstructor
public class DeleteClinicRoomService implements DeleteClinicRoomUseCase {
    private final DeleteClinicRoomPort deleteClinicRoomPort;

    @Override
    public void deleteClinicRoom(String clinicRoomName) {
        deleteClinicRoomPort.deleteClinicRoom(clinicRoomName);
    }
}
