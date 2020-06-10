package org.medihub.application.ports.incoming.medical_doctor;

public interface CheckMedicalRecordPermissionUseCase {
    boolean doctorHasPermission(Long patientId);
}
