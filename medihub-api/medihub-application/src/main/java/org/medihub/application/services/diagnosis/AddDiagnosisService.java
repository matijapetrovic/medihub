package org.medihub.application.services.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.diagnosis.AddDiagnosisUseCase;
import org.medihub.application.ports.outgoing.diagnosis.SaveDiagnosisPort;
import org.medihub.domain.Diagnosis;

@RequiredArgsConstructor
public class AddDiagnosisService implements AddDiagnosisUseCase {
    private final SaveDiagnosisPort saveDiagnosisPort;

    @Override
    public Diagnosis addDiagnosis(String name) {
        return saveDiagnosisPort.saveDiagnosis(new Diagnosis(null, name));
    }
}
