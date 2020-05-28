package org.medihub.application.ports.outgoing.prescription;

import org.medihub.domain.Prescription;

public interface SavePrescriptionPort {
    public Prescription savePrescription(Prescription p);
}
