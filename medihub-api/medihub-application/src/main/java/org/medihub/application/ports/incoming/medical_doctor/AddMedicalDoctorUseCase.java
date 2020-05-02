package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.domain.MedicalDoctor;

public interface AddMedicalDoctorUseCase {
    void addDoctor(MedicalDoctor medicalDoctor);
}
