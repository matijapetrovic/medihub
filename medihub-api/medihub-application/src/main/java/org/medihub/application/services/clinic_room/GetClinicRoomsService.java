package org.medihub.application.services.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsOutput;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsQuery;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.domain.ClinicRoom;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetClinicRoomsService implements GetClinicRoomsQuery, GetClinicRoomUseCase {
    private final GetClinicRoomsPort getClinicRoomsPort;

    @Override
    public List<GetClinicRoomsOutput> getClinicRooms(Long clinicId) {
        return getClinicRoomsPort
                .getClinicRooms(clinicId)
                .stream()
                .map(clinicRoom -> new GetClinicRoomsOutput(clinicRoom.getId(), clinicRoom.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public List<ClinicRoom> getAll() {
        return getClinicRoomsPort.getAll();
    }
}
