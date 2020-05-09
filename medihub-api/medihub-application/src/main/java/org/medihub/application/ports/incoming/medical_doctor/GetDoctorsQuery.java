package org.medihub.application.ports.incoming.medical_doctor;

import java.util.List;

public interface GetDoctorsQuery {
    List<GetDoctorsOutput> getDoctorsForClinic(Long clinicId);
}
