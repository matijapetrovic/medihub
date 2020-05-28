package org.medihub.persistence.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicAdminMapper {
    private final AccountMapper accountMapper;
    private final ClinicMapper clinicMapper;

    public ClinicAdminJpaEntity mapToJpaEntity(ClinicAdmin clinicAdmin) {
        return new ClinicAdminJpaEntity(
                clinicAdmin.getId(),
                accountMapper.mapToJpaEntity(clinicAdmin.getAccount()),
                clinicMapper.mapToJpaEntity(clinicAdmin.getClinic())
        );
    }

    public ClinicAdmin mapToDomainEntity(ClinicAdminJpaEntity clinicAdminJpaEntity) {
        return new ClinicAdmin(
                clinicAdminJpaEntity.getId(),
                accountMapper.mapToDomainEntity(clinicAdminJpaEntity.getAccount()),
                clinicMapper.mapToDomainEntity(clinicAdminJpaEntity.getClinic())
        );
    }

    public List<ClinicAdmin> mapToDomainList(List<ClinicAdminJpaEntity> clinicAdminJpaEntities) {
        return clinicAdminJpaEntities.stream().map(this::mapToDomainEntity).collect(Collectors.toList());
    }
}
