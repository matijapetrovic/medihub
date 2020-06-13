package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

public interface GetMedicalDoctorByClinicIdOnDateUseCase {
    List<MedicalDoctor> loadAll(LocalDate date);
}
