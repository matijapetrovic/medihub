package org.medihub.persistence.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.domain.MedicalDoctor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAdapter implements
        LoadDoctorPort,
        SaveDoctorPort,
        GetDoctorsPort {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final MedicalDoctorRepository medicalDoctorRepository;

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
    public List<MedicalDoctor> getDoctorsForClinic(Long clinicId) {
        return medicalDoctorRepository
                .findAllByClinicId(clinicId)
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
