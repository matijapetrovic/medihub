package org.medihub.application.services.appointment_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.AppointmentRequestResponse;
import org.medihub.application.ports.incoming.appointment_request.GetAppointmentRequestUseCase;
import org.medihub.application.ports.incoming.medical_doctor.MedicalDoctorResponse;
import org.medihub.application.ports.incoming.patient.PatientResponse;
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
                        new MedicalDoctorResponse(
                                appointmentRequest.getDoctor().getId(),
                                appointmentRequest.getDoctor().getPersonalInfo().getAccount().getEmail(),
                                appointmentRequest.getDoctor().getPersonalInfo().getFirstName(),
                                appointmentRequest.getDoctor().getPersonalInfo().getLastName(),
                                appointmentRequest.getDoctor().getPersonalInfo().getAddress(),
                                appointmentRequest.getDoctor().getPersonalInfo().getTelephoneNumber(),
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
                                appointmentRequest.getPatient().getPersonalInfo().getFirstName(),
                                appointmentRequest.getPatient().getPersonalInfo().getLastName(),
                                appointmentRequest.getPatient().getPersonalInfo().getAccount().getEmail(),
                                appointmentRequest.getPatient().getPersonalInfo().getAddress(),
                                appointmentRequest.getPatient().getInsuranceNumber()
                        ),
                        appointmentRequest.getPrice().getAmount(),
                        appointmentRequest.getDate().toString(),
                        appointmentRequest.getTime().toString()
                ))
                .collect(Collectors.toList());
    }
}
