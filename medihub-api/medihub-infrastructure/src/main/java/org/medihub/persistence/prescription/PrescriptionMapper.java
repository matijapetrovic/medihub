package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Prescription;
import org.medihub.persistence.drug.DrugMapper;
import org.medihub.persistence.medical_nurse.MedicalNurseMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PrescriptionMapper {
    private final DrugMapper drugMapper;
    private final MedicalNurseMapper medicalNurseMapper;

    public Prescription mapToDomainEntity (PrescriptionJpaEntity prescriptionJpaEntity){
        return new Prescription(
                prescriptionJpaEntity.getId(),
                drugMapper.mapToDomainEntity(prescriptionJpaEntity.getDrugJpaEntity()),
                medicalNurseMapper.mapToDomainEntity(prescriptionJpaEntity.getMedicalNurseJpaEntity())
        );
    }

    public PrescriptionJpaEntity mapToJpaEntity(Prescription prescription){
        return new PrescriptionJpaEntity (
                null,
                drugMapper.mapToJpaEntity(prescription.getDrug()),
                medicalNurseMapper.mapToJpaEntity(prescription.getMedicalNurse())
        );
    }

    public Set<Prescription> mapToDomainSet(Set<PrescriptionJpaEntity> prescriptionJpaEntities){
        return prescriptionJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toSet());
    }

    public Set<PrescriptionJpaEntity> mapToJpaSet(Set<Prescription> prescriptions){
        return prescriptions
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toSet());
    }
}
