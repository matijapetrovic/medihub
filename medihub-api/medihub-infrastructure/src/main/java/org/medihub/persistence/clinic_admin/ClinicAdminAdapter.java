package org.medihub.persistence.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.domain.clinic.ClinicAdmin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClinicAdminAdapter implements
        AddClinicAdminPort,
        LoadClinicAdminPort {
    private final ClinicAdminRepository clinicAdminRepository;
    private final ClinicAdminMapper clinicAdminMapper;

    @Override
    public ClinicAdmin addClinicAdmin(ClinicAdmin clinicAdmin) {
        ClinicAdminJpaEntity clinicAdminJpaEntity =
                clinicAdminRepository.save(clinicAdminMapper.mapToJpaEntity(clinicAdmin));

        return clinicAdminMapper.mapToDomainEntity(clinicAdminJpaEntity);
    }

    @Override
    public ClinicAdmin loadClinicAdminByAccountId(Long accountId) {
        ClinicAdminJpaEntity clinicAdmin = clinicAdminRepository.findByAccount_Id(accountId).orElseThrow();
        return clinicAdminMapper.mapToDomainEntity(clinicAdmin);
    }

    @Override
    public List<ClinicAdmin> loadClinicAdminsFromClinic(Long clinicId) {
        return clinicAdminMapper.mapToDomainList(clinicAdminRepository.findAllByClinicId(clinicId));
    }
}
