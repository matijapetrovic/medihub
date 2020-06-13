package org.medihub.application.services.clinic.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.SearchClinicsOutput;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchClinicsService implements SearchClinicsQuery {
    private final LoadAppointmentTypePort loadAppointmentTypePort;
    private final SearchClinicsPort searchClinicsPort;

    @Override
    public List<SearchClinicsOutput> searchClinics(LocalDate date, Long appointmentTypeId) {
        AppointmentType appointmentType =
                appointmentTypeId == null ? null : loadAppointmentTypePort.loadAppointmentType(appointmentTypeId);

        return mapToOutput(searchClinicsPort.searchClinics(date, appointmentTypeId), appointmentType);
    }

    private List<SearchClinicsOutput> mapToOutput(List<Clinic> clinics, AppointmentType appointmentType) {
        return clinics
                .stream()
                .map((clinic) -> new SearchClinicsOutput(
                        clinic.getId(),
                        clinic.getName(),
                        clinic.getDescription(),
                        clinic.getRating().getRating(),
                        clinic.getRating().getCount(),
                        clinic.getAddress().getAddressLine(),
                        clinic.getAddress().getCity(),
                        clinic.getAddress().getCountry(),
                        appointmentType == null ? null : clinic.getPrice(appointmentType).getAmount()))
                .collect(Collectors.toList());
    }
}
