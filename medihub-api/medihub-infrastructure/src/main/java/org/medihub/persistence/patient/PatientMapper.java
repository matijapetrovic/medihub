package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Patient;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_record.MedicalRecordMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PatientMapper {
    private final AccountMapper accountMapper;
    private final MedicalRecordMapper medicalRecordMapper;
    private final AppointmentMapper appointmentMapper;

    public Patient mapToDomainEntity(PatientJpaEntity patientJpaEntity){
        return new Patient(
                patientJpaEntity.getId(),
                patientJpaEntity.getInsuranceNumber(),
                accountMapper.mapToDomainEntity(patientJpaEntity.getAccount()),
                medicalRecordMapper.mapToDomainEntity(patientJpaEntity.getMedicalRecordJpaEntity()),
                appointmentMapper.mapToDomainSet(patientJpaEntity.getAppointmentJpaEntitySet())
                );
    }

    public PatientJpaEntity mapToJpaEntity(Patient patient){
        return new PatientJpaEntity(
                null,
                patient.getInsuranceNumber(),
                accountMapper.mapToJpaEntity(patient.getAccount()),
                medicalRecordMapper.mapToJpaEntity(patient.getMedicalRecord()),
                appointmentMapper.mapToJpaSet(patient.getAppointments())
        );
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
