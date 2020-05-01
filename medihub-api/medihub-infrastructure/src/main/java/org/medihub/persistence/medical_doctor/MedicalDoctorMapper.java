package org.medihub.persistence.medical_doctor;

import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.medihub.persistence.account.AccountJpaEntity;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalDoctorMapper {
    private ClinicMapper clinicMapper = new ClinicMapper();

    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                new Account(
                        null,
                        medicalDoctorJpaEntity.getAccount().getEmail(),
                        medicalDoctorJpaEntity.getAccount().getPassword(),
                        new PersonalInfo(
                                medicalDoctorJpaEntity.getAccount().getFirstName(),
                                medicalDoctorJpaEntity.getAccount().getLastName(),
                                new Address(
                                        medicalDoctorJpaEntity.getAccount().getAddress(),
                                        medicalDoctorJpaEntity.getAccount().getCity(),
                                        medicalDoctorJpaEntity.getAccount().getCountry()
                                ),
                                medicalDoctorJpaEntity.getAccount().getTelephoneNumber()
                        ),
                        medicalDoctorJpaEntity.getAccount().isPasswordChanged(),
                        null
                ),
                new WorkingCalendar(),
                new Clinic(
                        medicalDoctorJpaEntity.getClinic().getId(),
                        medicalDoctorJpaEntity.getClinic().getName(),
                        new Address(
                                medicalDoctorJpaEntity.getClinic().getAddress(),
                                medicalDoctorJpaEntity.getClinic().getCity(),
                                medicalDoctorJpaEntity.getClinic().getCountry()
                        ),
                        medicalDoctorJpaEntity.getClinic().getDescription()),
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
                new AccountJpaEntity(
                        null,
                        medicalDoctor.getAccount().getEmail(),
                        medicalDoctor.getAccount().getPassword(),
                        medicalDoctor.getAccount().getFirstName(),
                        medicalDoctor.getAccount().getLastName(),
                        medicalDoctor.getAccount().getAddress(),
                        medicalDoctor.getAccount().getCity(),
                        medicalDoctor.getAccount().getCountry(),
                        medicalDoctor.getAccount().getTelephoneNumber(),
                        medicalDoctor.getAccount().isPasswordChanged(),
                        null
                ),
                new WorkingCalendarJpaEntity(),
                clinicMapper.mapToJpaEntity(medicalDoctor.getClinic()),
                medicalDoctor.getAppointments()
                        .stream()
                        .map(appointmentJpa -> new AppointmentJpaEntity())
                        .collect(Collectors.toSet())
        );
    }
}
