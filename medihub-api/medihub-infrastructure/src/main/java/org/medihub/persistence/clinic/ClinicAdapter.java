package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic.GetClinicByIDPort;
import org.medihub.application.ports.outgoing.clinic.GetClinicNamesPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicAdapter implements SaveClinicPort, SearchClinicsPort, GetClinicNamesPort, GetClinicByIDPort {
    private final ClinicRepository clinicRepository;
    private final ClinicMapper mapper;

    @Override
    public Clinic saveClinic(Clinic clinic) {
        ClinicJpaEntity saved =
                clinicRepository.save(mapper.mapToJpaEntity(clinic));
        return mapper.mapToDomainEntity(saved);
    }

    @Override
    public List<Clinic> searchClinics(Date date, AppointmentType appointmentType) {
        return clinicRepository
                .findAll()
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Clinic> getClinicNames() {
        return clinicRepository
                .findAll()
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Clinic getByID(Long id) {
        return mapper.mapToDomainEntity(clinicRepository.findById(id).get());
    }
}
