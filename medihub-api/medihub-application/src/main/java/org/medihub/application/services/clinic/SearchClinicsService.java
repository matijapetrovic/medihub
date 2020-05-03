package org.medihub.application.services.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.SearchClinicsOutput;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.domain.AppointmentType;
import org.medihub.domain.Clinic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class SearchClinicsService implements SearchClinicsQuery {
    private final LoadAppointmentTypePort loadAppointmentTypePort;

    @Override
    public List<SearchClinicsOutput> searchClinics(Date date, Long appointmentTypeId) {
        AppointmentType appointmentType =
                loadAppointmentTypePort.loadAppointmentType(appointmentTypeId);

        return new ArrayList<>();
    }

    private List<SearchClinicsOutput> mapToOutput(List<Clinic> clinics) {
        return new ArrayList<>();
    }
}
