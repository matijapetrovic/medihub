package org.medihub.persistence.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.domain.ClinicAdmin;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddClinicAdminAdapter implements AddClinicAdminPort {
    private final ClinicAdminRepository clinicAdminRepository;
    private final ClinicAdminMapper clinicAdminMapper;

    @Override
    public ClinicAdmin addClinicAdmin(ClinicAdmin clinicAdmin) {
        ClinicAdminJpaEntity clinicAdminJpaEntity =
                clinicAdminRepository.save(clinicAdminMapper.mapToJpaEntity(clinicAdmin));

        return clinicAdminMapper.mapToDomainEntity(clinicAdminJpaEntity);
    }
}
