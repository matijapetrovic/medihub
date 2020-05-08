package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicAdmin;

public interface LoadClinicAdminPort {
    ClinicAdmin loadClinicAdmin(Long accountId);
}
