package org.medihub.application.ports.outgoing;

import org.medihub.domain.Clinic;

public interface SaveClinicPort {
    void saveClinic(Clinic clinic);
}
