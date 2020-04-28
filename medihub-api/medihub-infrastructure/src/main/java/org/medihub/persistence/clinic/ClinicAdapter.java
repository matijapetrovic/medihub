package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.SaveClinicPort;
import org.medihub.domain.Clinic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicAdapter implements SaveClinicPort {
    private final ClinicRepository clinicRepository;
    private final ClinicMapper mapper;

    @Override
    public void saveClinic(Clinic clinic) {
        ClinicJpaEntity saved =
                clinicRepository.save(mapper.mapToJpaEntity(clinic));
    }
}
