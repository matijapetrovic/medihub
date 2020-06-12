package org.medihub.application.ports.incoming.appointment_request;

import org.medihub.domain.appointment.AppointmentRequest;

import java.util.List;

public interface GetAllAppointmentRequestsUseCase {
    List<AppointmentRequest> getAll();
}
