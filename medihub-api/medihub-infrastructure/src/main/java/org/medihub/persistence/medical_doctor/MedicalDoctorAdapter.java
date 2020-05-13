package org.medihub.persistence.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.*;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.appointment_type.AppointmentTypeRepository;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.medihub.persistence.clinic.ClinicRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAdapter implements
        LoadDoctorPort,
        SaveDoctorPort,
        GetDoctorsPort,
        GetAllDoctorsPort,
        SearchDoctorsPort {
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

    public List<MedicalDoctor> getDoctorsForClinic(Long clinicId) {
        return medicalDoctorRepository
                .findAllByClinicId(clinicId)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalDoctor> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId) {
        ClinicJpaEntity clinic = clinicRepository
                .findById(clinicId)
                .orElseThrow(EntityNotFoundException::new);

        AppointmentTypeJpaEntity appointmentType = appointmentTypeRepository
                .findById(appointmentTypeId)
                .orElseThrow(EntityNotFoundException::new);

        return medicalDoctorRepository
                .findAllByClinicAndSpecializationAvailableOnDate(clinic, Date.valueOf(date), appointmentType)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}

