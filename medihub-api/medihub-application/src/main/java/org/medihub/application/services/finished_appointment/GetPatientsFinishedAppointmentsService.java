package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentOutput;
import org.medihub.application.ports.incoming.finished_appointment.GetPatientsFinishedAppointmetsQuery;
import org.medihub.application.ports.outgoing.finished_appointment.GetPatientsFinishedAppointmentsPort;
import org.medihub.domain.appointment.FinishedAppointment;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPatientsFinishedAppointmentsService implements GetPatientsFinishedAppointmetsQuery {
    private final GetPatientsFinishedAppointmentsPort getPatientsFinishedAppointmentsPort;

    @Override
    public List<GetFinishedAppointmentOutput> getPatientsFinishedAppointments(Long patientId) {
        List<FinishedAppointment> finishedAppointments = getPatientsFinishedAppointmentsPort.getPatientsFinishedAppointments(patientId);

        return createOutput(finishedAppointments);
    }

    private List<GetFinishedAppointmentOutput> createOutput(List<FinishedAppointment> finishedAppointments) {
        return finishedAppointments
                .stream()
                .map(finishedAppointment -> new GetFinishedAppointmentOutput(
                        finishedAppointment.getId(),
                        finishedAppointment.getDescription(),
                        finishedAppointment.getAppointment().getId(),
                        finishedAppointment.getDiagnosis().getId(),
                        finishedAppointment.getAppointment().getDate().toString(),
                        finishedAppointment.getAppointment().getTime().toString(),
                        finishedAppointment.getDiagnosis().getName()
                ))
                .collect(Collectors.toList());
    }
}
