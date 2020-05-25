package org.medihub.persistence.diagnosis;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisPort;
import org.medihub.application.ports.outgoing.diagnosis.SaveDiagnosisPort;
import org.medihub.domain.Diagnosis;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiagnosisAdapter implements SaveDiagnosisPort, GetDiagnosisPort {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        DiagnosisJpaEntity jpaDiagnosis = diagnosisRepository.save(diagnosisMapper.mapToJpaEntity(diagnosis));
        return diagnosisMapper.mapToDomainEntity(jpaDiagnosis);
    }

    @Override
    public List<Diagnosis> getDiagnosis() {
        return diagnosisRepository
                .findAll()
                .stream()
                .map(diagnosisMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
