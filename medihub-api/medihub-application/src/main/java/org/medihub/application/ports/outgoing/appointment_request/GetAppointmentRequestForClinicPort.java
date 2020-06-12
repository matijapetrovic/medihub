package org.medihub.application.ports.outgoing.appointment_request;

import org.medihub.domain.appointment.AppointmentRequest;

import java.util.List;

public interface GetAppointmentRequestForClinicPort {
    List<AppointmentRequest> loadAll(Long clinicAdminId);
}
