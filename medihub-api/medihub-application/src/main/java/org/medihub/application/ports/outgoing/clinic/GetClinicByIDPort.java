package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.Clinic;

public interface GetClinicByIDPort {
    Clinic getByID(Long id);
}
