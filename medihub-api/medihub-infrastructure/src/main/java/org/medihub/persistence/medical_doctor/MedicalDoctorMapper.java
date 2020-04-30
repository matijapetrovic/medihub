package org.medihub.persistence.medical_doctor;

import org.medihub.domain.Appointment;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.WorkingCalendar;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.working_calendar.WorkingCalendarJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MedicalDoctorMapper {
    private final ClinicMapper clinicMapper = new ClinicMapper();

    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                new WorkingCalendar(),
                clinicMapper.mapToDomainEntity(medicalDoctorJpaEntity.getClinicJpaEntity()),
                medicalDoctorJpaEntity.
                        getAppointments()
                        .stream()
                        .map(appointment -> new Appointment())
                        .collect(Collectors.toSet())
                );
    }

    public MedicalDoctorJpaEntity mapToJpaEntity(MedicalDoctor medicalDoctor){
        return new MedicalDoctorJpaEntity(
                null,
                new WorkingCalendarJpaEntity(),
                clinicMapper.mapToJpaEntity(medicalDoctor.getClinic()),
                medicalDoctor.
                        getAppointments()
                        .stream()
                        .map(appointment -> new AppointmentJpaEntity())
                        .collect(Collectors.toSet())
        );
    }
}
