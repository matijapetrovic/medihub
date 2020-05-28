package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

@RequiredArgsConstructor
public class GetMedicalDoctorService implements GetMedicalDoctorUseCase {
    private final GetAllDoctorsPort getAllDoctorsPort;

    @Override
    public List<MedicalDoctor> loadAll() {
        return getAllDoctorsPort.getAllDoctors();
    }
}
