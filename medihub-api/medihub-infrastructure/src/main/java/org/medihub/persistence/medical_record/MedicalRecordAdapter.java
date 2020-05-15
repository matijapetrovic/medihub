package org.medihub.persistence.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.domain.medical_record.MedicalRecord;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class MedicalRecordAdapter implements LoadMedicalRecordPort {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecord loadMedicalRecord(Long patientId) {
        MedicalRecordJpaEntity medicalRecord =
                medicalRecordRepository.findByPatient_Id(patientId)
                .orElseThrow(EntityNotFoundException::new);

        return medicalRecordMapper.mapToDomainEntity(medicalRecord);
    }
}
