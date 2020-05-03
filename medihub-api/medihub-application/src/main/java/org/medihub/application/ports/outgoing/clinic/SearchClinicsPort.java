package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.AppointmentType;
import org.medihub.domain.Clinic;

import java.util.Date;
import java.util.List;

public interface SearchClinicsPort {
    List<Clinic> searchClinics(Date date, AppointmentType appointmentType);
}
