package org.medihub.persistence.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.patient.MedicalRecord;
import org.medihub.persistence.diagnosis.DiagnosisMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MedicalRecordMapper {
    private final DiagnosisMapper diagnosisMapper;

    public MedicalRecord mapToDomainEntity(MedicalRecordJpaEntity medicalRecordJpaEntity){
        return new MedicalRecord(
                medicalRecordJpaEntity.getId(),
                diagnosisMapper.mapToDomainSet(medicalRecordJpaEntity.getDiagnosis())
                );
    }

    public MedicalRecordJpaEntity mapToJpaEntity(MedicalRecord medicalRecord){
        return new MedicalRecordJpaEntity(
                null,
                diagnosisMapper.mapToJpaSet(medicalRecord.getDiagnosis())
        );
    }
}
