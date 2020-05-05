package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicAdmin;

public interface AddClinicAdminPort {
    ClinicAdmin addClinicAdmin(ClinicAdmin clinicAdmin);
}
