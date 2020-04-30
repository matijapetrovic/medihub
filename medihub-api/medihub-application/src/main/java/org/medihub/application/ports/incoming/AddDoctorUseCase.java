package org.medihub.application.ports.incoming;

import org.medihub.domain.MedicalDoctor;

public interface AddDoctorUseCase {
    void addDoctor(MedicalDoctor medicalDoctor);
}
