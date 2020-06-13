package org.medihub.application.ports.outgoing.medical_nurse;

import org.medihub.domain.medical_nurse.MedicalNurseSchedule;

public interface GetNurseSchedulePort {
    MedicalNurseSchedule getSchedule(Long id);
}
