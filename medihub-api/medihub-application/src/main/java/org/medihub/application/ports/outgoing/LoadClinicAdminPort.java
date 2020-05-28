package org.medihub.application.ports.outgoing;

import org.medihub.domain.clinic.ClinicAdmin;

import java.util.List;

public interface LoadClinicAdminPort {
    ClinicAdmin loadClinicAdminByAccountId(Long accountId);

    List<ClinicAdmin> loadClinicAdminsFromClinic(Long clinicId);
}
