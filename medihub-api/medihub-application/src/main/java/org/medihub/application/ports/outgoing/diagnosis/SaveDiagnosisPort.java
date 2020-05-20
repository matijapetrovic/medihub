package org.medihub.application.ports.outgoing.diagnosis;

import org.medihub.domain.Diagnosis;

public interface SaveDiagnosisPort {
    Diagnosis saveDiagnosis(Diagnosis diagnosis);
}
