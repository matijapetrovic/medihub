package org.medihub.application.ports.outgoing.drugs;

import org.medihub.domain.Drug;

public interface SaveDrugPort {
    Drug saveDrug(Drug drug);
}
