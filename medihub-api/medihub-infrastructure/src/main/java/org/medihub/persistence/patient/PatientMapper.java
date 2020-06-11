package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.patient.Patient;
import org.medihub.persistence.account.AccountMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PatientMapper {
    private final AccountMapper accountMapper;

    public Patient mapToDomainEntity(PatientJpaEntity patientJpaEntity){
        return new Patient(
                patientJpaEntity.getId(),
                patientJpaEntity.getInsuranceNumber(),
                accountMapper.mapToDomainEntity(patientJpaEntity.getAccount()));
    }

    public PatientJpaEntity mapToJpaEntity(Patient patient){
        return new PatientJpaEntity(
                patient.getId(),
                patient.getInsuranceNumber(),
                accountMapper.mapToJpaEntity(patient.getAccount()));
    }

    public List<Patient> mapToDomainList(List<PatientJpaEntity> patientJpaEntities){
        return patientJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    public List<PatientJpaEntity> mapToJpaList(List<Patient> patients){
        return patients
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toList());
    }
}
