package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.MedicalDoctor;

public interface LoadDoctorPort {
    MedicalDoctor loadDoctor(Long id);
}
