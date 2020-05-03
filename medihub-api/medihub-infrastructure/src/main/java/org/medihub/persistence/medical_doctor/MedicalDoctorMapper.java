package org.medihub.persistence.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.*;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MedicalDoctorMapper {
    private final AccountMapper accountMapper;
    private final WorkingCalendarMapper workingCalendarMapper;
    private final ClinicMapper clinicMapper;
    private final AppointmentMapper appointmentMapper;

    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                medicalDoctorJpaEntity.getId(),
                accountMapper.mapToDomainEntity(medicalDoctorJpaEntity.getAccount()),
                workingCalendarMapper.mapToDomainEntity(medicalDoctorJpaEntity.getWorkingCalendarJpaEntity()),
                clinicMapper.mapToDomainEntity(medicalDoctorJpaEntity.getClinic()),
                medicalDoctorJpaEntity
                        .getAppointments()
                        .stream()
                        .map(appointmentMapper::mapToDomainEntity)
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
