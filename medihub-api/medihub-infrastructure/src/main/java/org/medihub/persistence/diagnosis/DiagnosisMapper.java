package org.medihub.persistence.diagnosis;

import org.medihub.domain.Diagnosis;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class DiagnosisMapper {
    public Diagnosis mapToDomainEntity(DiagnosisJpaEntity diagnosis){
        return new Diagnosis(
                diagnosis.getId(),
                diagnosis.getName());
    }

    public DiagnosisJpaEntity mapToJpaEntity(Diagnosis diagnosis){
        return new DiagnosisJpaEntity(
                diagnosis.getId(),
                diagnosis.getName());
    }

    public Set<Diagnosis> mapToDomainSet(Set<DiagnosisJpaEntity> diagnosisJpaSet){
        return diagnosisJpaSet.stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toSet());
    }

    public Set<DiagnosisJpaEntity> mapToJpaSet(Set<Diagnosis> diagnosisDomainSet){
        return diagnosisDomainSet.stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toSet());
    }
}
