package org.medihub.persistence.medical_nurse;

import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.working_calendar.WorkingCalendarMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MedicalNurseMapper {
    private final AccountMapper accountMapper = new AccountMapper();
    private final WorkingCalendarMapper workingCalendarMapper = new WorkingCalendarMapper();
    private final ClinicMapper clinicMapper = new ClinicMapper();

    public MedicalNurse mapToDomainEntity(MedicalNurseJpaEntity medicalNurseJpaEntity){
        return new MedicalNurse(
                accountMapper.mapToDomainEntity(medicalNurseJpaEntity.getAccount()),
                workingCalendarMapper.mapToDomainEntity(medicalNurseJpaEntity.getWorkingCalendarJpaEntity()),
                clinicMapper.mapToDomainEntity(medicalNurseJpaEntity.getClinic())
        );
    }

    public MedicalNurseJpaEntity mapToJpaEntity(MedicalNurse medicalNurse){
        return new MedicalNurseJpaEntity(
                null,
                accountMapper.mapToJpaEntity(medicalNurse.getAccount()),
                workingCalendarMapper.mapToJpaEntity(medicalNurse.getWorkingCalendar()),
                clinicMapper.mapToJpaEntity(medicalNurse.getClinic())
        );
    }
}
