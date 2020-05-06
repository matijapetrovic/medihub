package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.doctor.GetDoctorPort;
import org.medihub.domain.MedicalDoctor;

import java.util.List;

@RequiredArgsConstructor
public class GetMedicalDoctorService implements GetMedicalDoctorUseCase {
    private final GetDoctorPort getDoctorPort;

    @Override
    public List<MedicalDoctor> loadAll() {
        return getDoctorPort.getAllDoctors();
    }
}
