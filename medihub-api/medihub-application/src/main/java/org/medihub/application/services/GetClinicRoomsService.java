package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsOutput;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsQuery;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.domain.ClinicAdmin;
import org.medihub.domain.identity.Account;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetClinicRoomsService implements GetClinicRoomsQuery {
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    @Override
    public List<GetClinicRoomsOutput> getClinicRooms() {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdmin(authenticated.getId());
        Long clinicId = clinicAdmin.getClinic().getId();
        return getClinicRoomsPort
                .getClinicRooms(clinicId)
                .stream()
                .map(clinicRoom -> new GetClinicRoomsOutput(clinicRoom.getName()))
                .collect(Collectors.toList());
    }
}
