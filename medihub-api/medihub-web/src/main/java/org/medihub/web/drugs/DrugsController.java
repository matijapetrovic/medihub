package org.medihub.web.drugs;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.drugs.AddDrugUseCase;
import org.medihub.application.ports.incoming.drugs.GetDrugsOutput;
import org.medihub.application.ports.incoming.drugs.GetDrugsQuery;
import org.medihub.domain.Drug;
import org.medihub.web.clinic_room.ClinicRoomRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/drugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugsController {
    final private AddDrugUseCase addDrugUseCase;
    final private GetDrugsQuery getDrugsQuery;

    @GetMapping("")
    public ResponseEntity<List<GetDrugsOutput>> getDrugs() {
        return ResponseEntity.ok(getDrugsQuery.getDrugs());
    }

    @PostMapping("/add")
    public Drug add(@RequestBody String name) {
        return addDrugUseCase.addDrug(name);
    }
}
