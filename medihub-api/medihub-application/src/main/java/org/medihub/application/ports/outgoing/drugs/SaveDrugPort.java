package org.medihub.application.ports.outgoing.drugs;

import org.medihub.domain.Drug;

public interface SaveDrugPort {
    public Drug saveDrug(Drug drug);
}
