package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.application.ports.incoming.patient.PatientResponse;

import java.util.List;

public interface GetPreviousPatientsQuery {
    List<PatientResponse> getPreviousPatients();
}
