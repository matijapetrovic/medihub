package org.medihub.persistence.doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadDoctorPort;
import org.medihub.application.ports.outgoing.SaveDoctorPort;
import org.medihub.domain.MedicalDoctor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAdapter implements  LoadDoctorPort ,SaveDoctorPort{
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
}
