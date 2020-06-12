package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.CancelAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RequiredArgsConstructor
public class CancelAppointmentService implements CancelAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadAppointmentPort loadAppointmentPort;
    private final DeleteAppointmentScheduleItemPort deleteAppointmentScheduleItemPort;

    @Override
    @Transactional
    public void cancelAppointment(@NotNull Long appointmentId) throws ForbiddenException, NotFoundException {
        Appointment appointment = loadAppointmentPort.getAppointmentById(appointmentId);
        Account account = getAuthenticatedPort.getAuthenticated();
        ensurePatientCanCancelAppointment(appointment, account);

        deleteAppointmentScheduleItemPort.deleteAppointmentItemByAppointmentId(appointmentId);
    }

    private void ensurePatientCanCancelAppointment(Appointment appointment, Account account) throws ForbiddenException {
        if (!appointment.getPatient().getPersonalInfo().getAccount().getId().equals(account.getId()))
            throw new ForbiddenException("Patient cannot access this appointment");

        if (LocalDate.now().plusDays(1).isAfter(appointment.getDate()))
            throw new ForbiddenException("Patient can only cancel appointment 24h before start");
    }
}
