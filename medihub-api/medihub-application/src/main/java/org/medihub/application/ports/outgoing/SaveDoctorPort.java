package org.medihub.application.ports.outgoing;

import org.medihub.domain.MedicalDoctor;

public interface SaveDoctorPort {
    void saveDoctor(MedicalDoctor doctor);
}
