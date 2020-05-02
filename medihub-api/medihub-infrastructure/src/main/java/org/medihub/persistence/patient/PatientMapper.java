package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Patient;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_record.MedicalRecordMapper;
import org.springframework.stereotype.Component;

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
}
