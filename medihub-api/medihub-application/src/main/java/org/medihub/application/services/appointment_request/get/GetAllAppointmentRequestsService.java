package org.medihub.application.services.appointment_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.GetAllAppointmentRequestsUseCase;
import org.medihub.application.ports.outgoing.appointment_request.GetAllAppointmentRequestsPort;
import org.medihub.domain.appointment.AppointmentRequest;

import java.util.List;

@RequiredArgsConstructor
public class GetAllAppointmentRequestsService implements GetAllAppointmentRequestsUseCase {
    private final GetAllAppointmentRequestsPort getAllAppointmentRequestsPort;

    @Override
    public List<AppointmentRequest> getAll() {
        return getAllAppointmentRequestsPort.getAll();
    }
}
