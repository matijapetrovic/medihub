package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;

import java.time.LocalDate;
import java.util.List;

public interface SearchClinicsPort {
    List<Clinic> searchClinics(LocalDate date, Long appointmentTypeId);
}
