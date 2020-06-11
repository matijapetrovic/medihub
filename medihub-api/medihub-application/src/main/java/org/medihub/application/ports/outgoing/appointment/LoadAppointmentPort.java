package org.medihub.application.ports.outgoing.appointment;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.appointment.Appointment;

import java.util.List;

public interface LoadAppointmentPort {
    Appointment getAppointmentById(Long id) throws NotFoundException;

    List<Appointment> getAllByClinicId(Long clinicId);
}
