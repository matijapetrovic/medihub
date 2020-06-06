package org.medihub.application.ports.incoming.diagnosis;

import java.util.List;

public interface GetDiagnosisQuery {
    public List<GetDiagnosisOutput> getDiagnosis();
}
