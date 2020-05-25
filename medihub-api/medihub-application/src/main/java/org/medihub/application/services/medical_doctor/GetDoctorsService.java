package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.SearchDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetDoctorsService implements GetDoctorsQuery {
    private final GetDoctorsPort getDoctorsPort;

    @Override
    public List<SearchDoctorsOutput> getDoctorsForClinic(Long clinicId) {
        return getDoctorsPort
                .getDoctorsForClinic(clinicId)
                .stream()
                .map(medicalDoctor -> new SearchDoctorsOutput(
                        medicalDoctor.getId(),
                        medicalDoctor.getFirstName(),
                        medicalDoctor.getLastName(),
                        0.0,
                        medicalDoctor.getWorkingTime().getFrom().toString(),
                        medicalDoctor.getWorkingTime().getTo().toString()))
                .collect(Collectors.toList());
    }
}
