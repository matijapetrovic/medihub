package org.medihub.application.services.appointment_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.AppointmentRequestResponse;
import org.medihub.application.ports.incoming.appointment_request.GetAppointmentRequestForClinicUseCase;
import org.medihub.application.ports.incoming.medical_doctor.MedicalDoctorResponse;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAppointmentRequestForClinicPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic.ClinicAdmin;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentRequestForClinicService implements GetAppointmentRequestForClinicUseCase {
    private final GetAppointmentRequestForClinicPort getAppointmentRequestForClinicPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<AppointmentRequestResponse> getAll() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return mapToResponse(getAppointmentRequestForClinicPort.loadAll(clinicAdmin.getClinic().getId()));
    }

    private List<AppointmentRequestResponse> mapToResponse(List<AppointmentRequest> appointmentRequests){
        return appointmentRequests
                .stream()
                .map(appointmentRequest -> new AppointmentRequestResponse(
                        appointmentRequest.getId(),
                        new MedicalDoctorResponse(
                                appointmentRequest.getDoctor().getId(),
                                appointmentRequest.getDoctor().getAccount().getEmail(),
                                appointmentRequest.getDoctor().getAccount().getFirstName(),
                                appointmentRequest.getDoctor().getAccount().getLastName(),
                                appointmentRequest.getDoctor().getAccount().getAddress(),
                                appointmentRequest.getDoctor().getAccount().getTelephoneNumber(),
                                appointmentRequest.getDoctor().getWorkingTime().getFrom().toString(),
                                appointmentRequest.getDoctor().getWorkingTime().getTo().toString(),
                                appointmentRequest.getDoctor().getClinic().getName(),
                                appointmentRequest.getAppointmentType().getName(),
                                appointmentRequest.getAppointmentType().getId(),
                                appointmentRequest.getDoctor().getRating().getRating(),
                                appointmentRequest.getDoctor().getRating().getCount()
                        ),
                        new PatientResponse(
                                appointmentRequest.getPatient().getId(),
                                appointmentRequest.getPatient().getAccount().getFirstName(),
                                appointmentRequest.getPatient().getAccount().getLastName(),
                                appointmentRequest.getPatient().getAccount().getEmail(),
                                appointmentRequest.getPatient().getAccount().getAddress(),
                                appointmentRequest.getPatient().getInsuranceNumber()
                        ),
                        appointmentRequest.getPrice().getAmount(),
                        appointmentRequest.getDate().toString(),
                        appointmentRequest.getTime().toString(),
                        appointmentRequest.getType()
                ))
                .collect(Collectors.toList());
    }
}
