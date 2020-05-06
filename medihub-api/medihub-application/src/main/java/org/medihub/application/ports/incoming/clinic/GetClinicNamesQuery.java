package org.medihub.application.ports.incoming.clinic;

import java.util.List;

public interface GetClinicNamesQuery {
    List<GetClinicNamesOutput> getClinicNames();
}
