package org.medihub.application.services.clinic.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.GetClinicProfileOutput;
import org.medihub.application.ports.incoming.clinic.GetClinicProfileQuery;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.predefined_appointment.GetPredefinedAppointmentsPort;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.domain.clinic.Clinic;

import java.util.List;

@RequiredArgsConstructor
public class GetClinicProfileService implements GetClinicProfileQuery {
    private final LoadClinicPort loadClinicPort;

    @Override
    public GetClinicProfileOutput getClinicProfile(Long clinicId) {
        Clinic clinic = loadClinicPort.loadClinic(clinicId);

        return mapToOutput(clinic);
    }

    GetClinicProfileOutput mapToOutput(Clinic clinic) {
        return new GetClinicProfileOutput(
                clinic.getName(),
                clinic.getDescription(),
                clinic.getAddress().getAddressLine(),
                clinic.getAddress().getCity(),
                clinic.getAddress().getCountry(),
                clinic.getRating().getRating(),
                clinic.getRating().getCount());
    }
}
