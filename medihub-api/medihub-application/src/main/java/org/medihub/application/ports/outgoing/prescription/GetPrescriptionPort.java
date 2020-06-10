package org.medihub.application.ports.outgoing.prescription;

import org.medihub.domain.Prescription;

public interface GetPrescriptionPort {
    Prescription getPrescription(Long id);
}
