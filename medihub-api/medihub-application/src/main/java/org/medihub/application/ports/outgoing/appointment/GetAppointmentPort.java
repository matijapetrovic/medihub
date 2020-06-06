package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

import java.util.List;

public interface GetAppointmentPort {
    Appointment getAppointmentById(Long id);

    List<Appointment> getAllByClinicId(Long clinicId);
}
