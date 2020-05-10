package org.medihub.application.services.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.SearchClinicsOutput;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.Clinic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchClinicsService implements SearchClinicsQuery {
    private final LoadAppointmentTypePort loadAppointmentTypePort;
    private final SearchClinicsPort searchClinicsPort;

    @Override
    public List<SearchClinicsOutput> searchClinics(Date date, Long appointmentTypeId) {
        AppointmentType appointmentType =
                loadAppointmentTypePort.loadAppointmentType(appointmentTypeId);

        return mapToOutput(searchClinicsPort.searchClinics(date, appointmentType), appointmentType);
    }

    private List<SearchClinicsOutput> mapToOutput(List<Clinic> clinics, AppointmentType appointmentType) {
        return clinics
                .stream()
                .map((clinic) -> new SearchClinicsOutput(
                        clinic.getId(),
                        clinic.getName(),
                        5.0,
                        clinic.getAddress().getAddressLine(),
                        clinic.getAddress().getCity(),
                        clinic.getAddress().getCountry(),
                        clinic.getPrice(appointmentType).getAmount()))
                .collect(Collectors.toList());
    }
}
