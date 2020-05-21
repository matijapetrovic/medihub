package org.medihub.persistence.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.diagnosis.DiagnosisMapper;
import org.medihub.persistence.prescription.PrescriptionMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishedAppointmentMapper {
    private final AppointmentMapper appointmentMapper;
    private final DiagnosisMapper diagnosisMapper;
    private final PrescriptionMapper prescriptionMapper;

    public FinishedAppointment mapToDomainEntity(FinishedAppointmentJpaEntity appointment){
        return new FinishedAppointment(
                appointment.getId(),
                appointment.getDescription(),
                appointmentMapper.mapToDomainEntity(appointment.getAppointment()),
                prescriptionMapper.mapToDomainSet(appointment.getPrescriptions()),
                diagnosisMapper.mapToDomainEntity(appointment.getDiagnosis()));
    }

    public FinishedAppointmentJpaEntity mapToJpaEntity(FinishedAppointment appointment) {
        return new FinishedAppointmentJpaEntity(
                appointment.getId(),
                appointment.getDescription(),
                appointmentMapper.mapToJpaEntity(appointment.getAppointment()),
                prescriptionMapper.mapToJpaSet(appointment.getPrescriptions()),
                diagnosisMapper.mapToJpaEntity(appointment.getDiagnosis()));
    }
}
