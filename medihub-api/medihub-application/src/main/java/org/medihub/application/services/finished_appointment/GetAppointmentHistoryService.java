package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryOutput;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.patient.Patient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentHistoryService implements GetAppointmentHistoryQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetFinishedAppointmentsPort getFinishedAppointmentsPort;
    private final LoadClinicReviewPort loadClinicReviewPort;

    @Override
    public List<GetAppointmentHistoryOutput> getAppointmentHistory() {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());

        List<FinishedAppointment> finishedAppointments =
                getFinishedAppointmentsPort.getFinishedAppointments(patient.getId());

        return mapToOutput(finishedAppointments);
    }

    private List<GetAppointmentHistoryOutput> mapToOutput(List<FinishedAppointment> appointments) {
        return appointments
                .stream()
                .map(appointment ->
                        new GetAppointmentHistoryOutput(
                            appointment.getId(),
                            appointment.getAppointment().getDoctor().getSpecialization().getName(), // mozda predugacko
                            appointment.getAppointment().getDoctor().getFullName(),
                            appointment.getAppointment().getDoctor().getClinic().getName(),
                            appointment.getAppointment().getDate().toString(),
                            appointment.getAppointment().getTime().toString(),
                            getRating(appointment)
                        ))
                .collect(Collectors.toList());
    }

    private BigDecimal getRating(FinishedAppointment appointment) {
        ClinicReview review = loadClinicReviewPort.loadByAppointmentId(appointment.getId());
        return review != null ? review.getRating() : null;
    }
}
