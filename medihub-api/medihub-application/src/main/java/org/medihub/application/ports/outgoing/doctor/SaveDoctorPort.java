package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.MedicalDoctor;

public interface SaveDoctorPort {
    void saveDoctor(MedicalDoctor doctor);
}
