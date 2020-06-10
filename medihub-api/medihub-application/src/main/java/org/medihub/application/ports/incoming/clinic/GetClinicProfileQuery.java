package org.medihub.application.ports.incoming.clinic;

public interface GetClinicProfileQuery {
    GetClinicProfileOutput getClinicProfile(Long clinicId);
}
