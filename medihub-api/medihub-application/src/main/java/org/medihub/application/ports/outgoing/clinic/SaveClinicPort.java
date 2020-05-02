package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.Clinic;

public interface SaveClinicPort {
    Clinic saveClinic(Clinic clinic);
}
