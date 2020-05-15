package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.clinic.Clinic;

public interface LoadClinicPort {
    Clinic loadClinic(Long clinicId);
}
