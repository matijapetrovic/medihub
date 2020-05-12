package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;

import java.util.Date;
import java.util.List;

public interface SearchClinicsPort {
    List<Clinic> searchClinics(Date date, AppointmentType appointmentType);
}
