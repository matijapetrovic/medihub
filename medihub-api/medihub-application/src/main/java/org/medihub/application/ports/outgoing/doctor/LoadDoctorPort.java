package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

public interface LoadDoctorPort {
    MedicalDoctor loadDoctor(Long id);
    MedicalDoctor loadDoctorByAccountId(Long accountId);
}
