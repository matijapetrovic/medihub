package org.medihub.web.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.diagnosis.AddDiagnosisUseCase;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.Drug;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiagnosisController {
    final private AddDiagnosisUseCase addDiagnosisUseCase;

    @PostMapping("/add")
    Diagnosis add(@RequestBody String name) {
        return addDiagnosisUseCase.addDiagnosis(name);
    }
}
