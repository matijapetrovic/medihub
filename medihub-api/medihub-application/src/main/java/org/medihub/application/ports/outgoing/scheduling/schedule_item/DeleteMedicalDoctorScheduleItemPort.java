package org.medihub.application.ports.outgoing.scheduling.schedule_item;

import org.medihub.application.exceptions.NotFoundException;

public interface DeleteMedicalDoctorScheduleItemPort {
    void deleteMedicalDoctorScheduleItem(Long scheduleItemId) throws NotFoundException;
}
