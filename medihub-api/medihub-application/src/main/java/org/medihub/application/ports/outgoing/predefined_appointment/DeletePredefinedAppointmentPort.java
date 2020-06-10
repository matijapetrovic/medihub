package org.medihub.application.ports.outgoing.predefined_appointment;

import org.medihub.application.exceptions.NotFoundException;

public interface DeletePredefinedAppointmentPort {
    void deletePredefinedAppointment(Long appointmentId) throws NotFoundException;
}
