package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

public interface GetDoctorsPort {
    List<MedicalDoctor> getDoctorsForClinic(Long clinicId);

    MedicalDoctor getMedicalDoctorById(Long id);
}
