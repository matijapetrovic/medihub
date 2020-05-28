package org.medihub.application.services.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.diagnosis.GetDiagnosisOutput;
import org.medihub.application.ports.incoming.diagnosis.GetDiagnosisQuery;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisPort;
import org.medihub.domain.Diagnosis;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetDiagnosisService implements GetDiagnosisQuery {
    private final GetDiagnosisPort getDiagnosisPort;

    @Override
    public List<GetDiagnosisOutput> getDiagnosis() {
        List<Diagnosis> diagnosis = getDiagnosisPort.getDiagnosis();
        List<GetDiagnosisOutput> diagnosisOut = new ArrayList<GetDiagnosisOutput>();

        for(Diagnosis d : diagnosis) {
            diagnosisOut.add(new GetDiagnosisOutput(
                    d.getId(),
                    d.getName()
            ));
        }

        return diagnosisOut;
    }
}
