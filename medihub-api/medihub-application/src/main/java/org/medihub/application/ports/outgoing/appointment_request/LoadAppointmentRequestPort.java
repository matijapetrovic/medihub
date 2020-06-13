package org.medihub.application.ports.outgoing.appointment_request;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.appointment.AppointmentRequest;

public interface LoadAppointmentRequestPort {
    AppointmentRequest loadById(Long id) throws NotFoundException;
}
