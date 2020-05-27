package org.medihub.application.ports.outgoing.drugs;

import org.medihub.domain.Drug;

public interface GetDrugByIdPort {
    public Drug getDrugById(Long id);
}
