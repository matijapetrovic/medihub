package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.AppointmentRequest;

public interface SaveAppointmentRequestPort {
    void saveAppointmentRequest(AppointmentRequest request);
}
