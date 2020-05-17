package org.medihub.application.ports.incoming.appointment_request;

import java.util.List;

public interface GetAppointmentRequestUseCase {
    List<AppointmentRequestResponse> getAll();
}
