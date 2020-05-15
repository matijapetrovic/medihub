package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

public interface SearchDoctorsPort {
    List<MedicalDoctor> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId);
}
