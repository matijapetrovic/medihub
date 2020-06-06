package org.medihub.application.ports.outgoing.finished_appointment;

import org.medihub.domain.appointment.FinishedAppointment;

public interface SaveFinishedAppointmentPort {
    public FinishedAppointment saveFinishedAppointment(FinishedAppointment finishedAppointment);
}
