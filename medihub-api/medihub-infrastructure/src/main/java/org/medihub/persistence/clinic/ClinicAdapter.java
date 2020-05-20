package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.clinic.GetClinicNamesPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.appointment_type.AppointmentTypeRepository;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicAdapter implements SaveClinicPort, SearchClinicsPort, GetClinicNamesPort, LoadClinicPort {
    private final ClinicRepository clinicRepository;
    private final ClinicMapper mapper;
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Clinic saveClinic(Clinic clinic) {
        ClinicJpaEntity saved =
                clinicRepository.save(mapper.mapToJpaEntity(clinic));
        return mapper.mapToDomainEntity(saved);
    }

    @Override
    public List<Clinic> searchClinics(LocalDate date, Long appointmentTypeId) {
        AppointmentTypeJpaEntity appointmentType = appointmentTypeRepository
                .findById(appointmentTypeId)
                .orElseThrow(EntityNotFoundException::new);

        return clinicRepository
                .findAllWithDoctorsByAppointmentTypeOnDate(Date.valueOf(date), appointmentType)
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Clinic> getClinicNames() {
        return clinicRepository
                .findAll()
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Clinic loadClinic(Long clinicId) {
        return mapper.mapToDomainEntity(clinicRepository.findById(clinicId).get());
    }
}
