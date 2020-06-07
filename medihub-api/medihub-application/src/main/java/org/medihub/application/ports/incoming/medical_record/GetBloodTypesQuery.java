package org.medihub.application.ports.incoming.medical_record;

import java.util.List;

public interface GetBloodTypesQuery {
    public List<BloodTypeOutput> getBloodTypes();
}
