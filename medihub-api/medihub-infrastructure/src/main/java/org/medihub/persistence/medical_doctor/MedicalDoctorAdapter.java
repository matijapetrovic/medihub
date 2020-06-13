package org.medihub.persistence.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.*;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.persistence.appointment_type.AppointmentTypeRepository;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.clinic.ClinicRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAdapter implements
        LoadDoctorPort,
        SaveDoctorPort,
        GetDoctorsPort,
        GetAllDoctorsPort,
        SearchDoctorsPort,
        GetDoctorWorkingTimePort {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final MedicalDoctorRepository medicalDoctorRepository;
    private final ClinicRepository clinicRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public MedicalDoctor loadDoctor(Long id) {
        MedicalDoctorJpaEntity medicalDoctorJpaEntity =
                medicalDoctorRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        return medicalDoctorMapper.mapToDomainEntity(medicalDoctorJpaEntity);
    }

    @Override
    public void saveDoctor(MedicalDoctor doctor) {
        medicalDoctorRepository.save(medicalDoctorMapper.mapToJpaEntity(doctor));
    }

    @Override
    public List<MedicalDoctor> getAllDoctors() {
        return medicalDoctorMapper.mapToDomainList(medicalDoctorRepository.findAll());
    }

    @Override
    public List<MedicalDoctor> getDoctorsForClinicOnDate(Long clinicId, LocalDate date) {
        Timestamp dateStart = (date == null ? null : Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (date == null ? null :Timestamp.valueOf(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)));

        return medicalDoctorRepository
                .findAllByClinicIdOnDate(clinicId, dateStart, dateEnd)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalDoctor> getDoctorsForClinic(Long clinicId) {
        return medicalDoctorRepository
                .findAllByClinicIdAndArchivedFalse(clinicId)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalDoctor getMedicalDoctorById(Long id) {
        return medicalDoctorMapper.mapToDomainEntity(medicalDoctorRepository.findById(id).get());
    }

    @Override
    public List<MedicalDoctor> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId) {
        ClinicJpaEntity clinic = clinicRepository
                .findById(clinicId)
                .orElseThrow(EntityNotFoundException::new);

        var appointmentType =
                (appointmentTypeId == null ? null :
                        appointmentTypeRepository
                                .findById(appointmentTypeId)
                                .orElseThrow(EntityNotFoundException::new));

        Timestamp dateStart = (date == null ? null : Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (date == null ? null :Timestamp.valueOf(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)));

        return medicalDoctorRepository
                .findAllByClinicAndSpecializationAvailableOnDate(clinic, dateStart, dateEnd, appointmentType)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public WorkingTime getWorkingTime(Long doctorId) {
        MedicalDoctorJpaEntity doctor = medicalDoctorRepository
                .findById(doctorId)
                .orElseThrow(EntityNotFoundException::new);

        return new WorkingTime(doctor.getFrom().toLocalTime(), doctor.getTo().toLocalTime());
    }

    @Override
    public MedicalDoctor loadDoctorByAccountId(Long accountId) {
        MedicalDoctorJpaEntity doctor = medicalDoctorRepository
                .findByPersonalInfoAccountId(accountId);
        return medicalDoctorMapper.mapToDomainEntity(doctor);
    }
}

