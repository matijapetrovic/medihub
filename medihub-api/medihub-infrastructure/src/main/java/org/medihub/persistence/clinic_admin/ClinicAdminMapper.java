package org.medihub.persistence.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.personal_info.PersonalInfoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicAdminMapper {
    private final AccountMapper accountMapper;
    private final ClinicMapper clinicMapper;
    private final PersonalInfoMapper personalInfoMapper;

    public ClinicAdminJpaEntity mapToJpaEntity(ClinicAdmin clinicAdmin) {
        return new ClinicAdminJpaEntity(
                clinicAdmin.getId(),
                clinicMapper.mapToJpaEntity(clinicAdmin.getClinic()),
                personalInfoMapper.mapToJpaEntity(clinicAdmin.getPersonalInfo()));
    }

    public ClinicAdmin mapToDomainEntity(ClinicAdminJpaEntity clinicAdminJpaEntity) {
        return new ClinicAdmin(
                clinicAdminJpaEntity.getId(),
                personalInfoMapper.mapToDomainEntity(clinicAdminJpaEntity.getPersonalInfo()),
                clinicMapper.mapToDomainEntity(clinicAdminJpaEntity.getClinic()));
    }

    public List<ClinicAdmin> mapToDomainList(List<ClinicAdminJpaEntity> clinicAdminJpaEntities) {
        return clinicAdminJpaEntities.stream().map(this::mapToDomainEntity).collect(Collectors.toList());
    }
}
