package org.medihub.web.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.diagnosis.AddDiagnosisUseCase;
import org.medihub.application.ports.incoming.diagnosis.GetDiagnosisOutput;
import org.medihub.application.ports.incoming.diagnosis.GetDiagnosisQuery;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.Drug;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiagnosisController {
    final private AddDiagnosisUseCase addDiagnosisUseCase;
    final private GetDiagnosisQuery getDiagnosisQuery;

    @GetMapping("")
    ResponseEntity<List<GetDiagnosisOutput>> getDiagnosis() {
        return ResponseEntity.ok(getDiagnosisQuery.getDiagnosis());
    }

    @PostMapping("/add")
    Diagnosis add(@RequestBody String name) {
        return addDiagnosisUseCase.addDiagnosis(name);
    }
}
