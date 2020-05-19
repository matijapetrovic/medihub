package org.medihub.web.medical_record;


import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordOutput;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical_record", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {
    private final GetMedicalRecordQuery getMedicalRecordQuery;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<GetMedicalRecordOutput> get() {
        return ResponseEntity.ok(getMedicalRecordQuery.getMedicalRecord());
    }
}
