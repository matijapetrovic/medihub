package org.medihub.application.ports.outgoing.predefined_appointment;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.appointment.PredefinedAppointment;

public interface LoadPredefinedAppointmentPort {
    PredefinedAppointment loadPredefinedAppointment(Long appointmentId) throws NotFoundException;
    PredefinedAppointment loadByIdWithLock(Long id) throws NotFoundException;
}
