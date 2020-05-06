package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetDoctorsService implements GetDoctorsQuery {
    private final GetDoctorsPort getDoctorsPort;

    @Override
    public List<GetDoctorsOutput> getDoctorsForClinic(Long clinicId) {
        return getDoctorsPort
                .getDoctorsForClinic(clinicId)
                .stream()
                .map(medicalDoctor -> new GetDoctorsOutput(
                        medicalDoctor.getFirstName(),
                        medicalDoctor.getLastName(),
                        5.0))
                .collect(Collectors.toList());
    }
}
