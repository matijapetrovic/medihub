package org.medihub.application.services.medical_record;

import org.medihub.application.ports.incoming.medical_record.GetBloodTypesQuery;
import org.medihub.domain.medical_record.PatientDetails;

import java.util.List;

public class GetBloodTypesService implements GetBloodTypesQuery {

    @Override
    public List<String> getBloodTypes() {
        return PatientDetails.BloodGroup.getBloodTypes();
    }
}
