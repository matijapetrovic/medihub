package org.medihub.application.ports.outgoing.appointment_request;

import org.medihub.domain.appointment.AppointmentRequest;

import java.util.List;

public interface GetAppointmentRequestPort {
    List<AppointmentRequest> loadAll(Long clinicAdminId);
}
