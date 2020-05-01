package org.medihub.persistence.medical_doctor;

import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;
import org.medihub.persistence.working_calendar.WorkingCalendarMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalDoctorMapper {
    private AccountMapper accountMapper = new AccountMapper();
    private WorkingCalendarMapper workingCalendarMapper = new WorkingCalendarMapper();
    private ClinicMapper clinicMapper = new ClinicMapper();

    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                accountMapper.mapToDomainEntity(medicalDoctorJpaEntity.getAccount()),
                workingCalendarMapper.mapToDomainEntity(medicalDoctorJpaEntity.getWorkingCalendarJpaEntity()),
                clinicMapper.mapToDomainEntity(medicalDoctorJpaEntity.getClinic()),
                medicalDoctorJpaEntity
                        .getAppointments()
                        .stream()
                        .map(appointmentJpa -> new Appointment())
                        .collect(Collectors.toSet())
        );
    }

    public MedicalDoctorJpaEntity mapToJpaEntity(MedicalDoctor medicalDoctor){
        return new MedicalDoctorJpaEntity(
                null,
                accountMapper.mapToJpaEntity(medicalDoctor.getAccount()),
                workingCalendarMapper.mapToJpaEntity(medicalDoctor.getWorkingCalendar()),
                clinicMapper.mapToJpaEntity(medicalDoctor.getClinic()),
                medicalDoctor.getAppointments()
                        .stream()
                        .map(appointmentJpa -> new AppointmentJpaEntity())
                        .collect(Collectors.toSet())
        );
    }
}
