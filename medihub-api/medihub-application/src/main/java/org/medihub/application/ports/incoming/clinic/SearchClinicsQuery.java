package org.medihub.application.ports.incoming.clinic;

import java.time.LocalDate;
import java.util.List;

public interface SearchClinicsQuery {
    List<SearchClinicsOutput> searchClinics(LocalDate date, Long appointmentTypeId);
}
