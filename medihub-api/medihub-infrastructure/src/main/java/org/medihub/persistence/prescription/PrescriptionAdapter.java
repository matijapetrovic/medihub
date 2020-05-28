package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Prescription;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrescriptionAdapter implements SavePrescriptionPort {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    public Prescription savePrescription(Prescription p) {
        return prescriptionMapper.mapToDomainEntity(prescriptionRepository.save(prescriptionMapper.mapToJpaEntity(p)));
    }
}
