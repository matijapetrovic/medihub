package org.medihub.web.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicRoomUseCase;
import org.medihub.domain.ClinicRoom;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/clinic-room", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicRoomController {
    private final AddClinicRoomUseCase addClinicRoomUseCase;

    @PostMapping("/add")
    void add(@RequestBody ClinicRoomRequest request) {
        ClinicRoom cr = new ClinicRoom(request.getName());
        addClinicRoomUseCase.addClinicRoom(cr);
    }
}
