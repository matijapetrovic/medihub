package org.medihub.web.security.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.ClinicRoomUseCase;
import org.medihub.domain.ClinicRoom;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/clinic-room", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicRoomController {
    private final ClinicRoomUseCase clinicRoomUseCase;

    @PostMapping("/add")
    void add(@RequestBody ClinicRoom request) {
        clinicRoomUseCase.addClinicRoom(request);
    }

}
