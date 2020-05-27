package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.predefined_appointment.AddPredefinedAppointmentPort;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PredefinedAppointmentAdapter implements AddPredefinedAppointmentPort {
    private final PredefinedAppointmentRepository predefinedAppointmentRepository;
    private final PredefinedAppointmentMapper predefinedAppointmentMapper;

    @Override
    public void addPredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        predefinedAppointmentRepository.save(predefinedAppointmentMapper.mapToJpaEntity(predefinedAppointment));
    }
}
