package org.medihub.application.services.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.SearchClinicRoomsOutput;
import org.medihub.application.ports.incoming.clinic_room.SearchClinicRoomsQuery;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.SearchClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.clinic_room.ClinicRoom;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchClinicRoomsService implements SearchClinicRoomsQuery {
    private final SearchClinicRoomsPort searchClinicRoomsPort;
    private final LoadClinicRoomSchedulePort loadClinicRoomSchedulePort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<SearchClinicRoomsOutput> searchClinicRooms(String name, Integer number, LocalDate date) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Long clinicId = clinicAdmin.getClinic().getId();
        return mapToOutput(searchClinicRoomsPort.searchClinicRooms(name, number, date, clinicId), date);
    }

    private List<SearchClinicRoomsOutput> mapToOutput(List<ClinicRoom> clinicRooms, LocalDate date){
        return clinicRooms
                .stream()
                .map(clinicRoom -> new SearchClinicRoomsOutput(
                        clinicRoom.getName(),
                        clinicRoom.getNumber(),
                        loadClinicRoomSchedulePort.loadClinicRoomSchedule(clinicRoom.getId()),
                        date
                ))
                .collect(Collectors.toList());
    }
}
