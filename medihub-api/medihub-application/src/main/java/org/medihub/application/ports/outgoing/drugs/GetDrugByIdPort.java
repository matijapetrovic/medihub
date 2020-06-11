package org.medihub.application.ports.outgoing.drugs;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.Drug;

public interface GetDrugByIdPort {
    Drug getDrugById(Long id) throws NotFoundException;
}
