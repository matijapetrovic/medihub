package org.medihub.application.ports.outgoing.diagnosis;

import org.medihub.domain.Diagnosis;

public interface GetDiagnosisByIdPort {
    public Diagnosis getDiagnosisById(Long id);
}
