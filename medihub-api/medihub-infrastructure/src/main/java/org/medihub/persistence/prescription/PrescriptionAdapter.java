package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionsPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Prescription;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrescriptionAdapter implements SavePrescriptionPort,
        GetPrescriptionsPort,
        GetPrescriptionPort {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    public Prescription savePrescription(Prescription p) {
        return prescriptionMapper.mapToDomainEntity(prescriptionRepository.save(prescriptionMapper.mapToJpaEntity(p)));
    }

    @Override
    public List<Prescription> getPrescriptions(Long clinicId) {
        return prescriptionRepository.getClinicPrescriptions(clinicId)
                .stream()
                .map(prescriptionMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Prescription getPrescription(Long id) {
        return prescriptionMapper.mapToDomainEntity(prescriptionRepository.getOne(id));
    }
}
