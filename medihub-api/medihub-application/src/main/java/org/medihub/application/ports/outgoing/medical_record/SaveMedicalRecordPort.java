package org.medihub.application.ports.outgoing.medical_record;

import org.medihub.domain.medical_record.MedicalRecord;

public interface SaveMedicalRecordPort {
    void saveMedicalRecordPort(MedicalRecord medicalRecord);
}
