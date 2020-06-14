package org.medihub.application.ports.outgoing.appointment_request;

import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.appointment.AppointmentRequest;

public interface LoadAppointmentRequestPort {
    AppointmentRequest loadById(Long id) throws NotFoundException;
    AppointmentRequest loadByIdWithLock(Long id) throws NotFoundException, NotAvailableException;
}