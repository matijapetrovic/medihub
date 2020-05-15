package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

public interface GetDoctorByAccountIdPort {
    MedicalDoctor getDoctor(Long accountId);
}
