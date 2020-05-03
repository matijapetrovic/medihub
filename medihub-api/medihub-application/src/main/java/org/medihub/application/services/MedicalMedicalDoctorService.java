package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.domain.MedicalDoctor;

@RequiredArgsConstructor
public class MedicalMedicalDoctorService implements AddMedicalDoctorUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final SaveDoctorPort saveDoctorPort;

    @Override
    public void addDoctor(MedicalDoctor medicalDoctor) {
        saveDoctorPort.saveDoctor(medicalDoctor);
    }
}
