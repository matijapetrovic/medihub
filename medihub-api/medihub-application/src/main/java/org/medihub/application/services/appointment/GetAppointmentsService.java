package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsOutput;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsQuery;
import org.medihub.application.ports.outgoing.appointment.GetScheduledAppointmentsPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentsService implements GetAppointmentsQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetScheduledAppointmentsPort getScheduledAppointmentsPort;

    @Override
    public List<GetAppointmentsOutput> getAppointments() {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        List<Appointment> appointments = getScheduledAppointmentsPort.getAppointments(patient.getId());

        return mapToOutput(appointments);
    }

    private List<GetAppointmentsOutput> mapToOutput(List<Appointment> appointments) {
        return appointments
                .stream()
                .map(appointment ->
                        new GetAppointmentsOutput(
                                appointment.getId(),
                                appointment.getDoctor().getSpecialization().getName(),
                                appointment.getDoctor().getFullName(),
                                appointment.getDoctor().getClinic().getName(),
                                appointment.getDate().toString(),
                                appointment.getTime().toString()))
                .collect(Collectors.toList());
    }
}
