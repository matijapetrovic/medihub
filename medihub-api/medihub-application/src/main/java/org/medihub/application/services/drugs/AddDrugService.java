package org.medihub.application.services.drugs;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.drugs.AddDrugUseCase;
import org.medihub.application.ports.outgoing.drugs.SaveDrugPort;
import org.medihub.domain.Drug;

@RequiredArgsConstructor
public class AddDrugService implements AddDrugUseCase {
    private final SaveDrugPort saveDrugPort;

    @Override
    public Drug addDrug(String name) {
        return saveDrugPort.saveDrug(new Drug(null, name));
    }
}
