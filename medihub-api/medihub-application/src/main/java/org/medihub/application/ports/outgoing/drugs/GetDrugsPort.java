package org.medihub.application.ports.outgoing.drugs;

import org.medihub.domain.Drug;

import java.util.List;

public interface GetDrugsPort {
    public List<Drug> getDrugs();
}
