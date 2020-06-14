package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic.*;
import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.appointment_type.AppointmentTypeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicAdapter implements
        SaveClinicPort,
        SearchClinicsPort,
        GetClinicNamesPort,
        LoadClinicPort,
        GetAppointmentPricePort{
    private final ClinicRepository clinicRepository;
    private final ClinicMapper mapper;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Clinic saveClinic(Clinic clinic) {
        ClinicJpaEntity saved =
                clinicRepository.save(mapper.mapToJpaEntity(clinic));
        return mapper.mapToDomainEntity(saved);
    }

    @Override
    public List<Clinic> searchClinics(LocalDate date, Long appointmentTypeId) {
        AppointmentTypeJpaEntity appointmentType =
                (appointmentTypeId == null ? null :
                    appointmentTypeRepository
                        .findById(appointmentTypeId)
                        .orElseThrow(EntityNotFoundException::new));

        Timestamp dateStart = (date == null ? null : Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (date == null ? null :Timestamp.valueOf(LocalDateTime.of(date, LocalTime.of(23, 0))));

        return clinicRepository
                .findAllWithDoctorsByAppointmentTypeOnDate(dateStart, dateEnd, appointmentType)
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

    @Override
    public Map<AppointmentType, Money> getPrices(Long clinicId) {
        return mapper.mapToDomainEntity(clinicRepository.findById(clinicId).get()).getAppointmentPrices();
    }
}
