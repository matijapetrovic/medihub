package org.medihub.web.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsOutput;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsQuery;
import org.medihub.domain.ClinicRoom;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/clinic-room", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicRoomController {
    private final AddClinicRoomUseCase addClinicRoomUseCase;
    private final DeleteClinicRoomUseCase deleteClinicRoomUseCase;
    private final GetClinicRoomsQuery getClinicRoomsQuery;

    @GetMapping("")
    ResponseEntity<List<GetClinicRoomsOutput>> get() {
        return ResponseEntity.ok(getClinicRoomsQuery.getClinicRooms());
    }

    @PostMapping("/add")
    void add(@RequestBody ClinicRoomRequest request) {
        ClinicRoom cr = new ClinicRoom(null, request.getName());
        addClinicRoomUseCase.addClinicRoom(cr);
    }

    @PostMapping("/delete")
    void delete(@RequestBody ClinicRoomRequest request){
        ClinicRoom cr = new ClinicRoom(null, request.getName());
        deleteClinicRoomUseCase.deleteClinicRoom(cr.getName());
    }
}
