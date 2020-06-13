package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.GetPreviousPatientsPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.patient.SavePatientPort;
import org.medihub.domain.patient.Patient;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientAdapter implements
        GetPatientsPort,
        LoadPatientPort,
        GetPreviousPatientsPort,
        SavePatientPort {
    private final PatientMapper patientMapper;
        private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientMapper.mapToDomainList(patientRepository.findAll());
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientMapper.mapToDomainEntity(patientRepository.findById(id).get());
    }

    @Override
    public Patient loadPatient(Long patientId) {
        PatientJpaEntity patient = patientRepository
                .findById(patientId)
                .orElseThrow(EntityNotFoundException::new);

        return patientMapper.mapToDomainEntity(patient);
    }

    @Override
    public Patient loadPatientByAccountId(Long accountId) {
        PatientJpaEntity patient = patientRepository
                .findByPersonalInfoAccountId(accountId)
                .orElseThrow(EntityNotFoundException::new);

        return patientMapper.mapToDomainEntity(patient);
    }

    @Override
    public List<Patient> getPreviousPatients(Long doctorId) {
        return patientRepository.getPreviousPatients(doctorId)
                .stream()
                .map(patientMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientMapper.mapToDomainEntity(patientRepository.save(patientMapper.mapToJpaEntity(patient)));
    }
}
