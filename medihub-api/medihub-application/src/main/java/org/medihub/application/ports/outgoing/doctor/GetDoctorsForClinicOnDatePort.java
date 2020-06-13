package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

public interface GetDoctorsForClinicOnDatePort {
    List<MedicalDoctor> getDoctorsForClinicOnDate(Long clinicId, LocalDate date);
}
