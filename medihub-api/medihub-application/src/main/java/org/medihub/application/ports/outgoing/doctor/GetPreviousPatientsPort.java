package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.patient.Patient;

import java.util.List;

public interface GetPreviousPatientsPort {
    List<Patient> getPreviousPatients(Long doctorId);
}
