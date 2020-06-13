package org.medihub.application.services.clinic_room.get;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchClinicRoomsService implements SearchClinicRoomsQuery {
    private final SearchClinicRoomsPort searchClinicRoomsPort;
    private final LoadClinicRoomSchedulePort loadClinicRoomSchedulePort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<SearchClinicRoomsOutput> searchClinicRooms(String name, Integer number, LocalDate date, LocalTime time) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Long clinicId = clinicAdmin.getClinic().getId();

        List<ClinicRoom> clinicRooms = searchClinicRoomsPort.searchClinicRooms(name, number, date, time, clinicId);
        if (clinicRooms.isEmpty())
            clinicRooms = searchClinicRoomsPort.searchClinicRooms(name, number, date, null, clinicId);
        if (clinicRooms.isEmpty())
            clinicRooms = searchClinicRoomsPort.searchClinicRooms(name, number, null, null, clinicId);
        return mapToOutput(clinicRooms, LocalDateTime.of(date, time));
    }

    private List<SearchClinicRoomsOutput> mapToOutput(List<ClinicRoom> clinicRooms, LocalDateTime datetime){
        return clinicRooms
                .stream()
                .map(clinicRoom -> new SearchClinicRoomsOutput(
                        clinicRoom.getId(),
                        clinicRoom.getName(),
                        clinicRoom.getNumber(),
                        loadClinicRoomSchedulePort.loadClinicRoomSchedule(clinicRoom.getId()),
                        datetime
                ))
                .collect(Collectors.toList());
    }
}
