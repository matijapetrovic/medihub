package org.medihub.web.medical_record;


import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical_record", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {
    private final GetMedicalRecordQuery getMedicalRecordQuery;
    private final GetBloodTypesQuery getBloodTypesQuery;
    private final GetPatientMedicalRecordQuery getPatientMedicalRecordQuery;
    private final ChangeMedicalRecordUseCase changeMedicalRecordUseCase;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<GetMedicalRecordOutput> get() {
        return ResponseEntity.ok(getMedicalRecordQuery.getMedicalRecord());
    }

    @GetMapping("/blood_types")
    ResponseEntity<List<String>> getBloodTypes() { return ResponseEntity.ok(getBloodTypesQuery.getBloodTypes());}

    @GetMapping("/{id}")
    ResponseEntity<GetMedicalRecordOutput> getPatientMedicalRecord(@PathVariable Long id) {
        GetMedicalRecordOutput getMedicalRecordOutput = getPatientMedicalRecordQuery.getPatientMedicalRecord(id);
        return ResponseEntity.ok(getMedicalRecordOutput);
    }

    @PostMapping("/change")
    void changeMedicalRecord(@RequestBody ChangeMedicalReportRequest request) {
        ChangeMedicalRecordUseCase.ChangeMedicalRecordCommand command = createCommand(request);
        changeMedicalRecordUseCase.changeMedicalRecord(command);
    }

    private ChangeMedicalRecordUseCase.ChangeMedicalRecordCommand createCommand(
            ChangeMedicalReportRequest getMedicalRecordOutput) {
        return new ChangeMedicalRecordUseCase.ChangeMedicalRecordCommand(
                getMedicalRecordOutput.getId(),
                getMedicalRecordOutput.getHeight(),
                getMedicalRecordOutput.getWeight(),
                getMedicalRecordOutput.getBloodType(),
                getMedicalRecordOutput.getRhPositive(),
                getMedicalRecordOutput.getLeftDioptry(),
                getMedicalRecordOutput.getRightDioptry()
        );
    }
}
