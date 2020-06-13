package org.medihub.persistence.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordByIdPort;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.application.ports.outgoing.medical_record.SaveMedicalRecordPort;
import org.medihub.domain.medical_record.MedicalRecord;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class MedicalRecordAdapterPort implements LoadMedicalRecordPort,
        LoadMedicalRecordByIdPort,
        SaveMedicalRecordPort {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecord loadMedicalRecord(Long patientId) {
        MedicalRecordJpaEntity medicalRecord =
                medicalRecordRepository.findByPatient_Id(patientId)
                .orElseThrow(EntityNotFoundException::new);

        return medicalRecordMapper.mapToDomainEntity(medicalRecord);
    }

    @Override
    public MedicalRecord loadMedicalRecordById(Long id) {
        MedicalRecordJpaEntity medicalRecordJpaEntity = medicalRecordRepository.getOne(id);
        return medicalRecordMapper.mapToDomainEntity(medicalRecordJpaEntity);
    }

    @Override
    public void saveMedicalRecordPort(MedicalRecord medicalRecord) {
        MedicalRecordJpaEntity medicalRecordJpaEntity = medicalRecordMapper.mapToJpaEntity(medicalRecord);
        medicalRecordRepository.save(medicalRecordJpaEntity);
    }
}
