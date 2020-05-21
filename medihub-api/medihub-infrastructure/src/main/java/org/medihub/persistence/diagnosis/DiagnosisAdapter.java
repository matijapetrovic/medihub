package org.medihub.persistence.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.diagnosis.SaveDiagnosisPort;
import org.medihub.domain.Diagnosis;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiagnosisAdapter implements SaveDiagnosisPort {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        DiagnosisJpaEntity jpaDiagnosis = diagnosisRepository.save(diagnosisMapper.mapToJpaEntity(diagnosis));
        return diagnosisMapper.mapToDomainEntity(jpaDiagnosis);
    }
}
