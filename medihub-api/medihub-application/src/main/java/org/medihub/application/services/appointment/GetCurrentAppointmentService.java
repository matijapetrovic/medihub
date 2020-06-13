package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.GetCurrentAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.MedicalDoctor;

@RequiredArgsConstructor
public class GetCurrentAppointmentService implements GetCurrentAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadDoctorPort loadDoctorPort;
    private final GetCurrentAppointmentPort getCurrentAppointmentPort;

    @Override
    public Appointment getCurrentAppointment(Long patientId) {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalDoctor doctor = loadDoctorPort.loadDoctorByAccountId(account.getId());
        return getCurrentAppointmentPort.getCurrentAppointment(doctor.getId(), patientId);
    }
}
