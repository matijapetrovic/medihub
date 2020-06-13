package org.medihub.application.ports.outgoing.medical_nurse;

import org.medihub.domain.medical_nurse.MedicalNurse;

public interface GetMedicalNurseByAccountIdPort {
    MedicalNurse getMedicalNurseByAccountId(Long id);
}
