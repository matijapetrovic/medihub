package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.clinic.Clinic;

import java.util.List;

public interface GetClinicNamesPort {
    List<Clinic> getClinicNames();
}
