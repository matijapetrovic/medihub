package org.medihub.persistence.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_record.Allergy;
import org.medihub.domain.medical_record.MedicalRecord;
import org.medihub.domain.medical_record.PatientDetails;
import org.medihub.persistence.diagnosis.DiagnosisMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MedicalRecordMapper {
    private final DiagnosisMapper diagnosisMapper;

    public MedicalRecord mapToDomainEntity(MedicalRecordJpaEntity medicalRecord){
        return new MedicalRecord(
                medicalRecord.getId(),
                new PatientDetails(
                        medicalRecord.getHeight(),
                        medicalRecord.getWeight(),
                        medicalRecord.getBloodType(),
                        medicalRecord.getRhPositive(),
                        medicalRecord.getLeftDioptry(),
                        medicalRecord.getRightDioptry()),
                diagnosisMapper.mapToDomainSet(medicalRecord.getDiagnosis()),
                mapToDomainAllergySet(medicalRecord.getAllergies()));
    }

    public MedicalRecordJpaEntity mapToJpaEntity(MedicalRecord medicalRecord){
        return new MedicalRecordJpaEntity(
                medicalRecord.getId(),
                medicalRecord.getHeight(),
                medicalRecord.getWeight(),
                medicalRecord.getBloodType(),
                medicalRecord.getRhPositive(),
                medicalRecord.getLeftDioptry(),
                medicalRecord.getRightDioptry(),
                diagnosisMapper.mapToJpaSet(medicalRecord.getDiagnosis()),
                mapToJpaEntityAllergyMap(medicalRecord.getAllergies()));
    }

    private Set<Allergy> mapToDomainAllergySet(Map<String, String> allergies) {
        return allergies
                .entrySet()
                .stream()
                .map(entry -> new Allergy(entry.getKey(),
                        entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Map<String, String> mapToJpaEntityAllergyMap(Set<Allergy> allergies) {
        return allergies
                .stream()
                .collect(Collectors.toMap(
                        Allergy::getName,
                        Allergy::getLevelString));
    }
}
