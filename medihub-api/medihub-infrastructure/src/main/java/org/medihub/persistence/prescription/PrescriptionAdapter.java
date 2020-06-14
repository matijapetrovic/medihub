package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.prescription.DeletePrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionsPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Drug;
import org.medihub.domain.Prescription;
import org.medihub.persistence.drug.DrugMapper;
import org.medihub.persistence.drug.DrugRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrescriptionAdapter implements SavePrescriptionPort,
        GetPrescriptionsPort,
        GetPrescriptionPort,
        DeletePrescriptionPort {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    @Override
    public Prescription savePrescription(Prescription p) {
        PrescriptionJpaEntity prescription = prescriptionMapper.mapToJpaEntity(p);
        return prescriptionMapper.mapToDomainEntity(prescriptionRepository.save(prescription));
    }

    @Override
    public List<Prescription> getPrescriptions(Long clinicId) {
        return prescriptionMapper.mapToDomainList(prescriptionRepository.getClinicPrescriptions(clinicId));
    }

    @Override
    public List<Prescription> getPrescriptionsForAppointment(Long appointmentId) {
        return prescriptionMapper.mapToDomainList(prescriptionRepository.findAllByFinishedAppointmentId(appointmentId));
    }

    @Override
    public Prescription getPrescription(Long id) {
        return prescriptionMapper.mapToDomainEntity(prescriptionRepository.getOne(id));
    }

    @Override
    public void delete(Prescription p) {
        prescriptionRepository.delete(prescriptionMapper.mapToJpaEntity(p));
    }
}
