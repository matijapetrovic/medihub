package org.medihub.application.ports.incoming.medical_doctor;

import java.util.List;

public interface GetDoctorsQuery {
    List<SearchDoctorsOutput> getDoctorsForClinic(Long clinicId);
}
