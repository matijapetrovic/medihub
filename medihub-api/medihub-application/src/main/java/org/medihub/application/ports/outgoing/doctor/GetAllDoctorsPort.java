package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.MedicalDoctor;

import java.util.List;

public interface GetAllDoctorsPort {
    List<MedicalDoctor> getAllDoctors();
}
