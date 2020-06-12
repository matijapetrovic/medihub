package org.medihub.persistence.medical_nurse;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.personal_info.PersonalInfoMapper;
import org.springframework.stereotype.Component;

import java.sql.Time;

@RequiredArgsConstructor
@Component
public class MedicalNurseMapper {
    private final AccountMapper accountMapper;
    private final ClinicMapper clinicMapper;
    private final PersonalInfoMapper personalInfoMapper;

    public MedicalNurse mapToDomainEntity(MedicalNurseJpaEntity medicalNurseJpaEntity){
        return new MedicalNurse(
                medicalNurseJpaEntity.getId(),
                personalInfoMapper.mapToDomainEntity(medicalNurseJpaEntity.getPersonalInfo()),
                clinicMapper.mapToDomainEntity(medicalNurseJpaEntity.getClinic()),
                new WorkingTime(medicalNurseJpaEntity.getFrom().toLocalTime(), medicalNurseJpaEntity.getTo().toLocalTime())
        );
    }

    public MedicalNurseJpaEntity mapToJpaEntity(MedicalNurse medicalNurse){
        return new MedicalNurseJpaEntity(
                medicalNurse.getId(),
                personalInfoMapper.mapToJpaEntity(medicalNurse.getPersonalInfo()),
                clinicMapper.mapToJpaEntity(medicalNurse.getClinic()),
                Time.valueOf(medicalNurse.getWorkingTime().getFrom()),
                Time.valueOf(medicalNurse.getWorkingTime().getTo())
        );
    }
}
