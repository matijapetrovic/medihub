package org.medihub.application.ports.outgoing.diagnosis;

import org.medihub.domain.Diagnosis;

import java.util.List;

public interface GetDiagnosisPort {
    public List<Diagnosis> getDiagnosis();
}
