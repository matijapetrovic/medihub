package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.appointment.CancelAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.DeleteAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class CancelAppointmentService implements CancelAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadAppointmentPort loadAppointmentPort;
    private final DeleteAppointmentPort deleteAppointmentPort;
    private final DeleteAppointmentScheduleItemPort deleteAppointmentScheduleItemPort;

    @Override
    public void cancelAppointment(@NotNull Long appointmentId) throws ForbiddenException {
        Appointment appointment = loadAppointmentPort.getAppointmentById(appointmentId);
        Account account = getAuthenticatedPort.getAuthenticated();
        ensurePatientCanAccessAppointment(appointment, account);

        deleteAppointmentScheduleItemPort.deleteAppointmentItemByAppointmentId(appointmentId);
        deleteAppointmentPort.deleteAppointment(appointmentId);
    }

    private void ensurePatientCanAccessAppointment(Appointment appointment, Account account) throws ForbiddenException {
        if (!appointment.getPatient().getAccount().getId().equals(account.getId()))
            throw new ForbiddenException();
    }
}
