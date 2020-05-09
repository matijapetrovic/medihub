package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.domain.MedicalDoctor;

import java.util.List;

@RequiredArgsConstructor
public class GetMedicalDoctorService implements GetMedicalDoctorUseCase {
    private final GetAllDoctorsPort getAllDoctorsPort;

    @Override
    public List<MedicalDoctor> loadAll() {
        return getAllDoctorsPort.getAllDoctors();
    }
}
