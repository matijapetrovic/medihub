package org.medihub.persistence.medical_record;

import org.medihub.domain.MedicalRecord;
import org.medihub.persistence.diagnosis.DiagnosisMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {
    private final DiagnosisMapper diagnosisMapper = new DiagnosisMapper();

    public MedicalRecord mapToDomainEntity(MedicalRecordJpaEntity patientJpaEntity){
        return new MedicalRecord(
                diagnosisMapper.mapToDomainSet(patientJpaEntity.getDiagnosis())
                );
    }

    public MedicalRecordJpaEntity mapToJpaEntity(MedicalRecord medicalRecord){
        return new MedicalRecordJpaEntity(
                null,
                diagnosisMapper.mapToJpaSet(medicalRecord.getDiagnosis())
        );
    }
}
