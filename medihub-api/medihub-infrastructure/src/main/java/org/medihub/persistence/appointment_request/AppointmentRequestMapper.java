package org.medihub.persistence.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentRequestMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final PatientMapper patientMapper;

    public AppointmentRequest mapToDomainEntity(AppointmentRequestJpaEntity request) {
        return new AppointmentRequest(
                request.getId(),
                medicalDoctorMapper.mapToDomainEntity(request.getMedicalDoctorJpaEntity()),
                patientMapper.mapToDomainEntity(request.getPatientJpaEntity()),
                Money.of(request.getMoney()),
                request.getDate(),
                request.getTime());
    }

    public AppointmentRequestJpaEntity mapToJpaEntity(AppointmentRequest request) {
        return new AppointmentRequestJpaEntity(
                request.getId(),
                medicalDoctorMapper.mapToJpaEntity(request.getDoctor()),
                patientMapper.mapToJpaEntity(request.getPatient()),
                request.getPrice().getAmount(),
                request.getDate(),
                request.getTime());
    }
}
