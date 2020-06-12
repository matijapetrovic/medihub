package org.medihub.application.services.clinic_room.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.GetAllClinicRoomsUseCase;
import org.medihub.application.ports.outgoing.clinic_room.GetAllClinicRoomsPort;
import org.medihub.domain.clinic_room.ClinicRoom;

import java.util.List;

@RequiredArgsConstructor
public class GetAllClinicRoomsService implements GetAllClinicRoomsUseCase {
    private final GetAllClinicRoomsPort getAllClinicRoomsPort;

    @Override
    public List<ClinicRoom> getAll() {
        return getAllClinicRoomsPort.getAll();
    }
}
