package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

public interface GetAllClinicMedicalDoctorsUseCase {
    List<MedicalDoctor> loadAll();
}

