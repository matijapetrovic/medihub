package org.medihub.application.ports.incoming.clinic;

import java.util.Date;
import java.util.List;

public interface SearchClinicsQuery {
    List<SearchClinicsOutput> searchClinics(Date date, Long appointmentTypeId);
}
