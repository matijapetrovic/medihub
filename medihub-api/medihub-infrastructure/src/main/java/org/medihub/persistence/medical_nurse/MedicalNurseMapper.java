package org.medihub.persistence.medical_nurse;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.*;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarMapper;
import org.springframework.stereotype.Component;

import java.sql.Time;

@RequiredArgsConstructor
@Component
public class MedicalNurseMapper {
    private final AccountMapper accountMapper;
    private final WorkingCalendarMapper workingCalendarMapper;
    private final ClinicMapper clinicMapper;

    public MedicalNurse mapToDomainEntity(MedicalNurseJpaEntity medicalNurseJpaEntity){
        return new MedicalNurse(
                medicalNurseJpaEntity.getId(),
                accountMapper.mapToDomainEntity(medicalNurseJpaEntity.getAccount()),
                workingCalendarMapper.mapToDomainEntity(medicalNurseJpaEntity.getWorkingCalendarJpaEntity()),
                clinicMapper.mapToDomainEntity(medicalNurseJpaEntity.getClinic()),
                new WorkingTime(medicalNurseJpaEntity.getFrom().toLocalTime(), medicalNurseJpaEntity.getTo().toLocalTime())
        );
    }

    public MedicalNurseJpaEntity mapToJpaEntity(MedicalNurse medicalNurse){
        return new MedicalNurseJpaEntity(
                null,
                accountMapper.mapToJpaEntity(medicalNurse.getAccount()),
                workingCalendarMapper.mapToJpaEntity(medicalNurse.getWorkingCalendar()),
                clinicMapper.mapToJpaEntity(medicalNurse.getClinic()),
                Time.valueOf(medicalNurse.getWorkingTime().getFrom()),
                Time.valueOf(medicalNurse.getWorkingTime().getTo())
        );
    }
}
