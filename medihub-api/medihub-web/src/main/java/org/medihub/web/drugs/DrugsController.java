package org.medihub.web.drugs;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.drugs.AddDrugUseCase;
import org.medihub.domain.Drug;
import org.medihub.web.clinic_room.ClinicRoomRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/drugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugsController {
    final private AddDrugUseCase addDrugUseCase;

    @PostMapping("/add")
    Drug add(@RequestBody String name) {
        return addDrugUseCase.addDrug(name);
    }
}
