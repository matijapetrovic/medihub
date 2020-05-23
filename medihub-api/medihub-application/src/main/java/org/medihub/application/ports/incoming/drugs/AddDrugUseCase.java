package org.medihub.application.ports.incoming.drugs;

import org.medihub.domain.Drug;

public interface AddDrugUseCase {
    public Drug addDrug(String name);
}
