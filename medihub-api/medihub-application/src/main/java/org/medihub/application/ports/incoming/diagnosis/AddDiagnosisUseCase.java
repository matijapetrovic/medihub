package org.medihub.application.ports.incoming.diagnosis;

import org.medihub.domain.Diagnosis;

public interface AddDiagnosisUseCase {
    Diagnosis addDiagnosis(String name);
}
