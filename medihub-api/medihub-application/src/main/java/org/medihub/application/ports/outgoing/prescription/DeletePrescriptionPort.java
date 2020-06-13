package org.medihub.application.ports.outgoing.prescription;

import org.medihub.domain.Prescription;

public interface DeletePrescriptionPort {
    void delete(Prescription p);
}
