package org.medihub.web.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.prescription.AcceptPrescriptionUseCase;
import org.medihub.application.ports.incoming.prescription.GetPrescriptionsOutput;
import org.medihub.application.ports.incoming.prescription.GetPrescriptionsQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescriptionController {
    private final GetPrescriptionsQuery getPrescriptionsQuery;
    private final AcceptPrescriptionUseCase acceptPrescriptionUseCase;

    @GetMapping("")
    ResponseEntity<List<GetPrescriptionsOutput>> getPrescriptions() {
        return ResponseEntity.ok(getPrescriptionsQuery.getPrescriptions());
    }

    @PostMapping("/accept")
    void acceptPrescription(@RequestBody Long id) {
        acceptPrescriptionUseCase.acceptPrescription(id);
    }
}