package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.patient.Patient;
import org.medihub.persistence.personal_info.PersonalInfoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PatientMapper {
    private final PersonalInfoMapper personalInfoMapper;

    public Patient mapToDomainEntity(PatientJpaEntity patientJpaEntity){
        return new Patient(
                patientJpaEntity.getId(),
                patientJpaEntity.getInsuranceNumber(),
                personalInfoMapper.mapToDomainEntity(patientJpaEntity.getPersonalInfo()));
    }

    public PatientJpaEntity mapToJpaEntity(Patient patient){
        return new PatientJpaEntity(
                patient.getId(),
                patient.getInsuranceNumber(),
                personalInfoMapper.mapToJpaEntity(patient.getPersonalInfo()));
    }

    public List<Patient> mapToDomainList(List<PatientJpaEntity> patientJpaEntities){
        return patientJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
