package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

public interface SaveDoctorPort {
    void saveDoctor(MedicalDoctor doctor);
}
