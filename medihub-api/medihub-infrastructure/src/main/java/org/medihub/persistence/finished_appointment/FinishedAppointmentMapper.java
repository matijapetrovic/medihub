package org.medihub.persistence.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.persistence.appointment.AbstractAppointmentMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.diagnosis.DiagnosisMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishedAppointmentMapper {
    private final AbstractAppointmentMapper abstractAppointmentMapper;
    private final DiagnosisMapper diagnosisMapper;

    public FinishedAppointment mapToDomainEntity(FinishedAppointmentJpaEntity appointment){
        return new FinishedAppointment(
                appointment.getId(),
                appointment.getDescription(),
                abstractAppointmentMapper.mapToDomainEntity(appointment.getAppointment()),
                diagnosisMapper.mapToDomainEntity(appointment.getDiagnosis()));
    }

    public FinishedAppointmentJpaEntity mapToJpaEntity(FinishedAppointment appointment) {
        return new FinishedAppointmentJpaEntity(
                appointment.getId(),
                appointment.getDescription(),
                (AppointmentJpaEntity) abstractAppointmentMapper.mapToJpaEntity(appointment.getAppointment()),
                diagnosisMapper.mapToJpaEntity(appointment.getDiagnosis()));
    }
}
