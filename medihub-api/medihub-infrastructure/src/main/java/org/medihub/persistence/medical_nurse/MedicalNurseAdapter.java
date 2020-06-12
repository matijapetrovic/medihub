package org.medihub.persistence.medical_nurse;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalNurseAdapter implements GetMedicalNurseByAccountIdPort {
    private final MedicalNurseRepository medicalNurseRepository;
    private final MedicalNurseMapper medicalNurseMapper;

    @Override
    public MedicalNurse getMedicalNurseByAccountId(Long id) {
        return medicalNurseMapper.mapToDomainEntity(medicalNurseRepository.findByPersonalInfoAccountId(id));
    }
}
