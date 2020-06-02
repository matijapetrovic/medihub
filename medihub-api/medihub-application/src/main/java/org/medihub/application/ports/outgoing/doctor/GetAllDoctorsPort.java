package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

public interface GetAllDoctorsPort {
    List<MedicalDoctor> getAllDoctors(Long clinicId);
}
