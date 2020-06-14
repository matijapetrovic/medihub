package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Prescription;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.persistence.drug.DrugMapper;
import org.medihub.persistence.drug.DrugRepository;
import org.medihub.persistence.finished_appointment.FinishedAppointmentMapper;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PrescriptionMapper {
    private final DrugMapper drugMapper;
    private final MedicalNurseMapper medicalNurseMapper;
    private final FinishedAppointmentMapper finishedAppointmentMapper;
    private final DrugRepository drugRepository;
    public Prescription mapToDomainEntity (PrescriptionJpaEntity prescriptionJpaEntity){
        MedicalNurse nurse = prescriptionJpaEntity.getMedicalNurseJpaEntity() == null? null : medicalNurseMapper.mapToDomainEntity(prescriptionJpaEntity.getMedicalNurseJpaEntity());
        return new Prescription(
                prescriptionJpaEntity.getId(),
                drugMapper.mapToDomainEntity(prescriptionJpaEntity.getDrugJpaEntity()),
                finishedAppointmentMapper.mapToDomainEntity(prescriptionJpaEntity.getFinishedAppointment()),
                nurse,
                prescriptionJpaEntity.getVersion()
        );
    }

    public PrescriptionJpaEntity mapToJpaEntity(Prescription prescription){
        MedicalNurseJpaEntity nurse = prescription.getMedicalNurse() == null? null : medicalNurseMapper.mapToJpaEntity(prescription.getMedicalNurse());
        return new PrescriptionJpaEntity (
                prescription.getId(),
                drugRepository.findById(prescription.getDrug().getId())
                .orElseThrow(),
                nurse,
                finishedAppointmentMapper.mapToJpaEntity(prescription.getFinishedAppointment()),
                prescription.getVersion()
        );
    }

    public List<Prescription> mapToDomainList(List<PrescriptionJpaEntity> prescriptionJpaEntities){
        return prescriptionJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    public List<PrescriptionJpaEntity> mapToJpaList(List<Prescription> prescriptions){
        return prescriptions
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toList());
    }
}
