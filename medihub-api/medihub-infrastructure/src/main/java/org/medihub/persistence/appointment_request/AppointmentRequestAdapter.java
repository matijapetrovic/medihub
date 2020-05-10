package org.medihub.persistence.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.domain.appointment.AppointmentRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentRequestAdapter implements SaveAppointmentRequestPort {
    private final AppointmentRequestRepository appointmentRequestRepository;
    private final AppointmentRequestMapper mapper;
    @Override
    public void saveAppointmentRequest(AppointmentRequest request) {
        AppointmentRequestJpaEntity entity = mapper.mapToJpaEntity(request);
        appointmentRequestRepository.save(entity);
    }
}
