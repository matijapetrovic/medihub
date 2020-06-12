package org.medihub.application.ports.outgoing.prescription;

import org.medihub.domain.Prescription;

import java.util.List;

public interface GetPrescriptionsPort {
    List<Prescription> getPrescriptions(Long clinicId);
    List<Prescription> getPrescriptionsForAppointment(Long appointmentId);
}
