package org.medihub.application.ports.outgoing.scheduling.schedule_item;

import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.domain.medical_nurse.MedicalNurseScheduleItem;

import java.time.LocalDate;

public interface SaveMedicalNurseScheduleItemPort {
    void saveMedicalNurseScheduleItem(MedicalNurseScheduleItem item, MedicalNurse nurse, LocalDate date);
}
