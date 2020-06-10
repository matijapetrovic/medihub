package org.medihub.application.ports.incoming.prescription;

import java.util.List;

public interface GetPrescriptionsQuery {
    List<GetPrescriptionsOutput> getPrescriptions();
}
