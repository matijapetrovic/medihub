package org.medihub.application.ports.incoming.medical_doctor;

import java.time.LocalDate;
import java.util.List;

public interface SearchDoctorsQuery {
    List<SearchDoctorsOutput> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId);
}
