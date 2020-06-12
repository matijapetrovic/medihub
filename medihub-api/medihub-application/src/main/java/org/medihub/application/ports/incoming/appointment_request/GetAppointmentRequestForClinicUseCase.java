package org.medihub.application.ports.incoming.appointment_request;

import java.util.List;

public interface GetAppointmentRequestForClinicUseCase {
    List<AppointmentRequestResponse> getAll();
}
