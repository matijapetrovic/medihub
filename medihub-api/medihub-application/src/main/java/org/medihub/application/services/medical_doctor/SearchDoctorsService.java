package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.SearchDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.SearchDoctorsQuery;
import org.medihub.application.ports.outgoing.doctor.SearchDoctorsPort;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchDoctorsService implements SearchDoctorsQuery {
    private final SearchDoctorsPort searchDoctorsPort;

    @Override
    public List<SearchDoctorsOutput> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId) {
        return searchDoctorsPort
                .searchDoctors(clinicId, date, appointmentTypeId)
                .stream()
                .map(medicalDoctor -> new SearchDoctorsOutput(
                        medicalDoctor.getId(),
                        medicalDoctor.getFirstName(),
                        medicalDoctor.getLastName(),
                        medicalDoctor.getRating().getRating(),
                        medicalDoctor.getRating().getCount(),
                        medicalDoctor.getWorkingTime().getFrom().toString(),
                        medicalDoctor.getWorkingTime().getTo().toString()))
                .collect(Collectors.toList());
    }
}
