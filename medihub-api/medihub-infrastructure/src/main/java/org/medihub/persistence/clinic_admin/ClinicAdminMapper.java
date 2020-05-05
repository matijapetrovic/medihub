package org.medihub.persistence.clinic_admin;

import org.medihub.domain.ClinicAdmin;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.springframework.stereotype.Component;

@Component
public class ClinicAdminMapper {

    private final AccountMapper accountMapper = new AccountMapper();
    private final ClinicMapper clinicMapper = new ClinicMapper();

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
}
