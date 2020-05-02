package org.medihub.persistence.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.*;
import org.medihub.persistence.account.AccountMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MedicalDoctorMapper {
    private AccountMapper accountMapper;
    private WorkingCalendarMapper workingCalendarMapper;
    private ClinicMapper clinicMapper;

    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                medicalDoctorJpaEntity.getId(),
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
