package org.medihub.application.ports.outgoing;

import org.medihub.domain.clinic.ClinicAdmin;

public interface LoadClinicAdminPort {
    ClinicAdmin loadClinicAdminByAccountId(Long accountId);
}
