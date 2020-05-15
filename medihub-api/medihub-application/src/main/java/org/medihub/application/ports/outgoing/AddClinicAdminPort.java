package org.medihub.application.ports.outgoing;

import org.medihub.domain.clinic.ClinicAdmin;

public interface AddClinicAdminPort {
    ClinicAdmin addClinicAdmin(ClinicAdmin clinicAdmin);
}
