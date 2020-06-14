package org.medihub.application.ports.outgoing.doctor;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.medical_doctor.MedicalDoctor;

public interface LoadDoctorPort {
    MedicalDoctor loadDoctor(Long id) throws NotFoundException;
    MedicalDoctor loadDoctorByAccountId(Long accountId);
}
