package org.medihub.application.services.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.AppointmentRequestResponse;
import org.medihub.application.ports.incoming.appointment_request.GetAppointmentRequestUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic.ClinicAdmin;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentRequestService implements GetAppointmentRequestUseCase {
    private final GetAppointmentRequestPort getAppointmentRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<AppointmentRequestResponse> getAll() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return mapToResponse(getAppointmentRequestPort.loadAll(clinicAdmin.getClinic().getId()));
    }

    private List<AppointmentRequestResponse> mapToResponse(List<AppointmentRequest> appointmentRequests){
        return appointmentRequests
                .stream()
                .map(appointmentRequest -> new AppointmentRequestResponse(
                        appointmentRequest.getId(),
                        appointmentRequest.getDoctor().getAccount().getEmail(),
                        appointmentRequest.getPatient().getAccount().getEmail(),
                        appointmentRequest.getPrice().getAmount(),
                        appointmentRequest.getDate().toString(),
                        appointmentRequest.getTime().toString()
                ))
                .collect(Collectors.toList());
    }
}
