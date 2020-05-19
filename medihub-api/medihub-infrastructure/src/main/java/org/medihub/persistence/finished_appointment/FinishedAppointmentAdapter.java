package org.medihub.persistence.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.domain.appointment.FinishedAppointment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FinishedAppointmentAdapter implements GetFinishedAppointmentsPort {
    private final FinishedAppointmentMapper mapper;
    private final FinishedAppointmentRepository repository;

    @Override
    public Set<FinishedAppointment> getFinishedAppointments(Long patientId) {
        return repository
                .findAllByAppointment_Patient_Id(patientId)
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toSet());
    }
}
