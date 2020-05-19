package org.medihub.persistence.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAppointmentRequestPort;
import org.medihub.domain.appointment.AppointmentRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentRequestAdapter implements
        SaveAppointmentRequestPort,
        GetAppointmentRequestPort,
        DeleteAppointmentRequestPort {
    private final AppointmentRequestRepository appointmentRequestRepository;
    private final AppointmentRequestMapper mapper;

    @Override
    public void saveAppointmentRequest(AppointmentRequest request) {
        AppointmentRequestJpaEntity entity = mapper.mapToJpaEntity(request);
        appointmentRequestRepository.save(entity);
    }

    @Override
    public List<AppointmentRequest> loadAll(Long clinicId) {
        return mapper.mapToDomainList(appointmentRequestRepository.findAll(clinicId));
    }

    @Override
    public void deleteAppointmentRequest(Long id) {
        appointmentRequestRepository.deleteById(id);
    }
}
