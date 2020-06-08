package org.medihub.application.ports.incoming.medical_record;

public interface GetPatientMedicalRecordQuery {
    public GetMedicalRecordOutput getPatientMedicalRecord(Long id);
}
