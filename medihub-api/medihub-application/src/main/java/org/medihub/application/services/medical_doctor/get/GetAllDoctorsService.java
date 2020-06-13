package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetAllMedicalDoctorsUseCase;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllDoctorsService implements GetAllMedicalDoctorsUseCase {
    private final GetAllDoctorsPort getAllDoctorsPort;

    @Override
    public List<MedicalDoctor> getAll() {
        return getAllDoctorsPort.getAllDoctors();
    }
}
